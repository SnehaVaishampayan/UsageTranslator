package org.usageTranslator.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usageTranslator.dao.ChargeableDao;
import org.usageTranslator.model.Chargeable;
import org.usageTranslator.validator.InputDataValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.usageTranslator.constants.CSVDataConstants.*;
import static org.usageTranslator.constants.QueryConstants.INSERT_CHARGEABLE_QUERY;

//  This class is responsible for the validation of the input data, and business logic associated with the Chargeable table.
public class ChargeableService {
    public static final Logger logger = LogManager.getLogger("ChargeableService.class");

    private final InputDataValidator inputDataValidator = new InputDataValidator();
    private final ChargeableDao chargeableDao;
    public ChargeableService(ChargeableDao chargeableDao) {
        this.chargeableDao = chargeableDao;
    }

    public void insertChargeableData(List<String[]> csvData, Map<String, String> jsonData) {
         logger.debug("ChargeableService: insertChargeableData");
        try {
            if(csvData == null || jsonData == null) {
                throw new NullPointerException("ChargeableService: Input data is null ");
            }
            Map<String,Integer> statsMap = new HashMap<>(); // statsMap to maintain the itemCount for each product

            for (String[] csvEntry : csvData) {
                if(csvEntry != null && csvEntry.length > 0) {
                    if (inputDataValidator.validateData(csvEntry)) {
                        Chargeable insertedChargeable = chargeableDao.insertChargeableData(populateChargeableObj(new Chargeable(), csvEntry, jsonData));
                        String statsMapKey = insertedChargeable.getProduct();
                        int itemCount = Integer.parseInt(csvEntry[ITEMCOUNT_COLUMN]);
                        statsMap.put(statsMapKey, (statsMap.getOrDefault(statsMapKey, 0) + itemCount));

                        logger.info("Success: For Product " + statsMapKey + " : " + statsMap.get(statsMapKey) + "items successfully inserted.");
                    }
                }
            }
            System.out.println("Successfully executed Insert Query for Chargeable: " + INSERT_CHARGEABLE_QUERY);

        } catch (NullPointerException npe) {
             logger.error("Exception while inserting into chargeable table" + npe.toString());
            throw npe;
        } catch (Exception e) {
             logger.error("Exception while inserting into chargeable table" + e.toString());
        }
    }

    private Chargeable populateChargeableObj(Chargeable chargeableObj, String[] csvEntry, Map<String, String> jsonData) {
         logger.debug("ChargeableService: populateChargeableObj");

        try {
            if(csvEntry == null || jsonData == null) {
                throw new NullPointerException("ChargeableService: populateChargeableObj(): Input data is null ");
            }
            chargeableObj.setPartnerID(Integer.parseInt(csvEntry[PARTNERID_COLUMN]));
            chargeableObj.setProduct(jsonData.get(csvEntry[PARTNUMBER_COLUMN]));
            chargeableObj.setPartnerPurchasedPlanID(csvEntry[ACCOUNTGUID_COLUMN].replaceAll("[^a-zA-Z0-9]", ""));
            chargeableObj.setPlan(csvEntry[PLAN_COLUMN]);
            int item_count = Integer.parseInt(csvEntry[ITEMCOUNT_COLUMN]);
            int usage = item_count;
            if(UNIT_REDUCTION_RULE.containsKey(csvEntry[PARTNUMBER_COLUMN])) {
                usage = (item_count/UNIT_REDUCTION_RULE.get(csvEntry[PARTNUMBER_COLUMN]));
            }
            chargeableObj.setUsage(usage);
        } catch (NullPointerException npe) {
             logger.error("Exception while populating chargeable object" + npe.toString());
            throw npe;
        } catch (Exception e) {
             logger.error("Exception while populating chargeable object" + e.toString());
        }
        return chargeableObj;
    }
}
