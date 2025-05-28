package org.usageTranslator.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usageTranslator.constants.ConfigConstants;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class InputJSONReaderUtil implements IDataReader {
    public static final Logger logger = LogManager.getLogger("InputJSONReaderUtil.class");

    Map<String,String> partNumberProductMapper;

    public Map<String,String> readData() throws Exception{
         logger.debug("InputJSONReaderUtil: readJSONFile");
        try {
            String filePath = ConfigConstants.INPUT_JSON_FILE_PATH;
            if(filePath == null || filePath.length() == 0) {
                throw new FileNotFoundException("Please provide JSON file path");
            }
            InputStream inputStream = InputJSONReaderUtil.class.getClassLoader().getResourceAsStream(filePath);
            ObjectMapper mapper = new ObjectMapper();
            partNumberProductMapper = mapper.readValue(inputStream, new TypeReference<HashMap<String,String>>() {});
        } catch (IOException ioe) {
             logger.error("Exception while reading CSV data. " + ioe.toString());
            throw ioe;
        }
        catch (Exception e) {
             logger.error("Exception while reading CSV data. " + e.toString());
            throw e;
        }
        return partNumberProductMapper;
    }
}