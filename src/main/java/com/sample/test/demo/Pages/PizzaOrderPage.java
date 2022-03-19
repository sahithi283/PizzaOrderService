package com.sample.test.demo.Pages;

import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;
import com.sample.test.demo.utilities.ReadDataUtilityClass;
import com.sample.test.demo.utilities.WebDriverUtilClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class PizzaOrderPage {
    private final WebDriver webDriver;
    private final Map<String, By> locatorsMap;
    private WebElement webElement;
    private static final Logger logger = LoggerFactory.getLogger(PizzaOrderPage.class);
    private WebElement inputBoxElement;
    private WebElement quantityInputBoxElement;
    private WebElement paymentModeRadioButtonElement;

    public PizzaOrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        locatorsMap = ReadDataUtilityClass.getLocatorsMap();
    }

    public void choosePizza(PizzaTypes pizzaType) {
        WebElement pizzaTypeDropdownElement = getWebElement("pizza1");
        logger.info("pizza type is choosen");
        WebDriverUtilClass.selectAValueFromDropdown(pizzaTypeDropdownElement, pizzaType.getDisplayName());
    }

    public void choosePizzaToppings(String pizzaToppingType, PizzaToppings pizzaToppings) {
        WebElement pizzaToppingsDropdownElement = getWebElement(pizzaToppingType);
        logger.info("pizza topping is selected");
        WebDriverUtilClass.selectAValueFromDropdown(pizzaToppingsDropdownElement, pizzaToppings.getDisplayName());
    }

    public void enterQuantity(int quantityNumber) {
        quantityInputBoxElement = getWebElement("pizza1Quantity");
        WebDriverUtilClass.enterText(quantityInputBoxElement, String.valueOf(quantityNumber));
    }

    public int getQuantityEntered() {
        return Integer.parseInt(quantityInputBoxElement.getAttribute("value"));
    }

    public double getCostDisplayed() {
        String costOfPizzaOrdered = WebDriverUtilClass.getElementValue(getWebElement("pizza1Cost"));
        return Double.parseDouble(costOfPizzaOrdered);
    }

    public int getQuantityData() {
        return Integer.parseInt(WebDriverUtilClass.getElementValue(webDriver.findElement(locatorsMap.get("pizza1Quantity"))));
    }

    public void enterTextInTheInputField(String fieldName, String fieldValue) {
        inputBoxElement = getWebElement(fieldName);
        WebDriverUtilClass.enterText(inputBoxElement, fieldValue);
    }

    public String getTextEnteredInTheInputField() {
        return inputBoxElement.getAttribute("value");
    }

    public WebElement getWebElement(String locator) {
        By by = locatorsMap.get(locator);
        webElement = webDriver.findElement(by);
        WebDriverUtilClass.waitTillElementIsVisible(by);
        return webElement;
    }

    public void selectPaymentModeRadioButtonElement(String locator) {
        paymentModeRadioButtonElement = getWebElement(locator);
        WebDriverUtilClass.scrollToTheWebElement(paymentModeRadioButtonElement);
        WebDriverUtilClass.clickOnButtonUsingJSExecutor(paymentModeRadioButtonElement);
    }

    public WebElement getPaymentModeRadioButtonElement() {
        return paymentModeRadioButtonElement;
    }

    public void selectPaymentMethod(String paymentMethod) {
        WebDriverUtilClass.scrollDownTheWebPage();
        if (isPaymentModeCreditCard(paymentMethod)) {
            selectPaymentModeRadioButtonElement("radioCreditCard");
        } else {
            selectPaymentModeRadioButtonElement("radioCash");
        }
    }

    public boolean isPaymentModeCreditCard(String paymentMethod) {
        return paymentMethod.equalsIgnoreCase("Credit Card");
    }

    public void clickOnPlaceOrderButton() {
        webElement = getWebElement("placeOrderButton");
        WebDriverUtilClass.clickOnButtonUsingJSExecutor(webElement);
    }

    public void clickOnResetButton() {
        webElement = getWebElement("resetButton");
        WebDriverUtilClass.clickOnButtonUsingJSExecutor(webElement);
    }
}
