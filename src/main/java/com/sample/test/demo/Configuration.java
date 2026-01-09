package com.sample.test.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static final String CONFIG_FILE_NAME = "config.properties";
    private Properties configProperties;
    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

    public Configuration() {
        loadProperties();
    }

    private void loadProperties() {
        configProperties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assertNotNull(classLoader);
        InputStream inputStream = classLoader.getResourceAsStream(CONFIG_FILE_NAME);
        try {
            configProperties.load(inputStream);
        } catch (final IOException ioException) {
            logger.info("Error loading configuration properties: {}", ioException.getMessage());
        }
    }

    public String getBrowser() {
        return getProperty("browser");
    }

    public String getPlatform() {
        return getProperty("platform");
    }

    public String getUrl() {
        return getProperty("url");
    }

    public String getProperty(String propertyName) {
        return configProperties.getProperty(propertyName);
    }
}
