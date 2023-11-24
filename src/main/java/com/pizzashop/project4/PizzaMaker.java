package com.pizzashop.project4;

import com.pizzashop.project4.pizzas.BuildYourOwn;
import com.pizzashop.project4.pizzas.Deluxe;
import com.pizzashop.project4.pizzas.Supreme;
import com.pizzashop.project4.pizzas.Meatzza;
import com.pizzashop.project4.pizzas.Seafood;
import com.pizzashop.project4.pizzas.Pepperoni;
import com.pizzashop.project4.pizzas.Pizza;

public class PizzaMaker {
    public static Pizza createPizza(String pizzaType) {
        return switch (pizzaType) {
            case "BYO" -> new BuildYourOwn();
            case "Deluxe" -> new Deluxe();
            case "Supreme" -> new Supreme();
            case "Meatzza" -> new Meatzza();
            case "Seafood" -> new Seafood();
            case "Pepperoni" -> new Pepperoni();
            default -> throw new IllegalArgumentException("Unknown pizza type: " + pizzaType);
        };
    }
}
