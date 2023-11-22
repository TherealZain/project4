package com.pizzashop.project4.pizzas;

import com.pizzashop.project4.enums.Sauce;
import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.enums.Toppings;

public class Seafood extends Pizza{
    private static final double SEAFOOD_SMALL_PRICE = 17.99;

    public Seafood() {
        sauce = Sauce.ALFREDO;
        toppings.add(Toppings.SHRIMP);
        toppings.add(Toppings.SQUID);
        toppings.add(Toppings.CRABMEAT);
    }

    @Override
    public double price() {
        double price = SEAFOOD_SMALL_PRICE;
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

    @Override
    public String toString() {
        return "hi";
    }
}
