package org.usageTranslator.dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usageTranslator.model.Chargeable;
import java.sql.*;
import static org.usageTranslator.constants.QueryConstants.*;

//  This class is responsible for manipulating the Chargeable table in db
public class ChargeableDao {
    public static final Logger logger = LogManager.getLogger("ChargeableDao.class");
    private Connection conn;
    public ChargeableDao(Connection conn) {
        this.conn = conn;
    }
    public void createTable() throws SQLException {
          logger.debug("ChargeableDao: createTable");
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(DROP_CHARGEABLE_TABLE);
            stmt.execute(CREATE_CHARGEABLE_TABLE);
        } catch (SQLException sqle) {
             logger.error("Exception while creating chargeable table" + sqle.toString());
            throw sqle;
        } catch (Exception e) {
             logger.error("Exception while creating chargeable table" + e.toString());
            throw e;
        }
    }

    public Chargeable insertChargeableData(Chargeable chargeable) throws SQLException {
          logger.debug("ChargeableDao: insertChargeableData");

        try {
            if(chargeable == null){
                throw new NullPointerException("Provided data is empty.");
            }
            PreparedStatement ps = conn.prepareStatement(INSERT_CHARGEABLE_QUERY);
            ps.setInt(1, chargeable.getPartnerID());
            ps.setString(2, chargeable.getProduct());
            ps.setString(3, chargeable.getPartnerPurchasedPlanID());
            ps.setString(4, chargeable.getPlan());
            ps.setInt(5, chargeable.getUsage());
            ps.executeUpdate();
            return chargeable;
        } catch (SQLException sqle) {
             logger.error("Exception while inserting in chargeable table" + sqle.toString());
            throw sqle;
        }catch (NullPointerException npe) {
             logger.error("Exception while inserting in chargeable table" + npe.toString());
            throw npe;
        }
        catch (Exception e) {
             logger.error("Exception while inserting in chargeable table" + e.toString());
            throw e;
        }
    }
}
