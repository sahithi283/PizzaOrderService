package com.sample.test.demo.constants;


import java.util.Locale;

public enum PizzaToppings {
    MANGOES("Diced Mango"),
    OLIVES("Olives"),
    MUSHROOMS("Mushrooms"),
    ONIONS("Caramelized onions"),
    ITALIANHAM("Italian Ham"),
    PEPPERONI("Classic Pepperoni"),
    SALAMI("Salami"),
    PROVOLNE("Provolone cheese"),
    EXTRACHEESE("Extra cheese"),
    MOZZARELLA("Mozzarella cheese"),
    PARMASAN("Parmesan cheese");

    private String displayName;

    private PizzaToppings(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static PizzaToppings fromString(String pizzaToppings){
        return PizzaToppings.valueOf(pizzaToppings.toUpperCase());
    }
}
