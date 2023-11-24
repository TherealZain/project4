package com.pizzashop.project4.pizzas;

import com.pizzashop.project4.BuildOwnController;
import com.pizzashop.project4.enums.Sauce;
import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.enums.Toppings;

import java.util.ArrayList;

public class BuildYourOwn extends Pizza{
    private static final int MAX_TOPPINGS = 3;
    private ArrayList<Toppings> toppings;
    private static final double EXTRA_TOPPING_COST = 1.49;
    public BuildYourOwn() {
        this.toppings = new ArrayList<>();
        this.size = Size.SMALL;
        this.sauce = Sauce.TOMATO;
        this.extraCheese = false;
        this.extraSauce = false;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public void setSauce(Sauce sauce){
        this.sauce = sauce;
    }

    @Override
    public double price() {
        double price = Size.SMALL.getPriceAdd();
        if(size == Size.MEDIUM){
            price += Size.MEDIUM.getPriceAdd();
        }else
        if(size == Size.LARGE){
            price += Size.LARGE.getPriceAdd();
        }
        if(toppings.size() > MAX_TOPPINGS){
            price += (toppings.size() - MAX_TOPPINGS)*EXTRA_TOPPING_COST;
        }
        if(extraCheese){
            price++;
        }
        if(extraSauce){
            price++;
        }
        return price;
    }

    @Override
    public void addToppings(Toppings topping){
        toppings.add(topping);
    }

    @Override
    public void removeToppings(Toppings topping){

        toppings.remove(topping);
    }

    @Override
    public String toString() {
        String pizzaType = "[Build Your Own] ";
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

        return pizzaType + toppingsString + sizeString + sauceString + extraCheeseString + extraSauceString + priceString;
    }
}

