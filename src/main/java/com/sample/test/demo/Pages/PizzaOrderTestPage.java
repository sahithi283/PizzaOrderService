package com.sample.test.demo.Pages;

import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;
import com.sample.test.demo.utilities.WebDriverUtilClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PizzaOrderTestPage {

    private final PizzaOrderPage pizzaOrderPage;
    private WebElement webElement;
    private final WebDriver webDriver;

    public PizzaOrderTestPage(WebDriver webDriver, PizzaOrderPage pizzaOrderPage) {
        this.webDriver = webDriver;
        this.pizzaOrderPage = pizzaOrderPage;
    }

    public boolean isToppingsEnabledAsPerPizzaTypeSelection(PizzaTypes pizzaType) {
        WebElement pizzaToppings1DropdownElement = pizzaOrderPage.getWebElement("pizza1Toppings1");
        WebElement pizzaToppings2DropdownElement = pizzaOrderPage.getWebElement("pizza1Toppings2");
        if (pizzaType.getDisplayName().contains("no toppings")) {
            return !isPizzaToppings1AndToppings2Enabled(pizzaToppings1DropdownElement, pizzaToppings2DropdownElement);
        } else if (pizzaType.getDisplayName().contains("1 topping")) {
            return isPizzaTopping1EnabledAndTopping2Disabled(pizzaToppings1DropdownElement, pizzaToppings2DropdownElement);
        }
        return isPizzaToppings1AndToppings2Enabled(pizzaToppings1DropdownElement, pizzaToppings2DropdownElement);
    }

    private boolean isPizzaToppings1AndToppings2Enabled(WebElement pizzaToppings1DropdownElement, WebElement pizzaToppings2DropdownElement) {
        return pizzaToppings1DropdownElement.isEnabled() && pizzaToppings2DropdownElement.isEnabled();
    }

    private boolean isPizzaTopping1EnabledAndTopping2Disabled(WebElement pizzaToppings1DropdownElement, WebElement pizzaToppings2DropdownElement) {
        return pizzaToppings1DropdownElement.isEnabled() && !pizzaToppings2DropdownElement.isEnabled();
    }

    public boolean isPizzaTypeSelected(PizzaTypes pizzaType) {
        return WebDriverUtilClass.verifySelectedOption(pizzaOrderPage.getWebElement("pizza1"), pizzaType.getDisplayName());
    }

    public boolean isPizzaToppingSelected(String pizzaToppingType, PizzaToppings pizzaToppings) {
        WebElement pizzaToppingsDropdownElement = pizzaOrderPage.getWebElement(pizzaToppingType);
        return WebDriverUtilClass.verifySelectedOption(pizzaToppingsDropdownElement, pizzaToppings.getDisplayName());
    }

    public boolean isPaymentModeRadioButtonElementSelected() {
        return WebDriverUtilClass.isWebElementSelected(pizzaOrderPage.getPaymentModeRadioButtonElement());
    }

    public boolean isButtonDisplayed(String buttonName) {
        webElement = pizzaOrderPage.getWebElement(buttonName);
        return WebDriverUtilClass.isElementDisplayed(webElement);
    }

    public boolean isElementsReset() {
        boolean isPersonalInformationReset = isPersonalInformationReset();
        boolean isPizzaOrderDropdownsReset = isPizzaOrderDropdownsReset();
        boolean isPaymentInfoReset = !isPaymentModeRadioButtonElementSelected();
        return isPersonalInformationReset && isPizzaOrderDropdownsReset && isPaymentInfoReset;
    }

    private boolean isPizzaOrderDropdownsReset() {
        return WebDriverUtilClass.verifySelectedOption(pizzaOrderPage.getWebElement("pizza1"), "Choose Pizza")
                && WebDriverUtilClass.verifySelectedOption(pizzaOrderPage.getWebElement("pizza1Toppings1"), "Choose a Toppings 1")
                && WebDriverUtilClass.verifySelectedOption(pizzaOrderPage.getWebElement("pizza1Toppings2"), "Choose a Toppings 2");
    }

    private boolean isPersonalInformationReset() {
        WebElement nameInputBoxElement = pizzaOrderPage.getWebElement("name");
        WebElement emailInputBoxElement = pizzaOrderPage.getWebElement("email");
        WebElement phoneNumberInputBoxElement = pizzaOrderPage.getWebElement("email");
        return (nameInputBoxElement.getText().isEmpty()) && (emailInputBoxElement.getText().isEmpty()) && (phoneNumberInputBoxElement.getText().isEmpty());
    }

    public boolean isSuccessMessageDisplayed() {
        webElement = pizzaOrderPage.getWebElement("dialogText");
        return WebDriverUtilClass.isElementDisplayed(webElement) && webElement.getText().contains("Thank you for your order!");
    }

    public boolean isErrorMessageDisplayed(String errorMessage) {
        webElement = pizzaOrderPage.getWebElement("dialogText");
        return WebDriverUtilClass.isElementDisplayed(webElement) && webElement.getText().contains(errorMessage);
    }

    public boolean isSuccessMessageDisplayedCorrectly(PizzaTypes pizzaType, Double costOfPizzaOrder) {
        String actualSuccessMessage = WebDriverUtilClass.getElementText(pizzaOrderPage.getWebElement("dialogText"));
        String expectedSuccessMessage = costOfPizzaOrder + " " + pizzaType.getDisplayName();
        return actualSuccessMessage.contains(expectedSuccessMessage);
    }

    public boolean isTitleOfPizzaOrderPageDisplayedCorrectly(String expectedTitle) {
        return webDriver.getTitle().contains(expectedTitle);
    }

    public boolean isTitleDisplayedCorrectly(String webElementLocator, String expectedTitle) {
        return pizzaOrderPage.getWebElement(webElementLocator).getText().trim().contains(expectedTitle);
    }
}
