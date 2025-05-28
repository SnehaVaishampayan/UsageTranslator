package org.usageTranslator.util;
import com.opencsv.CSVReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usageTranslator.constants.ConfigConstants;
import java.io.*;
import java.util.List;

//  Responsible for the reading the input CSV data file.
public class InputCSVReaderUtil implements IDataReader {
    public static final Logger logger = LogManager.getLogger("InputCSVReaderUtil.class");

    List<String[]> csvData;
    public List<String[]> readData() {
         logger.debug("InputCSVReaderUtil: readCSVFile");

        try {
            String filePath = ConfigConstants.INPUT_CSV_FILE_PATH;
            if(filePath == null || filePath.length() == 0) {
                throw new FileNotFoundException("Please provide CSV file path");
            }
            InputStream inputStream = InputCSVReaderUtil.class.getClassLoader().getResourceAsStream(filePath);
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            String[] headers = reader.readNext();
            if (headers == null) {
                throw new NullPointerException("CSV is empty");
            }
            this.csvData = reader.readAll();
        } catch (NullPointerException npe) {
             logger.error("Exception while reading CSV data. " + npe.toString());
            throw new NullPointerException();
        } catch (FileNotFoundException fnfe) {
             logger.error("Exception while reading CSV data. " + fnfe.toString());
            throw new RuntimeException(fnfe);
        } catch (Exception e) {
             logger.error("Exception while reading CSV data. " + e.toString());
        }
        return csvData;
    }
}
