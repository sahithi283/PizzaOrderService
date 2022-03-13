package com.sample.test.demo.tests;

import com.relevantcodes.extentreports.LogStatus;
import com.sample.test.demo.Pages.PizzaOrderPage;
import com.sample.test.demo.Pages.PizzaOrderTestPage;
import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;
import com.sample.test.demo.utilities.DataProviderClass;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PizzaOrderPageTest extends TestBase {

    @Test
    public void verifyTitleOfPizzaOrderPage() {
        test = extent.startTest("Verify title of the pizza order page");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        Assert.assertTrue(pizzaOrderTestPage.isTitleOfPizzaOrderPageDisplayedCorrectly("Pizza Order Form"));
        test.log(LogStatus.PASS, "Pizza order page title is verified");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation"}, dataProvider = "titles")
    public void verifyTitleOfTheForms(String webElementLocator, String expectedTitle) {
        test = extent.startTest("Verify title of the form");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        Assert.assertTrue(pizzaOrderTestPage.isTitleDisplayedCorrectly(webElementLocator, expectedTitle));
        test.log(LogStatus.PASS, "Test Passed and the title is verified");
    }

    @DataProvider(name = "titles")
    public Object[][] getTitles() {
        return new Object[][]{{"orderFormTitle", "Pizza Order Form"}, {"paymentInfoTitle", "PAYMENT INFORMATION"}, {"pickupInfoTitle", "PICKUP INFORMATION"}};
    }

    @Test(groups = {"pizza order form"}, dataProvider = "pizzaTypeProvider", dataProviderClass = DataProviderClass.class)
    public void isPizzaTypeSelected(PizzaTypes pizzaType) {
        test = extent.startTest("verify if the user is able to select any of the option from the pizza type drop down");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(pizzaType);
        Assert.assertTrue(pizzaOrderTestPage.isPizzaTypeSelected(pizzaType));
        test.log(LogStatus.PASS, "Test Passed and user is able to select any of the option from the pizza type drop down");
    }

    @Test(groups = {"pizza order form"}, dataProvider = "pizzaToppingsProvider", dataProviderClass = DataProviderClass.class)
    public void isPizzaToppingsSelected(PizzaToppings pizzaTopping) {
        test = extent.startTest("verify if the pizza user is able to select any of the option from the pizza toppings drop down");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings1", pizzaTopping);
        Assert.assertTrue(pizzaOrderTestPage.isPizzaToppingSelected("pizza1Toppings1", pizzaTopping));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings2", pizzaTopping);
        Assert.assertTrue(pizzaOrderTestPage.isPizzaToppingSelected("pizza1Toppings2", pizzaTopping));
        test.log(LogStatus.PASS, "Test Passed and user is able to select any of the option from the pizza toppings drop down");
    }

    @Test(groups = {"pizza order form"})
    public void isQuantityFieldEditable() {
        test = extent.startTest("verify if the user is able to edit the quantity field");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.enterQuantity(5);
        Assert.assertTrue(pizzaOrderTestPage.isQuantityEnteredCorrectly(5));
        test.log(LogStatus.PASS, "Test Passed and user is able to edit the quantity field");
    }

    @Test(groups = {"pizza order form"}, dataProvider = "quantityMinimumValue", dataProviderClass = DataProviderClass.class)
    public void verifyQuantityFieldDoesNotAcceptOrdersLessThanZero(String pizzaType, String pizzaToppings1, String
            pizzaToppings2, int quantity, String name, String email, String phone, String paymentMode) {
        test = extent.startTest("verify if the quantity field accepts order less than zero");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(PizzaTypes.fromString(pizzaType));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings1", PizzaToppings.fromString(pizzaToppings1));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings2", PizzaToppings.fromString(pizzaToppings2));
        pizzaOrderPage.enterQuantity(quantity);
        pizzaOrderPage.enterTextInTheInputField("name", name);
        pizzaOrderPage.enterTextInTheInputField("email", email);
        pizzaOrderPage.enterTextInTheInputField("phone", phone);
        pizzaOrderPage.selectPaymentMethod(paymentMode);
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderTestPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form"}, dataProvider = "quantityMaximumValue", dataProviderClass = DataProviderClass.class)
    public void isQuantityFieldAcceptingTheMaximumValue(String pizzaType, String pizzaToppings1, String
            pizzaToppings2, int maximumQuantity, String name, String email, String phone, String paymentMode) {
        test = extent.startTest("verify if the quantity field accepts the maximum value upto length five");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(PizzaTypes.fromString(pizzaType));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings1", PizzaToppings.fromString(pizzaToppings1));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings2", PizzaToppings.fromString(pizzaToppings2));
        pizzaOrderPage.enterQuantity(maximumQuantity);
        Assert.assertTrue(pizzaOrderTestPage.isQuantityEnteredCorrectly(maximumQuantity));
        pizzaOrderPage.enterTextInTheInputField("name", name);
        pizzaOrderPage.enterTextInTheInputField("email", email);
        pizzaOrderPage.enterTextInTheInputField("phone", phone);
        pizzaOrderPage.selectPaymentMethod(paymentMode);
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderTestPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed and quantity field accepts the maximum value upto length five");
    }

    @Test(groups = {"pizza order form"})
    public void verifyQuantityFieldDoesNotAcceptVeryLargeValues() {
        test = extent.startTest("verify if the quantity field does not accept very large values");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.enterQuantity(99999999);
        Assert.assertTrue(pizzaOrderTestPage.isQuantityEnteredCorrectly(99999));
        test.log(LogStatus.PASS, "Test Passed and the quantity field does not accept very large values");
    }

    @Test(groups = {"pizza order form"}, dataProvider = "pizzaData", dataProviderClass = DataProviderClass.class)
    public void isCostDisplayedCorrectly(String pizzaTypeName, int quantity, String name) {
        test = extent.startTest("verify if the cost is calculated and displayed correctly");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaTypes pizzaType = PizzaTypes.valueOf(pizzaTypeName);
        pizzaOrderPage.choosePizza(pizzaType);
        pizzaOrderPage.enterQuantity(quantity);
        pizzaOrderPage.enterTextInTheInputField("name", name);
        double expectedCost = pizzaType.getCost() * pizzaOrderPage.getQuantityData();
        double actualCost = pizzaOrderPage.getCostDisplayed();
        Assert.assertEquals(actualCost, expectedCost);
        test.log(LogStatus.PASS, "Test Passed and the cost is calculated and displayed correctly");
    }

    @Test(groups = {"pickup information form"}, dataProvider = "inputFieldData")
    public void isInputFieldEditable(String inputField, String inputFieldValue) {
        test = extent.startTest("verify if the input field is editable or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.enterTextInTheInputField(inputField, inputFieldValue);
        Assert.assertTrue(pizzaOrderTestPage.isTextEnteredCorrectlyInTheField(inputFieldValue));
        test.log(LogStatus.PASS, "Test Passed and the input field is editable");
    }

    @DataProvider(name = "inputFieldData")
    public Object[][] getInputFieldData() {
        return new Object[][]{{"name", "user1"}, {"email", "user1@gmail.com"}, {"phone", "123456789"}};
    }

    @Test(groups = {"paymentInformation"})
    public void isPaymentInformationButtonSelectable() {
        test = extent.startTest("verify if the payment information is selectable or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.selectPaymentMethod("Cash");
        Assert.assertTrue(pizzaOrderTestPage.isPaymentModeRadioButtonElementSelected());
        test.log(LogStatus.PASS, "Test Passed and the payment information is selectable");
    }

    @Test(groups = {"placeOrder"})
    public void isPlaceOrderButtonDisplayed() {
        test = extent.startTest("verify if the place order button is displayed or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        Assert.assertTrue(pizzaOrderTestPage.isButtonDisplayed("placeOrderButton"));
        test.log(LogStatus.PASS, "Test Passed and the place order button is displayed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"}, dataProvider = "pizzaOrderData", dataProviderClass = DataProviderClass.class)
    public void isSuccessMessageDisplayed(String pizzaType, String pizzaToppings1, String
            pizzaToppings2, int quantity, String name, String email, String phone, String paymentMode) {
        test = extent.startTest("verify if the success message is displayed or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(PizzaTypes.fromString(pizzaType));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings1", PizzaToppings.fromString(pizzaToppings1));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings2", PizzaToppings.fromString(pizzaToppings2));
        pizzaOrderPage.enterQuantity(quantity);
        pizzaOrderPage.enterTextInTheInputField("name", name);
        pizzaOrderPage.enterTextInTheInputField("email", email);
        pizzaOrderPage.enterTextInTheInputField("phone", phone);
        pizzaOrderPage.selectPaymentMethod(paymentMode);
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderTestPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed and the success dialog box is displayed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"}, dataProvider = "pizzaOrderData", dataProviderClass = DataProviderClass.class)
    public void verifyUserIsAbleToPlaceOrderSuccessfully(String pizzaType, String pizzaToppings1, String
            pizzaToppings2, int quantity, String name, String email, String phone, String paymentMode) {
        test = extent.startTest("verify if the user is able to place the order successfully");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(PizzaTypes.fromString(pizzaType));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings1", PizzaToppings.fromString(pizzaToppings1));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings2", PizzaToppings.fromString(pizzaToppings2));
        pizzaOrderPage.enterQuantity(quantity);
        pizzaOrderPage.enterTextInTheInputField("name", name);
        Double costDisplayed = pizzaOrderPage.getCostDisplayed();
        pizzaOrderPage.enterTextInTheInputField("email", email);
        pizzaOrderPage.enterTextInTheInputField("phone", phone);
        pizzaOrderPage.selectPaymentMethod(paymentMode);
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderTestPage.isSuccessMessageDisplayed());
        Assert.assertTrue(pizzaOrderTestPage.isSuccessMessageDisplayedCorrectly(PizzaTypes.fromString(pizzaType), costDisplayed));
        test.log(LogStatus.PASS, "Test Passed and the user is able to place the order successfully");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"}, dataProvider = "pizzaOrderData", dataProviderClass = DataProviderClass.class)
    public void verifyUserIsAbleToSeeErrorMessageWhenNameIsNotFilled(String pizzaType, String pizzaToppings1, String
            pizzaToppings2, int quantity, String name, String email, String phone, String paymentMode) {
        test = extent.startTest("verify if the user is able to see the error message when the name field is not filled");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(PizzaTypes.fromString(pizzaType));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings1", PizzaToppings.fromString(pizzaToppings1));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings2", PizzaToppings.fromString(pizzaToppings2));
        pizzaOrderPage.enterQuantity(quantity);
        pizzaOrderPage.enterTextInTheInputField("email", email);
        pizzaOrderPage.enterTextInTheInputField("phone", phone);
        pizzaOrderPage.selectPaymentMethod(paymentMode);
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderTestPage.isErrorMessageDisplayed("Missing name"));
        test.log(LogStatus.PASS, "Test Passed and the user is able to see the error message when the name field is not filled");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"}, dataProvider = "pizzaOrderData", dataProviderClass = DataProviderClass.class)
    public void verifyUserIsAbleToSeeErrorMessageWhenPhoneNumberIsNotFilled(String pizzaType, String pizzaToppings1, String
            pizzaToppings2, int quantity, String name, String email, String phoneNumber, String paymentMode) {
        test = extent.startTest("verify if the user is able to see the error message when the phone number field is not filled");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(PizzaTypes.fromString(pizzaType));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings1", PizzaToppings.fromString(pizzaToppings1));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings2", PizzaToppings.fromString(pizzaToppings2));
        pizzaOrderPage.enterQuantity(quantity);
        pizzaOrderPage.enterTextInTheInputField("email", email);
        pizzaOrderPage.enterTextInTheInputField("name", name);
        pizzaOrderPage.selectPaymentMethod(paymentMode);
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderTestPage.isErrorMessageDisplayed("Missing phone number"));
        test.log(LogStatus.PASS, "Test Passed and the user is able to see the error message when the phone number field is not filled");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"}, dataProvider = "pizzaOrderData", dataProviderClass = DataProviderClass.class)
    public void verifyUserIsAbleToSeeErrorMessageWhenNameAndPhoneNumberIsNotFilled(String pizzaType, String pizzaToppings1, String
            pizzaToppings2, int quantity, String name, String email, String phoneNumber, String paymentMode) {
        test = extent.startTest("verify if the user is able to see the error message when the name and phone number fields are not filled");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(PizzaTypes.fromString(pizzaType));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings1", PizzaToppings.fromString(pizzaToppings1));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings2", PizzaToppings.fromString(pizzaToppings2));
        pizzaOrderPage.enterQuantity(quantity);
        pizzaOrderPage.enterTextInTheInputField("email", email);
        pizzaOrderPage.selectPaymentMethod(paymentMode);
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderTestPage.isErrorMessageDisplayed("Missing name"));
        Assert.assertTrue(pizzaOrderTestPage.isErrorMessageDisplayed("Missing phone number"));
        test.log(LogStatus.PASS, "Test Passed and the user is able to see the error message when the name and phone number fields are not filled");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder", "resetFields"}, dataProvider = "pizzaOrderData", dataProviderClass = DataProviderClass.class)
    public void verifyUserIsAbleToResetSuccessfully(String pizzaType, String pizzaToppings1, String
            pizzaToppings2, int quantity, String name, String email, String phone, String paymentMode) {
        test = extent.startTest("verify if all the field of the pizza order page are reset successfully or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(PizzaTypes.fromString(pizzaType));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings1", PizzaToppings.fromString(pizzaToppings1));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings2", PizzaToppings.fromString(pizzaToppings2));
        pizzaOrderPage.enterQuantity(quantity);
        pizzaOrderPage.enterTextInTheInputField("name", name);
        pizzaOrderPage.enterTextInTheInputField("email", email);
        pizzaOrderPage.enterTextInTheInputField("phone", phone);
        pizzaOrderPage.selectPaymentMethod(paymentMode);
        pizzaOrderPage.clickOnResetButton();
        Assert.assertTrue(pizzaOrderTestPage.isElementsReset());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(dataProvider = "pizzaTypeProvider", dataProviderClass = DataProviderClass.class)
    public void isToppingsEnabledAsPerPizzaTypeSelection(PizzaTypes pizzaType) {
        test = extent.startTest("verify if the toppings are enabled as per the pizza type selection");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(pizzaType);
        Assert.assertTrue(pizzaOrderTestPage.isToppingsEnabledAsPerPizzaTypeSelection(pizzaType));
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"}, dataProvider = "pizzaOrderDataWithInvalidName", dataProviderClass = DataProviderClass.class)
    public void verifyUserIsNotAbleToPlaceOrderWhenPhoneNoIsInvalid(String pizzaType, String pizzaToppings1, String
            pizzaToppings2, int quantity, String name, String email, String phone, String paymentMode) {
        test = extent.startTest("verify if the user is not able to place an order when phone number provided is invalid");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(PizzaTypes.fromString(pizzaType));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings1", PizzaToppings.fromString(pizzaToppings1));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings2", PizzaToppings.fromString(pizzaToppings2));
        pizzaOrderPage.enterQuantity(quantity);
        pizzaOrderPage.enterTextInTheInputField("name", name);
        pizzaOrderPage.enterTextInTheInputField("email", email);
        pizzaOrderPage.enterTextInTheInputField("phone", phone);
        pizzaOrderPage.selectPaymentMethod(paymentMode);
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderTestPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"}, dataProvider = "pizzaOrderDataWithInvalidEmail", dataProviderClass = DataProviderClass.class)
    public void verifyUserIsNotAbleToPlaceOrderWhenEmailIsInvalid(String pizzaType, String pizzaToppings1, String
            pizzaToppings2, int quantity, String name, String email, String phone, String paymentMode) {
        test = extent.startTest("verify if the user is not able to place an order when email provided is invalid");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(PizzaTypes.fromString(pizzaType));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings1", PizzaToppings.fromString(pizzaToppings1));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings2", PizzaToppings.fromString(pizzaToppings2));
        pizzaOrderPage.enterQuantity(quantity);
        pizzaOrderPage.enterTextInTheInputField("name", name);
        pizzaOrderPage.enterTextInTheInputField("email", email);
        pizzaOrderPage.enterTextInTheInputField("phone", phone);
        pizzaOrderPage.selectPaymentMethod(paymentMode);
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderTestPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"}, dataProvider = "pizzaOrderDataWithNoPizzaType", dataProviderClass = DataProviderClass.class)
    public void verifyUserIsNotAbleToPlaceOrderWhenPizzaTypeIsNotSelected(int quantity, String name, String email, String phone, String paymentMode) {
        test = extent.startTest("verify if the user is not able to place an order when pizza type is not selected");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.enterQuantity(quantity);
        pizzaOrderPage.enterTextInTheInputField("name", name);
        pizzaOrderPage.enterTextInTheInputField("email", email);
        pizzaOrderPage.enterTextInTheInputField("phone", phone);
        pizzaOrderPage.selectPaymentMethod(paymentMode);
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderTestPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"}, dataProvider = "pizzaOrderDataWithNoToppings1", dataProviderClass = DataProviderClass.class)
    public void verifyUserIsNotAbleToPlaceOrderWhenToppings1IsNotSelected(String pizzaType, int quantity, String name, String email, String phone, String paymentMode) {
        test = extent.startTest("verify if the user is not able to place an order when pizza type is selected as pizza with one topping and topping is not selected from topping 1");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(PizzaTypes.fromString(pizzaType));
        pizzaOrderPage.enterQuantity(quantity);
        pizzaOrderPage.enterTextInTheInputField("name", name);
        pizzaOrderPage.enterTextInTheInputField("email", email);
        pizzaOrderPage.enterTextInTheInputField("phone", phone);
        pizzaOrderPage.selectPaymentMethod(paymentMode);
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderTestPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"}, dataProvider = "pizzaOrderDataWithNoToppings2", dataProviderClass = DataProviderClass.class)
    public void verifyUserIsNotAbleToPlaceOrderWhenToppings2IsNotSelected(String pizzaType, String pizzaToppings1, int quantity, String name, String email, String phone, String paymentMode) {
        test = extent.startTest("verify if the user is not able to place an order when pizza type with two toppings is selected and toppings from toppings 1 and toppings 2 is not selected");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(PizzaTypes.fromString(pizzaType));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings1", PizzaToppings.fromString(pizzaToppings1));
        pizzaOrderPage.enterQuantity(quantity);
        pizzaOrderPage.enterTextInTheInputField("name", name);
        pizzaOrderPage.enterTextInTheInputField("email", email);
        pizzaOrderPage.enterTextInTheInputField("phone", phone);
        pizzaOrderPage.selectPaymentMethod(paymentMode);
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderTestPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"}, dataProvider = "pizzaOrderData", dataProviderClass = DataProviderClass.class)
    public void verifyUserIsNotAbleToPlaceOrderWhenQuantityIsNotSelected(String pizzaType, String pizzaToppings1, String pizzaToppings2, int quantity, String name, String email, String phone, String paymentMode) {
        test = extent.startTest("verify if the user is not able to place an order when quantity field is not selected");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(PizzaTypes.fromString(pizzaType));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings1", PizzaToppings.fromString(pizzaToppings1));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings2", PizzaToppings.fromString(pizzaToppings2));
        pizzaOrderPage.enterTextInTheInputField("name", name);
        pizzaOrderPage.enterTextInTheInputField("email", email);
        pizzaOrderPage.enterTextInTheInputField("phone", phone);
        pizzaOrderPage.selectPaymentMethod(paymentMode);
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderTestPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"}, dataProvider = "pizzaOrderData", dataProviderClass = DataProviderClass.class)
    public void verifyUserIsNotAbleToPlaceOrderWhenPaymentModeIsNotSelected(String pizzaType, String pizzaToppings1, String pizzaToppings2, int quantity, String name, String email, String phone, String paymentMode) {
        test = extent.startTest("verify if the user is not able to place an order when payment mode is not selected");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(PizzaTypes.fromString(pizzaType));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings1", PizzaToppings.fromString(pizzaToppings1));
        pizzaOrderPage.choosePizzaToppings("pizza1Toppings2", PizzaToppings.fromString(pizzaToppings2));
        pizzaOrderPage.enterQuantity(quantity);
        pizzaOrderPage.enterTextInTheInputField("name", name);
        pizzaOrderPage.enterTextInTheInputField("email", email);
        pizzaOrderPage.enterTextInTheInputField("phone", phone);
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderTestPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"}, dataProvider = "pizzaOrderDataWithAllPaymentModes", dataProviderClass = DataProviderClass.class)
    public void verifyUserIsNotAbleToSelectAllThePaymentModes(String pizzaType, int quantity, String name, String email, String phone, String paymentMode1, String paymentMode2) {
        test = extent.startTest("verify if the user is not able to select multiple payment modes");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaOrderTestPage pizzaOrderTestPage = new PizzaOrderTestPage(driver, pizzaOrderPage);
        pizzaOrderPage.choosePizza(PizzaTypes.fromString(pizzaType));
        pizzaOrderPage.enterQuantity(quantity);
        pizzaOrderPage.enterTextInTheInputField("name", name);
        pizzaOrderPage.enterTextInTheInputField("email", email);
        pizzaOrderPage.enterTextInTheInputField("phone", phone);
        pizzaOrderPage.selectPaymentMethod(paymentMode1);
        pizzaOrderPage.selectPaymentMethod(paymentMode2);
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderTestPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }
}
