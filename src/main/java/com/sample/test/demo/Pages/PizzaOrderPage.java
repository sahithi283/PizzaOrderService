package com.sample.test.demo.Pages;

import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;
import com.sample.test.demo.utilities.LogManagerUtilClass;
import com.sample.test.demo.utilities.ReadDataUtilClass;
import com.sample.test.demo.utilities.WebDriverUtilClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class PizzaOrderPage {
    private final WebDriver webDriver;
    private final Map<String, By> locatorsMap;
    private WebElement pizzaTypeDropDownElement;
    private WebElement pizzaToppings1DropDownElement;
    private WebElement pizzaToppings2DropDownElement;
    private WebElement quantityInputBoxElement;
    private WebElement radioButton;
    private WebElement webElement;
    private WebElement phoneNumberInputBoxElement;
    private WebElement emailInputBoxElement;
    private WebElement nameInputBoxElement;
    private String costOfPizzaOrdered;
    Logger logger = LogManagerUtilClass.getInstance();

    public PizzaOrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        locatorsMap = ReadDataUtilClass.getLocatorsMap();
        WebDriverUtilClass utilClass = new WebDriverUtilClass(webDriver);
    }

    public void choosePizza(PizzaTypes pizzaType) {
        pizzaTypeDropDownElement = getWebElement("pizza1");
        logger.info("pizza type is choosen");
        WebDriverUtilClass.selectAValueFromDropDown(pizzaTypeDropDownElement, pizzaType.getDisplayName());
    }

    public void chooseToppings1(PizzaToppings pizzaToppings) {
        pizzaToppings1DropDownElement = getWebElement("pizza1Toppings1");
        logger.info("pizza toppings 1 is selected");
        WebDriverUtilClass.selectAValueFromDropDown(pizzaToppings1DropDownElement, pizzaToppings.getDisplayName());
    }

    public void chooseToppings2(PizzaToppings pizzaToppings) {
        pizzaToppings2DropDownElement = getWebElement("pizza1Toppings2");
        logger.info("pizza toppings 2 is selected");
        WebDriverUtilClass.selectAValueFromDropDown(pizzaToppings2DropDownElement, pizzaToppings.getDisplayName());
    }

    public boolean isToppingsEnabledAsPerPizzaTypeSelection(PizzaTypes pizzaType) {
        pizzaToppings1DropDownElement = getWebElement("pizza1Toppings1");
        pizzaToppings2DropDownElement = getWebElement("pizza1Toppings2");

        if (pizzaType.getDisplayName().contains("no toppings")) {
            return !pizzaToppings1DropDownElement.isEnabled() && !pizzaToppings2DropDownElement.isEnabled();
        } else if (pizzaType.getDisplayName().contains("1 topping")) {
            return pizzaToppings1DropDownElement.isEnabled() && !pizzaToppings2DropDownElement.isEnabled();
        }
        return pizzaToppings1DropDownElement.isEnabled() && pizzaToppings2DropDownElement.isEnabled();
    }

    public boolean isPizzaTypeSelected(PizzaTypes pizzaType) {
        return WebDriverUtilClass.verifySelectedOption(pizzaTypeDropDownElement, pizzaType.getDisplayName());
    }

    public boolean isPizzaTopping1Selected(PizzaToppings pizzaToppings) {
        return WebDriverUtilClass.verifySelectedOption(pizzaToppings1DropDownElement, pizzaToppings.getDisplayName());
    }

    public boolean isPizzaTopping2Selected(PizzaToppings pizzaToppings) {
        return WebDriverUtilClass.verifySelectedOption(pizzaToppings2DropDownElement, pizzaToppings.getDisplayName());
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
        costOfPizzaOrdered = WebDriverUtilClass.getElementValue(getWebElement("pizza1Cost"));
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

    public void selectRadioButton(String locator) {
        radioButton = getWebElement(locator);
        WebDriverUtilClass.scrollToTheWebElement(radioButton);
        WebDriverUtilClass.clickOnButtonUsingJSExecutor(radioButton);
    }

    public void selectPaymentMethod(String paymentMethod) {
        WebDriverUtilClass.scrollDownTheWebPage();
        if (paymentMethod.equalsIgnoreCase("Credit Card")) {
            selectRadioButton("radioCreditCard");
        } else {
            selectRadioButton("radioCash");
        }
    }

    public boolean isRadioButtonSelected() {
        return WebDriverUtilClass.isWebElementSelected(radioButton);
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
        IsElementsResetted();
    }

    public boolean IsElementsResetted() {
        boolean isPersonalInformationResetted = (nameInputBoxElement.getText().isEmpty()) && (emailInputBoxElement.getText().isEmpty())
                && (phoneNumberInputBoxElement.getText().isEmpty());
        boolean isPizzaOrderDropdownsResetted = WebDriverUtilClass.verifySelectedOption(pizzaTypeDropDownElement, "Choose Pizza")
                && WebDriverUtilClass.verifySelectedOption(pizzaToppings1DropDownElement, "Choose a Toppings 1") && WebDriverUtilClass.verifySelectedOption(pizzaToppings2DropDownElement, "Choose a Toppings 2");
        boolean isPaymentInfoResetted = !isRadioButtonSelected();
        return isPersonalInformationResetted && isPizzaOrderDropdownsResetted && isPaymentInfoResetted;
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
