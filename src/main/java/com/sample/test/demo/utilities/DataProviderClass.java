package com.sample.test.demo.utilities;

import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;
import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "pizzaTypeProvider")
    public static Object[][] getPizzaType() {
        PizzaTypes[] pizzaTypes = PizzaTypes.values();
        Object[][] object = new Object[pizzaTypes.length][];
        for (int i = 0; i < pizzaTypes.length; i++) {
            object[i] = new Object[]{pizzaTypes[i]};
        }
        return object;
    }

    @DataProvider(name = "pizzaToppingsProvider")
    public static Object[][] getPizzaToppings() {
        PizzaToppings[] pizzaToppings = PizzaToppings.values();
        Object[][] object = new Object[pizzaToppings.length][];
        for (int i = 0; i < pizzaToppings.length; i++) {
            object[i] = new Object[]{pizzaToppings[i]};
        }
        return object;
    }

    @DataProvider(name = "quantityMaximumValue")
    public static Object[][] getQuantityMaximumValue() {
        return new Object[][]{{"MEDIUM_TWOTOPPINGS", "olives", "mushrooms", 99999, "user1", "user1@gmail.com", "123456789", "Cash"}};
    }

    @DataProvider(name = "quantityMinimumValue")
    public static Object[][] getQuantityMinimumValue() {
        return new Object[][]{{"MEDIUM_TWOTOPPINGS", "olives", "mushrooms", -1, "user1", "user1@gmail.com", "123456789", "Cash"}};
    }

    @DataProvider(name = "pizzaOrderData")
    public static Object[][] getPizzaOrderData() {
        return new Object[][]{{"MEDIUM_TWOTOPPINGS", "olives", "mushrooms", 5, "user1", "user1@gmail.com", "123456789", "Cash"}};
    }

    @DataProvider(name = "pizzaOrderDataWithInvalidName")
    public static Object[][] getPizzaOrderDataWithInvalidName() {
        return new Object[][]{{"MEDIUM_TWOTOPPINGS", "olives", "mushrooms", 5, "user1", "user1@gmail.com", "abcd", "Cash"}};
    }

    @DataProvider(name = "pizzaOrderDataWithInvalidEmail")
    public static Object[][] getPizzaOrderDataWithInvalidEmail() {
        return new Object[][]{{"MEDIUM_TWOTOPPINGS", "olives", "mushrooms", 5, "user1", "123", "123456789", "Cash"}};
    }

    @DataProvider(name = "pizzaOrderDataWithNoPizzaType")
    public static Object[][] getPizzaOrderDataWithNoPizzaType() {
        return new Object[][]{{5, "user1", "user1@gmail.com", "123456789", "Cash"}};
    }

    @DataProvider(name = "pizzaOrderDataWithNoToppings1")
    public static Object[][] getPizzaOrderDataWithNoToppings1() {
        return new Object[][]{{"SMALL_ONETOPPINGS", 5, "user1", "123", "123456789", "Cash"}};
    }

    @DataProvider(name = "pizzaOrderDataWithNoToppings2")
    public static Object[][] getPizzaOrderDataWithNoToppings2() {
        return new Object[][]{{"MEDIUM_TWOTOPPINGS", "olives", 5, "user1", "123", "123456789", "Cash"}};
    }

    @DataProvider(name = "pizzaOrderDataWithAllPaymentModes")
    public static Object[][] getPizzaOrderDataWithAllPaymentModes() {
        return new Object[][]{{"SMALL_NOTOPPINGS", 5, "user1", "123", "123456789", "Cash", "Credit Card"}};
    }

    @DataProvider(name = "pizzaData")
    public static Object[][] getOnlyThePizzaData() {
        return new Object[][]{{"SMALL_NOTOPPINGS", 5, "user1"}};
    }
}
