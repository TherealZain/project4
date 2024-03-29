package com.pizzashop.project4.pizzas;

import com.pizzashop.project4.BuildOwnController;
import com.pizzashop.project4.enums.Sauce;
import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.enums.Toppings;
import java.util.ArrayList;

/**
 * Represents the Supreme pizza, a richly topped pizza type with a variety of ingredients.
 * This class extends the Pizza class and provides an implementation of its abstract methods
 * tailored to a pizza with diverse toppings.
 * The Supreme pizza includes a combination of meat and vegetable toppings and calculates its
 * price based on the chosen size.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */
public class Supreme extends Pizza{

    private static final double SUPREME_SMALL_PRICE = 15.99;

    /**
     * Constructor to create a Supreme pizza with default settings.
     * Initializes the pizza with a mix of meat and vegetable toppings and tomato sauce.
     */
    public Supreme(){
        toppings = new ArrayList<>();
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.SAUSAGE);
        toppings.add(Toppings.PEPPERONI);
        toppings.add(Toppings.HAM);
        toppings.add(Toppings.GREENPEPPER);
        toppings.add(Toppings.ONION);
        toppings.add(Toppings.BLACKOLIVE);
        toppings.add(Toppings.MUSHROOM);
    }

    /**
     * Calculates and returns the price of the Supreme pizza based on its size.
     * The base price is for a small size and increases for medium and large sizes.
     *
     * @return double representing the total price of the Supreme pizza.
     */
    @Override
    public double price() {
        double price = SUPREME_SMALL_PRICE;
        if(size == Size.MEDIUM){
            price += Size.MEDIUM.getPriceAdd();
        }
        if(size == Size.LARGE){
           price += Size.LARGE.getPriceAdd();
        }
        if(extraCheese){
            price++;
        }
        if(extraSauce){
            price++;
        }
        return price;
    }

    /**
     * Adding additional toppings is not supported for the Supreme pizza,
     * as it comes with a specific set of toppings.
     *
     * @param topping The topping that the user attempts to add (not used).
     */
    @Override
    public void addToppings(Toppings topping) {

    }

    /**
     * Removing existing toppings is not supported for the Supreme pizza,
     * as they are integral to its flavor profile.
     *
     * @param topping The topping that the user attempts to remove (not used).
     */
    @Override
    public void removeToppings(Toppings topping) {

    }

    /**
     * Sets the size of the Supreme pizza.
     *
     * @param newSize The new size of the pizza.
     */
    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }

    /**
     * Provides a string representation of the Supreme pizza.
     * The implementation should ideally include details such as the
     * type of pizza, the list of toppings, size, and sauce,
     * followed by the total price. However, it currently returns null
     * and needs to be properly implemented.
     *
     * @return String representing the details of the Supreme pizza, or null if not implemented.
     */
    @Override
    public String toString() {
        String pizzaType = "[Supreme] ";
        String toppingsString = "";
        for (Toppings topping : toppings) {
            if (!toppingsString.isEmpty()) {
                toppingsString += ", ";
            }
            toppingsString += BuildOwnController.capitalize
                    (topping.name().toLowerCase().replace('_', ' '));
        }
        String sizeString = ", " + size.toString().toLowerCase();
        String sauceString = ", " + sauce.toString().toLowerCase();
        String extraCheeseString = extraCheese ? ", extra cheese" : "";
        String extraSauceString = extraSauce ? ", extra sauce" : "";
        String priceString = " $" + String.format("%.2f", price());
        return pizzaType + toppingsString + sizeString + sauceString + extraCheeseString +
                extraSauceString + priceString;
    }
}
