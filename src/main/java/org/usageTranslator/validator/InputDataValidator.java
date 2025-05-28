package org.usageTranslator.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.usageTranslator.constants.CSVDataConstants.*;

//  Responsible for the validating the input data as per the constraints in the requirements.
public class InputDataValidator {
    public static final Logger logger = LogManager.getLogger("InputDataValidator.class");

    public boolean validateData(String[] csvEntry){
        try {
            if (csvEntry == null || csvEntry.length == 0) {
                throw new Exception("Empty CSV data");
            }
        }
        catch (Exception e) {
             logger.info(e);
        }
        return isPartNumberPresent(csvEntry) && isItemCountValid(csvEntry)
                && isPartnerIDValid(csvEntry);
    }

    // Validate if PartNumber is Present
    private boolean isPartNumberPresent(String[] csvEntry) {
        try {
            if (csvEntry == null || csvEntry.length == 0) {
                throw new Exception("Empty CSV data");
            }

            if(csvEntry[PARTNUMBER_COLUMN] == null || csvEntry[PARTNUMBER_COLUMN].isEmpty()) {
                 logger.error("Invalid PartNumber ");
                return false;
            }
        }
        catch (Exception e) {
             logger.info(e);
        }
        return true;
    }

    // Validate if ‘itemCount’ is non-positive
    private boolean isItemCountValid(String[] csvEntry) {
        try {
            if (csvEntry == null || csvEntry.length == 0) {
                throw new Exception("Empty CSV data");
            }
            if (csvEntry[ITEMCOUNT_COLUMN] == null || Integer.parseInt(csvEntry[ITEMCOUNT_COLUMN]) <= 0) {
                 logger.error("Invalid ItemCount ");
                return false;
            }
        }
         catch (Exception e) {
             logger.info(e);
        }
        return true;
    }

    //  Validate PartnerId is not in Configurable list.
    private boolean isPartnerIDValid(String[] csvEntry) {
        try {
            if (csvEntry == null || csvEntry.length == 0) {
                throw new Exception("Empty CSV data");
            }
            if(PARTNERID_CONFIGURABLE_LIST.contains(csvEntry[PARTNERID_COLUMN])) {
                return false;
            }
        }
        catch (Exception e) {
             logger.info(e);
        }
        return true;
    }
}
