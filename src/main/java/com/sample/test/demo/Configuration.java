package com.sample.test.demo;

import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static final String CONFIG_FILE_NAME = "config.properties";
    private Properties configProperties;


    public Configuration() {
        loadProperties();
    }

    private void loadProperties() {
        configProperties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assertTrue(classLoader != null);
        InputStream inputStream = classLoader.getResourceAsStream(CONFIG_FILE_NAME);
        try {
            configProperties.load(inputStream);
        } catch (final IOException e) {
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

    public String getName() {
        return getProperty("name");
    }

    public String getPhone() {
        return getProperty("phone");
    }

    public String getEmail() {
        return getProperty("email");
    }

    public String getPaymentMode1() {
        return getProperty("paymentModeByCreditCard");
    }

    public String getPaymentMode2() {
        return getProperty("paymentModeByCashOnPickup");
    }

    public PizzaTypes getPizzaTypeWithTwoToppings() {
        return PizzaTypes.valueOf(getProperty("twoToppingsPizza"));
    }

    public PizzaTypes getPizzaTypeWithOneTopping() {
        return PizzaTypes.valueOf(getProperty("oneToppingPizza"));
    }

    public PizzaTypes getPizzaTypeWithNoToppings() {
        return PizzaTypes.valueOf(getProperty("noToppingsPizza"));
    }

    public PizzaToppings getPizzaToppings1() {
        return PizzaToppings.fromString(getProperty("toppings1"));
    }

    public PizzaToppings getPizzaToppings2() {
        return PizzaToppings.fromString(getProperty("toppings2"));
    }

    public int getNegativeQuantity() {
        return Integer.parseInt(getProperty("negativeQuantity"));
    }

    public int getQuantity() {
        return Integer.parseInt(getProperty("quantity"));
    }

    public int getMaximumQuantity() {
        return Integer.parseInt(getProperty("maximumQuantity"));
    }

    public int getVeryLargeQuantity() {
        return Integer.parseInt(getProperty("veryLargeQuantityValue"));
    }

    public String getInvalidPhoneNumber() {
        return getProperty("invalidPhoneNumber");
    }

    public String getInvalidEmail() {
        return getProperty("invalidEmail");
    }

    public String getProperty(String propertyName) {
        return configProperties.getProperty(propertyName);
    }
}
