package com.pizzashop.project4.pizzas;

import com.pizzashop.project4.enums.Sauce;
import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.enums.Toppings;

public class Meatzza extends Pizza{

    private static final double MEATZZA_SMALL_PRICE = 16.99;
    public Meatzza(){
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.SAUSAGE);
        toppings.add(Toppings.PEPPERONI);
        toppings.add(Toppings.GREEN_PEPPER);
        toppings.add(Toppings.ONION);
        toppings.add(Toppings.MUSHROOM);
    }

    @Override
    public double price() {
        double price = MEATZZA_SMALL_PRICE;
        if(size == Size.MEDIUM){
            return price + Size.MEDIUM.getPriceAdd();
        }
        if(size == Size.LARGE){
            return price + Size.LARGE.getPriceAdd();
        }
        return price;
    }
    public void setSize(Size size){
        this.size = size;
    }
}
