package com.pizzashop.project4;

import com.pizzashop.project4.pizzas.BuildYourOwn;
import com.pizzashop.project4.pizzas.Deluxe;
import com.pizzashop.project4.pizzas.Supreme;
import com.pizzashop.project4.pizzas.Meatzza;
import com.pizzashop.project4.pizzas.Seafood;
import com.pizzashop.project4.pizzas.Pepperoni;
import com.pizzashop.project4.pizzas.Pizza;

/**
 * Factory class for creating various types of Pizza objects.
 * This class provides a static method to create different pizza
 * types based on a given string identifier.
 * It acts as a central point for creating instances of pizza subclasses
 * like BuildYourOwn, Deluxe, Supreme, Meatzza, Seafood, and Pepperoni.
 *
 * The createPizza method takes a string that identifies the type of pizza to be created and
 * returns an instance of the corresponding Pizza subclass.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */
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
