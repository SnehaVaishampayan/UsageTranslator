package org.usageTranslator.constants;

//This class holds all the information about the input files and the Database configuration
public class ConfigConstants {

    // Prevent instantiation
    private ConfigConstants() {
        throw new UnsupportedOperationException("Constants file cannot be initiated.");
    }

    // File paths
    public static final String INPUT_CSV_FILE_PATH = "inputData/Sample_Report.csv";
    public static final String INPUT_JSON_FILE_PATH = "inputData/typemap.json";

    // Database configuration
    public static final String DB_URL = "jdbc:mysql://localhost:3306/MySQL";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "rootadmin";
}
