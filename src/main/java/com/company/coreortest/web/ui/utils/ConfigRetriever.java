package com.company.coreortest.web.ui.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.coreortest.web.ui.exception.AutomationException;

/**
 * Fetches config from system property, environment variables and config file/.properties file.
 */
public class ConfigRetriever {

    /**
     * Instance of config retriever.
     */
    private static final Logger logger = LogManager.getLogger("ConfigRetriever");
    private static ConfigRetriever retriever;
    private static final Properties properties = new Properties();
    private static String env;

    /**
     * private constructor
     */
    private ConfigRetriever() {
    }

    /**
     * Get ConfigRetriever Instance
     */
    public static ConfigRetriever getInstance() {

        if (retriever == null) {
            synchronized (ConfigRetriever.class) {
                retriever = new ConfigRetriever();
            }
        }
        return retriever;
    }

    /**
     * Method to retrieve the value (from a key-value pair) from system property, environment variable
     * or config.properties (stored in /test/resources)
     *
     * @param key as String
     * @return String - the key value
     */
    public String getProperty(String key) throws AutomationException {
        String keyValue = System.getProperty(key);
        if (keyValue == null || keyValue.isEmpty()) {
            keyValue = System.getenv(key);
        }
        if (keyValue == null || keyValue.isEmpty()) {
            try {
                keyValue = loadProperties().getProperty(key);
            } catch (Exception e) {
                throw new AutomationException((String.format("Config value for %s cannot be retrieved. Ensure that it " +
                        "is either supplied as system property or environment variable or defined in the .properties " +
                        "file in the /resources folder.", key)));
            }
        }
        return keyValue;
    }

    private Properties loadProperties() throws IOException {
        String configFileName;
        String envVariable = "envName";
        if (StringUtils.isNotBlank(System.getProperty(envVariable)) || StringUtils.isNotBlank(System.getenv(envVariable))) {
            if (StringUtils.isNotBlank(System.getenv(envVariable))) {
                env = System.getenv(envVariable);
            }

            if (StringUtils.isNotBlank(System.getProperty(envVariable))) {
                env = System.getenv(envVariable);
            }
            configFileName = "config_" + env + ".properties";
        } else {
            configFileName = "config.properties";
        }

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);
            properties.load(inputStream);
        } catch (IOException e) {
            throw new AutomationException(String.format("Unable to retrieve config values from %s ", configFileName));
        }
        return properties;
    }
}
