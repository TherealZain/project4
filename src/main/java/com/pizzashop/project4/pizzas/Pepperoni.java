package com.pizzashop.project4.pizzas;

import com.pizzashop.project4.enums.Sauce;
import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.enums.Toppings;

public class Pepperoni extends Pizza{

    private static final double PEPPERONI_SMALL_PRICE = 10.99;
    public Pepperoni(){
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.PEPPERONI);
    }

    @Override
    public double price() {
        double price = PEPPERONI_SMALL_PRICE;
        if(size == Size.MEDIUM){
            return price + Size.MEDIUM.getPriceAdd();
        }
        if(size == Size.LARGE){
            return price + Size.LARGE.getPriceAdd();
        }
        return price;
    }
}
