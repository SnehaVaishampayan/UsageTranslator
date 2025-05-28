package org.usageTranslator;

import org.apache.logging.log4j.Logger;
import org.usageTranslator.dao.ChargeableDao;
import org.usageTranslator.service.ChargeableService;
import org.usageTranslator.dao.DomainDao;
import org.usageTranslator.service.DomainsService;
import org.usageTranslator.util.InputCSVReaderUtil;
import org.usageTranslator.util.InputJSONReaderUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import static org.usageTranslator.constants.ConfigConstants.*;

//  Start of the Application
public class App {
    public static final Logger logger = LogManager.getLogger("App.class");
    public static void main(String[] args) {
        ChargeableDao chargeableDao;
        DomainDao domainDao;
        ChargeableService chargeableService;
        DomainsService domainsService;
        Connection conn;
        try {
              logger.debug("Starting the application...");
            // Read CSV
            List<String[]> csvData;
             logger.debug("Read Input from CSV ");
            InputCSVReaderUtil inputCSVReaderUtil = new InputCSVReaderUtil();   // TODO
            csvData = inputCSVReaderUtil.readData();

            // Read JSON
             logger.debug("Read Input from JSON ");
            InputJSONReaderUtil inputJSONReaderUtil = new InputJSONReaderUtil();
            Map<String, String> jsonData = inputJSONReaderUtil.readData();

            // Connect to database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
              logger.debug("Connected to MySQL");

            chargeableDao = new ChargeableDao(conn);
            chargeableDao.createTable();
              logger.debug("Chargeable Table created ");

            domainDao = new DomainDao(conn);
            domainDao.createTable();
              logger.debug("Domain Table created ");

            // Insert data in chargeable table
            chargeableService = new ChargeableService(chargeableDao);
            chargeableService.insertChargeableData(csvData, jsonData);

            // Insert data in domain table
            domainsService = new DomainsService(domainDao);
            domainsService.insertDomainData(csvData, jsonData);

            conn.close();
        }
        catch(IOException ioe) {
            logger.error("Exception in App");
        }
        catch(Exception e) {
            logger.error("Exception in App");
        }
    }
}