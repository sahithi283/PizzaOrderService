package com.sample.test.demo.tests;

import com.relevantcodes.extentreports.LogStatus;
import com.sample.test.demo.Pages.PizzaOrderPage;
import com.sample.test.demo.constants.PizzaTypes;
import com.sample.test.demo.utilities.DataProviderClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PizzaOrderPageTest extends TestBase {

    @Test
    public void verifyTitleOfPizzaOrderPage() {
        test = extent.startTest("Verify title of the pizza order page");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        Assert.assertTrue(pizzaOrderPage.isTitleOfPizzaOrderPageDisplayedCorrectly("Pizza Order Form"));
        test.log(LogStatus.PASS, "Pizza order page title is verified");
    }

    @Test(groups = {"pizza order form"})
    public void verifyTitleOfPizzaOrderForm() {
        test = extent.startTest("Verify title of the pizza order form");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        Assert.assertTrue(pizzaOrderPage.isTitleOfPizzaOrderFormDisplayedCorrectly("Pizza Order Form"));
        test.log(LogStatus.PASS, "Test Passed and the pizza order form title is verified");
    }

    @Test(groups = {"pickup information form"})
    public void verifyTitleOfPickupInformation() {
        test = extent.startTest("Verify title of the pick up information form");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        Assert.assertTrue(pizzaOrderPage.isTitleOfPickupInfoDisplayedCorrectly("PICKUP INFORMATION"));
        test.log(LogStatus.PASS, "Test Passed and the pickup information form title is verified");
    }

    @Test(groups = {"paymentInformation"})
    public void verifyTitleOfPaymentInformation() {
        test = extent.startTest("verify title of the payment information form");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        Assert.assertTrue(pizzaOrderPage.isTitleOfPaymentInfoDisplayedCorrectly("PAYMENT INFORMATION"));
        test.log(LogStatus.PASS, "Test Passed and the payment information form title is verified");
    }

    @Test(groups = {"pizza order form"})
    public void isPizzaTypeSelected() {
        test = extent.startTest("verify if the user is able to select any of the option from the pizza type drop down");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaOrderData.getPizzaTypeWithNoToppings());
        Assert.assertTrue(pizzaOrderPage.isPizzaTypeSelected(pizzaOrderData.getPizzaTypeWithNoToppings()));
        test.log(LogStatus.PASS, "Test Passed and user is able to select any of the option from the pizza type drop down");
    }

    @Test(groups = {"pizza order form"})
    public void isToppings1Selected() {
        test = extent.startTest("verify if the pizza user is able to select any of the option from the pizza toppings 1 drop down");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizzaToppings1(pizzaOrderData.getPizzaToppings1());
        Assert.assertTrue(pizzaOrderPage.isPizzaTopping1Selected(pizzaOrderData.getPizzaToppings1()));
        test.log(LogStatus.PASS, "Test Passed and user is able to select any of the option from the pizza toppings 1 drop down");
    }

    @Test(groups = {"pizza order form"})
    public void isToppings2Selected() {
        test = extent.startTest("verify if the pizza user is able to select any of the option from the pizza toppings 2 drop down");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizzaToppings2(pizzaOrderData.getPizzaToppings2());
        Assert.assertTrue(pizzaOrderPage.isPizzaTopping2Selected(pizzaOrderData.getPizzaToppings2()));
        test.log(LogStatus.PASS, "Test Passed and user is able to select any of the option from the pizza toppings 2 drop down");
    }

    @Test(groups = {"pizza order form"})
    public void isQuantityFieldEditable() {
        test = extent.startTest("verify if the user is able to edit the quantity field");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.enterQuantity(pizzaOrderData.getQuantity());
        Assert.assertTrue(pizzaOrderPage.isQuantityEnteredCorrectly(pizzaOrderData.getQuantity()));
        test.log(LogStatus.PASS, "Test Passed and user is able to edit the quantity field");
    }

    @Test(groups = {"pizza order form"})
    public void verifyQuantityFieldDoesNotAcceptOrdersLessThanZero() {
        test = extent.startTest("verify if the quantity field accepts order less than zero");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaOrderData.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.choosePizzaToppings1(pizzaOrderData.getPizzaToppings1());
        pizzaOrderPage.choosePizzaToppings2(pizzaOrderData.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(pizzaOrderData.getNegativeQuantity());
        pizzaOrderPage.enterUserName(pizzaOrderData.getName());
        pizzaOrderPage.enterUserEmail(pizzaOrderData.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(pizzaOrderData.getPhone());
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form"})
    public void isQuantityFieldAcceptingTheMaximumValue() {
        test = extent.startTest("verify if the quantity field accepts the maximum value upto length five");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaOrderData.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.choosePizzaToppings1(pizzaOrderData.getPizzaToppings1());
        pizzaOrderPage.choosePizzaToppings2(pizzaOrderData.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(pizzaOrderData.getMaximumQuantity());
        Assert.assertTrue(pizzaOrderPage.isQuantityEnteredCorrectly(pizzaOrderData.getMaximumQuantity()));
        pizzaOrderPage.enterUserName(pizzaOrderData.getName());
        pizzaOrderPage.enterUserEmail(pizzaOrderData.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(pizzaOrderData.getPhone());
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed and quantity field accepts the maximum value upto length five");
    }

    @Test(groups = {"pizza order form"})
    public void verifyQuantityFieldDoesNotAcceptVeryLargeValues() {
        test = extent.startTest("verify if the quantity field does not accept very large values");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.enterQuantity(pizzaOrderData.getVeryLargeQuantity());
        Assert.assertTrue(pizzaOrderPage.isQuantityEnteredCorrectly(pizzaOrderData.getMaximumQuantity()));
        test.log(LogStatus.PASS, "Test Passed and the quantity field does not accept very large values");
    }

    @Test(groups = {"pizza order form"})
    public void isCostDisplayedCorrectly() {
        test = extent.startTest("verify if the cost is calculated and displayed correctly");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaTypes pizzaType = pizzaOrderData.getPizzaTypeWithNoToppings();
        pizzaOrderPage.choosePizza(pizzaType);
        pizzaOrderPage.enterQuantity(pizzaOrderData.getQuantity());
        pizzaOrderPage.enterUserName(pizzaOrderData.getName());
        double expectedCost = pizzaType.getCost() * pizzaOrderPage.getQuantityData();
        double actualCost = pizzaOrderPage.getCostDisplayed();
        Assert.assertEquals(actualCost, expectedCost);
        test.log(LogStatus.PASS, "Test Passed and the cost is calculated and displayed correctly");
    }

    @Test(groups = {"pickup information form"})
    public void isNameFieldEditable() {
        test = extent.startTest("verify if the name field is editable or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.enterUserName(pizzaOrderData.getName());
        Assert.assertTrue(pizzaOrderPage.isUserNameEnteredCorrectly(pizzaOrderData.getName()));
        test.log(LogStatus.PASS, "Test Passed and the name field is editable");
    }

    @Test(groups = {"pickup information form"})
    public void isEmailFieldEditable() {
        test = extent.startTest("verify if the email field is editable or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.enterUserEmail(pizzaOrderData.getEmail());
        Assert.assertTrue(pizzaOrderPage.isUserEmailEnteredCorrectly(pizzaOrderData.getEmail()));
        test.log(LogStatus.PASS, "Test Passed and the email field is editable");
    }

    @Test(groups = {"pickup information form"})
    public void isPhoneNumberFieldEditable() {
        test = extent.startTest("verify if the phone number field is editable or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.enterUserPhoneNumber(pizzaOrderData.getPhone());
        Assert.assertTrue(pizzaOrderPage.isUserPhoneNumberEnteredCorrectly(pizzaOrderData.getPhone()));
        test.log(LogStatus.PASS, "Test Passed and the phone number field is editable");
    }

    @Test(groups = {"paymentInformation"})
    public void isPaymentInformationButtonSelectable() {
        test = extent.startTest("verify if the payment information is selectable or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode2());
        Assert.assertTrue(pizzaOrderPage.isPaymentModeRadioButtonElementSelected());
        test.log(LogStatus.PASS, "Test Passed and the payment information is selectable");
    }

    @Test(groups = {"placeOrder"})
    public void isPlaceOrderButtonDisplayed() {
        test = extent.startTest("verify if the place order button is displayed or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        Assert.assertTrue(pizzaOrderPage.isButtonDisplayed("placeOrderButton"));
        test.log(LogStatus.PASS, "Test Passed and the place order button is displayed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"})
    public void isSuccessMessageDisplayed() {
        test = extent.startTest("verify if the success message is displayed or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaOrderData.getPizzaTypeWithNoToppings());
        pizzaOrderPage.enterUserName(pizzaOrderData.getName());
        pizzaOrderPage.enterUserPhoneNumber(pizzaOrderData.getPhone());
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed and the success dialog box is displayed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"})
    public void verifyUserIsAbleToPlaceOrderSuccessfully() {
        test = extent.startTest("verify if the user is able to place the order successfully");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaOrderData.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.choosePizzaToppings1(pizzaOrderData.getPizzaToppings1());
        pizzaOrderPage.choosePizzaToppings2(pizzaOrderData.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(pizzaOrderData.getQuantity());
        pizzaOrderPage.enterUserName(pizzaOrderData.getName());
        Double costDisplayed = pizzaOrderPage.getCostDisplayed();
        pizzaOrderPage.enterUserEmail(pizzaOrderData.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(pizzaOrderData.getPhone());
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderPage.isSuccessMessageDisplayed());
        Assert.assertTrue(pizzaOrderPage.isSuccessMessageDisplayedCorrectly(pizzaOrderData.getPizzaTypeWithTwoToppings(), costDisplayed));
        test.log(LogStatus.PASS, "Test Passed and the user is able to place the order successfully");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"})
    public void verifyUserIsAbleToSeeErrorMessageWhenNameIsNotFilled() {
        test = extent.startTest("verify if the user is able to see the error message when the name field is not filled");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaOrderData.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.choosePizzaToppings1(pizzaOrderData.getPizzaToppings1());
        pizzaOrderPage.choosePizzaToppings2(pizzaOrderData.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(pizzaOrderData.getQuantity());
        pizzaOrderPage.enterUserEmail(pizzaOrderData.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(pizzaOrderData.getPhone());
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderPage.isErrorMessageDisplayed("Missing name"));
        test.log(LogStatus.PASS, "Test Passed and the user is able to see the error message when the name field is not filled");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"})
    public void verifyUserIsAbleToSeeErrorMessageWhenPhoneNumberIsNotFilled() {
        test = extent.startTest("verify if the user is able to see the error message when the phone number field is not filled");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaOrderData.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.choosePizzaToppings1(pizzaOrderData.getPizzaToppings1());
        pizzaOrderPage.choosePizzaToppings2(pizzaOrderData.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(pizzaOrderData.getQuantity());
        pizzaOrderPage.enterUserEmail(pizzaOrderData.getEmail());
        pizzaOrderPage.enterUserName(pizzaOrderData.getName());
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderPage.isErrorMessageDisplayed("Missing phone number"));
        test.log(LogStatus.PASS, "Test Passed and the user is able to see the error message when the phone number field is not filled");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"})
    public void verifyUserIsAbleToSeeErrorMessageWhenNameAndPhoneNumberIsNotFilled() {
        test = extent.startTest("verify if the user is able to see the error message when the name and phone number fields are not filled");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaOrderData.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.choosePizzaToppings1(pizzaOrderData.getPizzaToppings1());
        pizzaOrderPage.choosePizzaToppings2(pizzaOrderData.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(pizzaOrderData.getQuantity());
        pizzaOrderPage.enterUserEmail(pizzaOrderData.getEmail());
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderPage.isErrorMessageDisplayed("Missing name"));
        Assert.assertTrue(pizzaOrderPage.isErrorMessageDisplayed("Missing phone number"));
        test.log(LogStatus.PASS, "Test Passed and the user is able to see the error message when the name and phone number fields are not filled");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder", "resetFields"})
    public void verifyUserIsAbleToResetSuccessfully() {
        test = extent.startTest("verify if all the field of the pizza order page are reset successfully or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaOrderData.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.choosePizzaToppings1(pizzaOrderData.getPizzaToppings1());
        pizzaOrderPage.choosePizzaToppings2(pizzaOrderData.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(pizzaOrderData.getQuantity());
        pizzaOrderPage.enterUserName(pizzaOrderData.getName());
        pizzaOrderPage.enterUserEmail(pizzaOrderData.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(pizzaOrderData.getPhone());
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode1());
        pizzaOrderPage.clickOnResetButton();
        Assert.assertTrue(pizzaOrderPage.isElementsReset());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(dataProvider = "pizzaTypeProvider", dataProviderClass = DataProviderClass.class)
    public void isToppingsEnabledAsPerPizzaTypeSelection(PizzaTypes pizzaType) {
        test = extent.startTest("verify if the toppings are enabled as per the pizza type selection");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaType);
        Assert.assertTrue(pizzaOrderPage.isToppingsEnabledAsPerPizzaTypeSelection(pizzaType));
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"})
    public void verifyUserIsNotAbleToPlaceOrderWhenPhoneNoIsInvalid() {
        test = extent.startTest("verify if the user is not able to place an order when phone number provided is invalid");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaOrderData.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.choosePizzaToppings1(pizzaOrderData.getPizzaToppings1());
        pizzaOrderPage.choosePizzaToppings2(pizzaOrderData.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(pizzaOrderData.getQuantity());
        pizzaOrderPage.enterUserName(pizzaOrderData.getName());
        pizzaOrderPage.enterUserEmail(pizzaOrderData.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(pizzaOrderData.getInvalidPhoneNumber());
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"})
    public void verifyUserIsNotAbleToPlaceOrderWhenEmailIsInvalid() {
        test = extent.startTest("verify if the user is not able to place an order when email provided is invalid");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaOrderData.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.choosePizzaToppings1(pizzaOrderData.getPizzaToppings1());
        pizzaOrderPage.choosePizzaToppings2(pizzaOrderData.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(pizzaOrderData.getQuantity());
        pizzaOrderPage.enterUserName(pizzaOrderData.getName());
        pizzaOrderPage.enterUserEmail(pizzaOrderData.getInvalidEmail());
        pizzaOrderPage.enterUserPhoneNumber(pizzaOrderData.getPhone());
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"})
    public void verifyUserIsNotAbleToPlaceOrderWhenPizzaTypeIsNotSelected() {
        test = extent.startTest("verify if the user is not able to place an order when pizza type is not selected");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.enterQuantity(pizzaOrderData.getQuantity());
        pizzaOrderPage.enterUserName(pizzaOrderData.getName());
        pizzaOrderPage.enterUserEmail(pizzaOrderData.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(pizzaOrderData.getPhone());
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"})
    public void verifyUserIsNotAbleToPlaceOrderWhenToppings1IsNotSelected() {
        test = extent.startTest("verify if the user is not able to place an order when pizza type is selected as pizza with one topping and topping is not selected from topping 1");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaOrderData.getPizzaTypeWithOneTopping());
        pizzaOrderPage.enterQuantity(pizzaOrderData.getQuantity());
        pizzaOrderPage.enterUserName(pizzaOrderData.getName());
        pizzaOrderPage.enterUserEmail(pizzaOrderData.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(pizzaOrderData.getPhone());
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test
    public void verifyUserIsNotAbleToPlaceOrderWhenToppings2IsNotSelected() {
        test = extent.startTest("verify if the user is not able to place an order when pizza type with two toppings is selected and toppings from toppings 1 and toppings 2 is not selected");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaOrderData.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.enterQuantity(pizzaOrderData.getQuantity());
        pizzaOrderPage.enterUserName(pizzaOrderData.getName());
        pizzaOrderPage.enterUserEmail(pizzaOrderData.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(pizzaOrderData.getPhone());
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"})
    public void verifyUserIsNotAbleToPlaceOrderWhenQuantityIsNotSelected() {
        test = extent.startTest("verify if the user is not able to place an order when quantity field is not selected");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaOrderData.getPizzaTypeWithNoToppings());
        pizzaOrderPage.enterUserName(pizzaOrderData.getName());
        pizzaOrderPage.enterUserEmail(pizzaOrderData.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(pizzaOrderData.getPhone());
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"})
    public void verifyUserIsNotAbleToPlaceOrderWhenPaymentModeIsNotSelected() {
        test = extent.startTest("verify if the user is not able to place an order when payment mode is not selected");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaOrderData.getPizzaTypeWithNoToppings());
        pizzaOrderPage.enterQuantity(pizzaOrderData.getQuantity());
        pizzaOrderPage.enterUserName(pizzaOrderData.getName());
        pizzaOrderPage.enterUserEmail(pizzaOrderData.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(pizzaOrderData.getPhone());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test(groups = {"pizza order form", "pickup information form", "paymentInformation", "placeOrder"})
    public void verifyUserIsNotAbleToSelectAllThePaymentModes() {
        test = extent.startTest("verify if the user is not able to select multiple payment modes");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(pizzaOrderData.getPizzaTypeWithNoToppings());
        pizzaOrderPage.enterQuantity(pizzaOrderData.getQuantity());
        pizzaOrderPage.enterUserName(pizzaOrderData.getName());
        pizzaOrderPage.enterUserEmail(pizzaOrderData.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(pizzaOrderData.getPhone());
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode1());
        pizzaOrderPage.selectPaymentMethod(pizzaOrderData.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }
}
