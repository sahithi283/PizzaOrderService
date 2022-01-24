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

    @Test
    public void verifyTitleOfPizzaOrderForm() {
        test = extent.startTest("Verify title of the pizza order form");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        Assert.assertTrue(pizzaOrderPage.isTitleOfPizzaOrderFormDisplayedCorrectly("Pizza Order Form"));
        test.log(LogStatus.PASS, "Test Passed and the pizza order form title is verified");
    }

    @Test
    public void verifyTitleOfPickupInformation() {
        test = extent.startTest("Verify title of the pick up information form");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        Assert.assertTrue(pizzaOrderPage.isTitleOfPickupInfoDisplayedCorrectly("PICKUP INFORMATION"));
        test.log(LogStatus.PASS, "Test Passed and the pickup information form title is verified");
    }

    @Test
    public void verifyTitleOfPaymentInformation() {
        test = extent.startTest("verify title of the payment information form");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        Assert.assertTrue(pizzaOrderPage.isTitleOfPaymentInfoDisplayedCorrectly("PAYMENT INFORMATION"));
        test.log(LogStatus.PASS, "Test Passed and the payment information form title is verified");
    }

    @Test
    public void isPizzaTypeSelected() {
        test = extent.startTest("verify if the user is able to select any of the option from the pizza type drop down");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(config.getPizzaTypeWithNoToppings());
        Assert.assertTrue(pizzaOrderPage.isPizzaTypeSelected(config.getPizzaTypeWithNoToppings()));
        test.log(LogStatus.PASS, "Test Passed and user is able to select any of the option from the pizza type drop down");
    }

    @Test
    public void isToppings1Selected() {
        test = extent.startTest("verify if the pizza user is able to select any of the option from the pizza toppings 1 drop down");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.chooseToppings1(config.getPizzaToppings1());
        Assert.assertTrue(pizzaOrderPage.isPizzaTopping1Selected(config.getPizzaToppings1()));
        test.log(LogStatus.PASS, "Test Passed and user is able to select any of the option from the pizza toppings 1 drop down");
    }

    @Test
    public void isToppings2Selected() {
        test = extent.startTest("verify if the pizza user is able to select any of the option from the pizza toppings 2 drop down");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.chooseToppings2(config.getPizzaToppings2());
        Assert.assertTrue(pizzaOrderPage.isPizzaTopping2Selected(config.getPizzaToppings2()));
        test.log(LogStatus.PASS, "Test Passed and user is able to select any of the option from the pizza toppings 2 drop down");
    }

    @Test
    public void isQuantityFieldEditable() {
        test = extent.startTest("verify if the user is able to edit the quantity field");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.enterQuantity(config.getQuantity());
        Assert.assertTrue(pizzaOrderPage.isQuantityEnteredCorrectly(config.getQuantity()));
        test.log(LogStatus.PASS, "Test Passed and user is able to edit the quantity field");
    }

    @Test
    public void verifyQuantityFieldDoesNotAcceptOrdersLessThanZero() {
        test = extent.startTest("verify if the quantity field accepts order less than zero");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(config.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.chooseToppings1(config.getPizzaToppings1());
        pizzaOrderPage.chooseToppings2(config.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(config.getNegativeQuantity());
        pizzaOrderPage.enterUserName(config.getName());
        pizzaOrderPage.enterUserEmail(config.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(config.getPhone());
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test
    public void isQuantityFieldAcceptingTheMaximumValue() {
        test = extent.startTest("verify if the quantity field accepts the maximum value upto length five");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(config.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.chooseToppings1(config.getPizzaToppings1());
        pizzaOrderPage.chooseToppings2(config.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(config.getMaximumQuantity());
        Assert.assertTrue(pizzaOrderPage.isQuantityEnteredCorrectly(config.getMaximumQuantity()));
        pizzaOrderPage.enterUserName(config.getName());
        pizzaOrderPage.enterUserEmail(config.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(config.getPhone());
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed and quantity field accepts the maximum value upto length five");
    }

    @Test
    public void verifyQuantityFieldDoesNotAcceptVeryLargeValues() {
        test = extent.startTest("verify if the quantity field does not accept very large values");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.enterQuantity(config.getVeryLargeQuantity());
        Assert.assertTrue(pizzaOrderPage.isQuantityEnteredCorrectly(config.getMaximumQuantity()));
        test.log(LogStatus.PASS, "Test Passed and the quantity field does not accept very large values");
    }

    @Test
    public void isCostDisplayedCorrectly() {
        test = extent.startTest("verify if the cost is calculated and displayed correctly");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        PizzaTypes pizzaType = config.getPizzaTypeWithNoToppings();
        pizzaOrderPage.choosePizza(pizzaType);
        pizzaOrderPage.enterQuantity(config.getQuantity());
        pizzaOrderPage.enterUserName(config.getName());
        double expectedCost = pizzaType.getCost() * pizzaOrderPage.getQuantityData();
        double actualCost = pizzaOrderPage.getCostDisplayed();
        Assert.assertEquals(actualCost, expectedCost);
        test.log(LogStatus.PASS, "Test Passed and the cost is calculated and displayed correctly");
    }

    @Test
    public void isNameFieldEditable() {
        test = extent.startTest("verify if the name field is editable or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.enterUserName(config.getName());
        Assert.assertTrue(pizzaOrderPage.isUserNameEnteredCorrectly(config.getName()));
        test.log(LogStatus.PASS, "Test Passed and the name field is editable");
    }

    @Test
    public void isEmailFieldEditable() {
        test = extent.startTest("verify if the email field is editable or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.enterUserEmail(config.getEmail());
        Assert.assertTrue(pizzaOrderPage.isUserEmailEnteredCorrectly(config.getEmail()));
        test.log(LogStatus.PASS, "Test Passed and the email field is editable");
    }

    @Test
    public void isPhoneNumberFieldEditable() {
        test = extent.startTest("verify if the phone number field is editable or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.enterUserPhoneNumber(config.getPhone());
        Assert.assertTrue(pizzaOrderPage.isUserPhoneNumberEnteredCorrectly(config.getPhone()));
        test.log(LogStatus.PASS, "Test Passed and the phone number field is editable");
    }

    @Test
    public void isPaymentInformationButtonSelectable() {
        test = extent.startTest("verify if the payment information is selectable or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode2());
        Assert.assertTrue(pizzaOrderPage.isRadioButtonSelected());
        test.log(LogStatus.PASS, "Test Passed and the payment information is selectable");
    }

    @Test
    public void isPlaceOrderButtonDisplayed() {
        test = extent.startTest("verify if the place order button is displayed or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        Assert.assertTrue(pizzaOrderPage.isButtonDisplayed("placeOrderButton"));
        test.log(LogStatus.PASS, "Test Passed and the place order button is displayed");
    }

    @Test
    public void isSuccessMessageDisplayed() {
        test = extent.startTest("verify if the success message is displayed or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(config.getPizzaTypeWithNoToppings());
        pizzaOrderPage.enterUserName(config.getName());
        pizzaOrderPage.enterUserPhoneNumber(config.getPhone());
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed and the success dialog box is displayed");
    }

    @Test
    public void verifyUserIsAbleToPlaceOrderSuccessfully() {
        test = extent.startTest("verify if the user is able to place the order successfully");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(config.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.chooseToppings1(config.getPizzaToppings1());
        pizzaOrderPage.chooseToppings2(config.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(config.getQuantity());
        pizzaOrderPage.enterUserName(config.getName());
        Double costDisplayed = pizzaOrderPage.getCostDisplayed();
        pizzaOrderPage.enterUserEmail(config.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(config.getPhone());
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderPage.isSuccessMessageDisplayed());
        Assert.assertTrue(pizzaOrderPage.isSuccessMessageDisplayedCorrectly(config.getPizzaTypeWithTwoToppings(), costDisplayed));
        test.log(LogStatus.PASS, "Test Passed and the user is able to place the order successfully");
    }

    @Test
    public void verifyUserIsAbleToSeeErrorMessageWhenNameIsNotFilled() {
        test = extent.startTest("verify if the user is able to see the error message when the name field is not filled");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(config.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.chooseToppings1(config.getPizzaToppings1());
        pizzaOrderPage.chooseToppings2(config.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(config.getQuantity());
        pizzaOrderPage.enterUserEmail(config.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(config.getPhone());
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderPage.isErrorMessageDisplayed("Missing name"));
        test.log(LogStatus.PASS, "Test Passed and the user is able to see the error message when the name field is not filled");
    }

    @Test
    public void verifyUserIsAbleToSeeErrorMessageWhenPhoneNumberIsNotFilled() {
        test = extent.startTest("verify if the user is able to see the error message when the phone number field is not filled");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(config.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.chooseToppings1(config.getPizzaToppings1());
        pizzaOrderPage.chooseToppings2(config.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(config.getQuantity());
        pizzaOrderPage.enterUserEmail(config.getEmail());
        pizzaOrderPage.enterUserName(config.getName());
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderPage.isErrorMessageDisplayed("Missing phone number"));
        test.log(LogStatus.PASS, "Test Passed and the user is able to see the error message when the phone number field is not filled");
    }

    @Test
    public void verifyUserIsAbleToSeeErrorMessageWhenNameAndPhoneNumberIsNotFilled() {
        test = extent.startTest("verify if the user is able to see the error message when the name and phone number fields are not filled");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(config.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.chooseToppings1(config.getPizzaToppings1());
        pizzaOrderPage.chooseToppings2(config.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(config.getQuantity());
        pizzaOrderPage.enterUserEmail(config.getEmail());
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pizzaOrderPage.isErrorMessageDisplayed("Missing name"));
        Assert.assertTrue(pizzaOrderPage.isErrorMessageDisplayed("Missing phone number"));
        test.log(LogStatus.PASS, "Test Passed and the user is able to see the error message when the name and phone number fields are not filled");
    }

    @Test
    public void verifyUserIsAbleToResetSuccessfully() {
        test = extent.startTest("verify if all the field of the pizza order page are reset successfully or not");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(config.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.chooseToppings1(config.getPizzaToppings1());
        pizzaOrderPage.chooseToppings2(config.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(config.getQuantity());
        pizzaOrderPage.enterUserName(config.getName());
        pizzaOrderPage.enterUserEmail(config.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(config.getPhone());
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode1());
        pizzaOrderPage.clickOnResetButton();
        Assert.assertTrue(pizzaOrderPage.IsElementsResetted());
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

    @Test
    public void verifyUserIsNotAbleToPlaceOrderWhenPhoneNoIsInvalid() {
        test = extent.startTest("verify if the user is not able to place an order when phone number provided is invalid");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(config.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.chooseToppings1(config.getPizzaToppings1());
        pizzaOrderPage.chooseToppings2(config.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(config.getQuantity());
        pizzaOrderPage.enterUserName(config.getName());
        pizzaOrderPage.enterUserEmail(config.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(config.getInvalidPhoneNumber());
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test
    public void verifyUserIsNotAbleToPlaceOrderWhenEmailIsInvalid() {
        test = extent.startTest("verify if the user is not able to place an order when email provided is invalid");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(config.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.chooseToppings1(config.getPizzaToppings1());
        pizzaOrderPage.chooseToppings2(config.getPizzaToppings2());
        pizzaOrderPage.enterQuantity(config.getQuantity());
        pizzaOrderPage.enterUserName(config.getName());
        pizzaOrderPage.enterUserEmail(config.getInvalidEmail());
        pizzaOrderPage.enterUserPhoneNumber(config.getPhone());
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test
    public void verifyUserIsNotAbleToPlaceOrderWhenPizzaTypeIsNotSelected() {
        test = extent.startTest("verify if the user is not able to place an order when pizza type is not selected");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.enterQuantity(config.getQuantity());
        pizzaOrderPage.enterUserName(config.getName());
        pizzaOrderPage.enterUserEmail(config.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(config.getPhone());
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test
    public void verifyUserIsNotAbleToPlaceOrderWhenToppings1IsNotSelected() {
        test = extent.startTest("verify if the user is not able to place an order when pizza type is selected as pizza with one topping and topping is not selected from topping 1");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(config.getPizzaTypeWithOneTopping());
        pizzaOrderPage.enterQuantity(config.getQuantity());
        pizzaOrderPage.enterUserName(config.getName());
        pizzaOrderPage.enterUserEmail(config.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(config.getPhone());
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test
    public void verifyUserIsNotAbleToPlaceOrderWhenToppings2IsNotSelected() {
        test = extent.startTest("verify if the user is not able to place an order when pizza type with two toppings is selected and toppings from toppings 1 and toppings 2 is not selected");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(config.getPizzaTypeWithTwoToppings());
        pizzaOrderPage.enterQuantity(config.getQuantity());
        pizzaOrderPage.enterUserName(config.getName());
        pizzaOrderPage.enterUserEmail(config.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(config.getPhone());
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test
    public void verifyUserIsNotAbleToPlaceOrderWhenQuantityIsNotSelected() {
        test = extent.startTest("verify if the user is not able to place an order when quantity field is not selected");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(config.getPizzaTypeWithNoToppings());
        pizzaOrderPage.enterUserName(config.getName());
        pizzaOrderPage.enterUserEmail(config.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(config.getPhone());
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test
    public void verifyUserIsNotAbleToPlaceOrderWhenPaymentModeIsNotSelected() {
        test = extent.startTest("verify if the user is not able to place an order when payment mode is not selected");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(config.getPizzaTypeWithNoToppings());
        pizzaOrderPage.enterQuantity(config.getQuantity());
        pizzaOrderPage.enterUserName(config.getName());
        pizzaOrderPage.enterUserEmail(config.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(config.getPhone());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Test
    public void verifyUserIsNotAbleToSelectAllThePaymentModes() {
        test = extent.startTest("verify if the user is not able to select multiple payment modes");
        PizzaOrderPage pizzaOrderPage = new PizzaOrderPage(driver);
        pizzaOrderPage.choosePizza(config.getPizzaTypeWithNoToppings());
        pizzaOrderPage.enterQuantity(config.getQuantity());
        pizzaOrderPage.enterUserName(config.getName());
        pizzaOrderPage.enterUserEmail(config.getEmail());
        pizzaOrderPage.enterUserPhoneNumber(config.getPhone());
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode1());
        pizzaOrderPage.selectPaymentMethod(config.getPaymentMode2());
        pizzaOrderPage.clickOnPlaceOrderButton();
        Assert.assertFalse(pizzaOrderPage.isSuccessMessageDisplayed());
        test.log(LogStatus.PASS, "Test Passed");
    }
}
