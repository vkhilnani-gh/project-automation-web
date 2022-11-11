package com.company.coreortest.web.ui.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Config Manager to read properties file.
 */
public class ConfigManager {

    /**
     * Instance of config manager.
     */
    private static final Logger logger = LogManager.getLogger("ConfigManager");
    private static ConfigManager manager;
    private static final Properties properties = new Properties();

    /**
     * private constructor where config file stream is loaded/read .
     */
    private ConfigManager() {

        try {

            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream("config.properties");
            properties.load(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get ConfigManager Instance
     */
    public static ConfigManager getInstance() {

        if (manager == null) {
            synchronized (ConfigManager.class) {
                manager = new ConfigManager();
            }
        }
        return manager;
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);

        if (value == null || value.isEmpty()) {
            //value = System.getProperty(key);
            //local system
             value = System.getenv(key);
            logger.info(value);
        }
        return value;
    }
}
