package org.usageTranslator.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usageTranslator.model.Chargeable;
import org.usageTranslator.model.Domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static org.usageTranslator.constants.QueryConstants.*;

public class DomainDao {
    public static final Logger logger = LogManager.getLogger("DomainDao.class");

    private Connection conn;


    public DomainDao(Connection conn) {
        this.conn = conn;
    }

    public void createTable() {
          logger.debug("DomainDao: createTable");

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(DROP_DOMAIN_TABLE);
            stmt.execute(CREATE_DOMAIN_TABLE);
        }
        catch (Exception e) {
             logger.error("Exception while creating domain table. " + e.toString());
        }
    }

    public void insertDomainData(Domain domain) throws SQLException {
          logger.debug("DomainDao: insertDomainData");

        try (PreparedStatement ps = conn.prepareStatement(INSERT_DOMAIN_QUERY)) {
            ps.setString(1, domain.getPartnerPurchasedPlanID());
            ps.setString(2, domain.getDomain());
            ps.executeUpdate();
        } catch (SQLException sqle) {
             logger.error("Exception while inserting data into domain table. " + sqle.toString());
        }
        catch (Exception e) {
             logger.error("Exception while inserting data into domain table. " + e.toString());
        }
    }


}
