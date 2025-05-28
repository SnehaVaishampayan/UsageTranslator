package org.usageTranslator.constants;

import java.util.*;

//  This class holds all the information about the column data and validation constraints data of the input CSV file
public class CSVDataConstants {

    // Prevent instantiation
    private CSVDataConstants() {
        throw new UnsupportedOperationException("Constants file cannot be initiated.");
    }

    //  Defines the column numbers in the input CSV
    public static final int PARTNERID_COLUMN = 0;
    public static final int PARTNERGUID = 1;
    public static final int ACCOUNTID_COLUMN = 2;
    public static final int ACCOUNTGUID_COLUMN = 3;
    public static final int USERNAME_COLUMN = 4;
    public static final int DOMAINS_COLUMN = 5;
    public static final int ITEMNAME_COLUMN = 6;
    public static final int PLAN_COLUMN = 7;
    public static final int ITEMTYPE_COLUMN = 8;
    public static final int PARTNUMBER_COLUMN = 9;
    public static final int ITEMCOUNT_COLUMN = 10;

    //  Defines the data used for the validation constraints of the input CSV
    public static final List<String> PARTNERID_CONFIGURABLE_LIST = List.of("26392");
    public static final Map<String, Integer> UNIT_REDUCTION_RULE = Map.of(
            "EA000001GB0O", 1000,
            "PMQ00005GB0R", 5000,
            "SSX006NR", 1000,
            "SPQ00001MB0R",2000
    );

}
