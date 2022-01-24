package com.sample.test.demo.utilities;

import com.sample.test.demo.constants.PizzaTypes;
import org.testng.annotations.DataProvider;


public class DataProviderClass {

    @DataProvider(name = "pizzaTypeProvider")
    public static Object[] getPizzaType() {
        PizzaTypes[] pizzaTypes = PizzaTypes.values();
        Object[][] object = new Object[pizzaTypes.length][];
        for (int i = 0; i < pizzaTypes.length; i++) {
            object[i] = new Object[]{pizzaTypes[i]};
        }
        return object;
    }
}
