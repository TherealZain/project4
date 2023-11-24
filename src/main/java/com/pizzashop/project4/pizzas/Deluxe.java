package com.pizzashop.project4.pizzas;

import com.pizzashop.project4.BuildOwnController;
import com.pizzashop.project4.enums.Sauce;
import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.enums.Toppings;
import java.util.ArrayList;

public class Deluxe extends Pizza{

    private static final double DELUXE_SMALL_PRICE = 14.99;

    public Deluxe() {
        toppings = new ArrayList<>();
        size = Size.SMALL;
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.SAUSAGE);
        toppings.add(Toppings.PEPPERONI);
        toppings.add(Toppings.GREENPEPPER);
        toppings.add(Toppings.ONION);
        toppings.add(Toppings.MUSHROOM);
    }
    @Override
    public double price() {
        double price = DELUXE_SMALL_PRICE;
        if(size == Size.MEDIUM){
            price += Size.MEDIUM.getPriceAdd();
        }else
        if(size == Size.LARGE){
            price += Size.LARGE.getPriceAdd();
        }
        if(extraCheese){
            price++;
        }
        if(extraSauce){
            price++;
        }
        System.out.println("Price calculated: " + price); // Debugging statement
        return price;
    }

    @Override
    public void addToppings(Toppings topping) {

    }

    @Override
    public void removeToppings(Toppings topping) {

    }

    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }

    @Override
    public String toString() {
        String pizzaType = "[Deluxe] ";
        String toppingsString = "";
        for (Toppings topping : toppings) {
            if (!toppingsString.isEmpty()) {
                toppingsString += ", ";
            }
            toppingsString += BuildOwnController.capitalize(topping.name().toLowerCase().replace('_', ' '));
        }
        String sizeString = ", " + size.toString().toLowerCase();
        String sauceString = ", " + sauce.toString().toLowerCase();

        String extraCheeseString = extraCheese ? ", extra cheese" : "";
        String extraSauceString = extraSauce ? ", extra sauce" : "";

        String priceString = " $" + String.format("%.2f", price());

        String result = pizzaType + toppingsString + sizeString + sauceString + extraCheeseString + extraSauceString + priceString;
        System.out.println("toString generated: " + result); // Debugging statement
        return result;
    }
}
