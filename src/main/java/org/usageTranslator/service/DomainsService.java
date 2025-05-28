package org.usageTranslator.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usageTranslator.dao.DomainDao;
import org.usageTranslator.model.Domain;
import java.util.*;
import static org.usageTranslator.constants.CSVDataConstants.*;
import static org.usageTranslator.constants.QueryConstants.INSERT_DOMAIN_QUERY;

//  This class is responsible for the validation of the input data, and business logic associated with the Domain table.
public class DomainsService {
    public static final Logger logger = LogManager.getLogger("ChargeableService.class");
    private final DomainDao domainDao;

    public DomainsService(DomainDao domainDao) {
        this.domainDao = domainDao;
    }

    public void insertDomainData(List<String[]> csvData, Map<String, String> jsonData) {
         logger.debug("DomainsService: insertDomainData");
        try {
            if(csvData == null || jsonData == null) {
                throw new NullPointerException("Input data is null ");
            }
            Set<String> domainsSet = new HashSet<>();
            // Put the domains in the domainsSet to maintain distinct values
            for (String[] csvEntry : csvData) {
                if(csvEntry != null && csvEntry.length > 0) {
                    String domain = csvEntry[DOMAINS_COLUMN];
                    if (!domainsSet.contains(domain)) {
                        Domain domainObj = new Domain();
                        domainObj.setDomain(csvEntry[DOMAINS_COLUMN]);
                        domainObj.setPartnerPurchasedPlanID(csvEntry[ACCOUNTGUID_COLUMN].replaceAll("[^a-zA-Z0-9]", ""));
                        domainsSet.add(csvEntry[DOMAINS_COLUMN]);
                        domainDao.insertDomainData(domainObj);
                    }
                }
            }
            System.out.println("Successfully executed Insert Query for Domain: " + INSERT_DOMAIN_QUERY);

        } catch (NullPointerException npe) {
             logger.error("Exception while inserting into domain table. " + npe.toString());
            throw npe;
        } catch (Exception e) {
             logger.error("Exception while inserting into domain table. " + e.toString());
        }
    }
}
