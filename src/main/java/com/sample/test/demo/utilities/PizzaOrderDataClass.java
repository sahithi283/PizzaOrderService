package com.sample.test.demo.utilities;

import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class PizzaOrderDataClass {

    private static JSONObject jsonObject;

    static {
        try {
            jsonObject = ReadDataUtilityClass.readDataFromJsonFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return (String) jsonObject.get("name");
    }

    public String getPhone() {
        return (String) jsonObject.get("phone");
    }

    public String getEmail() {
        return (String) jsonObject.get("email");
    }

    public String getPaymentMode1() {
        return (String) jsonObject.get("paymentModeByCreditCard");
    }

    public String getPaymentMode2() {
        return (String) jsonObject.get("paymentModeByCashOnPickup");
    }

    public PizzaTypes getPizzaTypeWithTwoToppings() {
        return PizzaTypes.valueOf((String) jsonObject.get("twoToppingsPizza"));
    }

    public PizzaTypes getPizzaTypeWithOneTopping() {
        return PizzaTypes.valueOf((String) jsonObject.get("oneToppingPizza"));
    }

    public PizzaTypes getPizzaTypeWithNoToppings() {
        return PizzaTypes.valueOf((String) jsonObject.get("noToppingsPizza"));
    }

    public PizzaToppings getPizzaToppings1() {
        return PizzaToppings.fromString((String) jsonObject.get("toppings1"));
    }

    public PizzaToppings getPizzaToppings2() {
        return PizzaToppings.fromString((String) jsonObject.get("toppings2"));
    }

    public int getNegativeQuantity() {
        return Integer.parseInt((String) jsonObject.get("negativeQuantity"));
    }

    public int getQuantity() {
        return Integer.parseInt((String) jsonObject.get("quantity"));
    }

    public int getMaximumQuantity() {
        return Integer.parseInt((String) jsonObject.get("maximumQuantity"));
    }

    public int getVeryLargeQuantity() {
        return Integer.parseInt((String) jsonObject.get("veryLargeQuantityValue"));
    }

    public String getInvalidPhoneNumber() {
        return (String) jsonObject.get("invalidPhoneNumber");
    }

    public String getInvalidEmail() {
        return (String) jsonObject.get("invalidEmail");
    }
}
