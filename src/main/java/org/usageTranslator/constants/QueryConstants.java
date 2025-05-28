package org.usageTranslator.constants;

public class QueryConstants {
    private QueryConstants() {
        throw new UnsupportedOperationException("Constants file cannot be initiated.");
    }

    public static final String DROP_CHARGEABLE_TABLE = "DROP TABLE IF EXISTS chargeable";

    public static final String CREATE_CHARGEABLE_TABLE =
            "CREATE TABLE chargeable (\n" +
            "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
            "    partnerID INT,\n" +
            "    product VARCHAR(255),\n" +
            "    partnerPurchasedPlanID VARCHAR(255),\n" +
            "    plan VARCHAR(255),\n" +
            "    `usage` INT\n" +
            ");";

    public static final String INSERT_CHARGEABLE_QUERY =
            "INSERT INTO chargeable (partnerID, product, partnerPurchasedPlanID, plan, `usage`) " +
            "VALUES (?, ?, ?, ?, ?)";

    public static final String SELECT_CHARGEABLE_QUERY =
            "SELECT * FROM chargeable";



    public static final String DROP_DOMAIN_TABLE = "DROP TABLE IF EXISTS domain";

    public static final String CREATE_DOMAIN_TABLE = "CREATE TABLE domain ("
            +   " id INT AUTO_INCREMENT PRIMARY KEY, partnerPurchasedPlanID VARCHAR(255), domain VARCHAR(255)"
            +   " )";

    public static final String INSERT_DOMAIN_QUERY =
            "INSERT INTO domain (partnerPurchasedPlanID, domain) " +
                    "VALUES (?,?)";
    public static final String SELECT_DOMAIN_QUERY =
            "SELECT * FROM domain";
}
