package com.sample.test.demo.Pages;

import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;
import com.sample.test.demo.utilities.LogManagerUtilClass;
import com.sample.test.demo.utilities.ReadDataUtilityClass;
import com.sample.test.demo.utilities.WebDriverUtilClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class PizzaOrderPage {
    private final WebDriver webDriver;
    private final Map<String, By> locatorsMap;
    private WebElement pizzaTypeDropdownElement;
    private WebElement pizzaToppings1DropdownElement;
    private WebElement pizzaToppings2DropdownElement;
    private WebElement quantityInputBoxElement;
    private WebElement paymentModeRadioButtonElement;
    private WebElement webElement;
    private WebElement phoneNumberInputBoxElement;
    private WebElement emailInputBoxElement;
    private WebElement nameInputBoxElement;
    Logger logger = LogManagerUtilClass.getInstance();

    public PizzaOrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        locatorsMap = ReadDataUtilityClass.getLocatorsMap();
    }

    public void choosePizza(PizzaTypes pizzaType) {
        pizzaTypeDropdownElement = getWebElement("pizza1");
        logger.info("pizza type is choosen");
        WebDriverUtilClass.selectAValueFromDropdown(pizzaTypeDropdownElement, pizzaType.getDisplayName());
    }

    public void choosePizzaToppings1(PizzaToppings pizzaToppings) {
        pizzaToppings1DropdownElement = getWebElement("pizza1Toppings1");
        logger.info("pizza toppings 1 is selected");
        WebDriverUtilClass.selectAValueFromDropdown(pizzaToppings1DropdownElement, pizzaToppings.getDisplayName());
    }

    public void choosePizzaToppings2(PizzaToppings pizzaToppings) {
        pizzaToppings2DropdownElement = getWebElement("pizza1Toppings2");
        logger.info("pizza toppings 2 is selected");
        WebDriverUtilClass.selectAValueFromDropdown(pizzaToppings2DropdownElement, pizzaToppings.getDisplayName());
    }

    public boolean isToppingsEnabledAsPerPizzaTypeSelection(PizzaTypes pizzaType) {
        pizzaToppings1DropdownElement = getWebElement("pizza1Toppings1");
        pizzaToppings2DropdownElement = getWebElement("pizza1Toppings2");
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
        return WebDriverUtilClass.verifySelectedOption(pizzaTypeDropdownElement, pizzaType.getDisplayName());
    }

    public boolean isPizzaTopping1Selected(PizzaToppings pizzaToppings) {
        return WebDriverUtilClass.verifySelectedOption(pizzaToppings1DropdownElement, pizzaToppings.getDisplayName());
    }

    public boolean isPizzaTopping2Selected(PizzaToppings pizzaToppings) {
        return WebDriverUtilClass.verifySelectedOption(pizzaToppings2DropdownElement, pizzaToppings.getDisplayName());
    }

    public void enterQuantity(int quantityNumber) {
        quantityInputBoxElement = getWebElement("pizza1Quantity");
        WebDriverUtilClass.enterText(quantityInputBoxElement, String.valueOf(quantityNumber));
    }

    public boolean isQuantityEnteredCorrectly(int textEntered) {
        return WebDriverUtilClass.verifyTextEntered(quantityInputBoxElement, String.valueOf(textEntered));
    }

    public boolean isUserNameEnteredCorrectly(String textEntered) {
        return WebDriverUtilClass.verifyTextEntered(nameInputBoxElement, textEntered);
    }

    public boolean isUserEmailEnteredCorrectly(String textEntered) {
        return WebDriverUtilClass.verifyTextEntered(emailInputBoxElement, textEntered);
    }

    public boolean isUserPhoneNumberEnteredCorrectly(String textEntered) {
        return WebDriverUtilClass.verifyTextEntered(phoneNumberInputBoxElement, textEntered);
    }

    public Double getCostDisplayed() {
        String costOfPizzaOrdered = WebDriverUtilClass.getElementValue(getWebElement("pizza1Cost"));
        return Double.parseDouble(costOfPizzaOrdered);
    }

    public int getQuantityData() {
        return Integer.parseInt(WebDriverUtilClass.getElementValue(webDriver.findElement(locatorsMap.get("pizza1Quantity"))));
    }

    public void enterUserName(String userName) {
        nameInputBoxElement = getWebElement("name");
        WebDriverUtilClass.enterText(nameInputBoxElement, userName);
    }

    public void enterUserEmail(String userEmail) {
        emailInputBoxElement = getWebElement("email");
        WebDriverUtilClass.enterText(emailInputBoxElement, userEmail);
    }

    public void enterUserPhoneNumber(String userPhoneNumber) {
        phoneNumberInputBoxElement = getWebElement("phone");
        WebDriverUtilClass.enterText(phoneNumberInputBoxElement, userPhoneNumber);
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

    public void selectPaymentMethod(String paymentMethod) {
        WebDriverUtilClass.scrollDownTheWebPage();
        if (isPaymentModeCreditCard(paymentMethod)) {
            selectPaymentModeRadioButtonElement("radioCreditCard");
        } else {
            selectPaymentModeRadioButtonElement("radioCash");
        }
    }

    private boolean isPaymentModeCreditCard(String paymentMethod) {
        return paymentMethod.equalsIgnoreCase("Credit Card");
    }

    public boolean isPaymentModeRadioButtonElementSelected() {
        return WebDriverUtilClass.isWebElementSelected(paymentModeRadioButtonElement);
    }

    public boolean isButtonDisplayed(String buttonName) {
        webElement = getWebElement(buttonName);
        return WebDriverUtilClass.isElementDisplayed(webElement);
    }

    public void clickOnPlaceOrderButton() {
        webElement = getWebElement("placeOrderButton");
        WebDriverUtilClass.clickOnButtonUsingJSExecutor(webElement);
    }

    public void clickOnResetButton() {
        webElement = getWebElement("resetButton");
        WebDriverUtilClass.clickOnButtonUsingJSExecutor(webElement);
        isElementsReset();
    }

    public boolean isElementsReset() {
        boolean isPersonalInformationReset = isPersonalInformationReset();
        boolean isPizzaOrderDropdownsReset = isPizzaOrderDropdownsReset();
        boolean isPaymentInfoReset = !isPaymentModeRadioButtonElementSelected();
        return isPersonalInformationReset && isPizzaOrderDropdownsReset && isPaymentInfoReset;
    }

    private boolean isPizzaOrderDropdownsReset() {
        return WebDriverUtilClass.verifySelectedOption(pizzaTypeDropdownElement, "Choose Pizza")
                && WebDriverUtilClass.verifySelectedOption(pizzaToppings1DropdownElement, "Choose a Toppings 1")
                && WebDriverUtilClass.verifySelectedOption(pizzaToppings2DropdownElement, "Choose a Toppings 2");
    }

    private boolean isPersonalInformationReset() {
        return (nameInputBoxElement.getText().isEmpty()) && (emailInputBoxElement.getText().isEmpty()) && (phoneNumberInputBoxElement.getText().isEmpty());
    }

    public boolean isSuccessMessageDisplayed() {
        webElement = getWebElement("dialogText");
        return WebDriverUtilClass.isElementDisplayed(webElement) && webElement.getText().contains("Thank you for your order!");
    }

    public boolean isErrorMessageDisplayed(String errorMessage) {
        webElement = getWebElement("dialogText");
        return WebDriverUtilClass.isElementDisplayed(webElement) && webElement.getText().contains(errorMessage);
    }

    public boolean isSuccessMessageDisplayedCorrectly(PizzaTypes pizzaType, Double costOfPizzaOrder) {
        String actualSuccessMessage = WebDriverUtilClass.getElementText(getWebElement("dialogText"));
        String expectedSuccessMessage = costOfPizzaOrder + " " + pizzaType.getDisplayName();
        return actualSuccessMessage.contains(expectedSuccessMessage);
    }

    public boolean isTitleOfPizzaOrderFormDisplayedCorrectly(String expectedTitle) {
        return getWebElement("orderFormTitle").getText().contains(expectedTitle);
    }

    public boolean isTitleOfPizzaOrderPageDisplayedCorrectly(String expectedTitle) {
        return webDriver.getTitle().contains(expectedTitle);
    }

    public boolean isTitleOfPaymentInfoDisplayedCorrectly(String expectedTitle) {
        return getWebElement("paymentInfoTitle").getText().trim().contains(expectedTitle);
    }

    public boolean isTitleOfPickupInfoDisplayedCorrectly(String expectedTitle) {
        return getWebElement("pickupInfoTitle").getText().trim().contains(expectedTitle);
    }
}
