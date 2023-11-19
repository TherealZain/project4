package com.pizzashop.project4.pizzas;

import com.pizzashop.project4.enums.Sauce;
import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.enums.Toppings;

public class Deluxe extends Pizza{

    private static final double DELUXE_SMALL_PRICE = 14.99;

    public Deluxe() {
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
            return price + Size.MEDIUM.getPriceAdd();
        }
        if(size == Size.LARGE){
            return price + Size.LARGE.getPriceAdd();
        }
        return price;
    }

    @Override
    public void addToppings(Toppings topping) {

    }

    @Override
    public void removeToppings(Toppings topping) {

    }
}
