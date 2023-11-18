package com.pizzashop.project4.pizzas;

import com.pizzashop.project4.enums.Sauce;
import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.enums.Toppings;

public class Supreme extends Pizza{

    private static final double SUPREME_SMALL_PRICE = 15.99;
    public Supreme(){
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.SAUSAGE);
        toppings.add(Toppings.PEPPERONI);
        toppings.add(Toppings.HAM);
        toppings.add(Toppings.GREEN_PEPPER);
        toppings.add(Toppings.ONION);
        toppings.add(Toppings.BLACK_OLIVE);
        toppings.add(Toppings.MUSHROOM);
    }
    @Override
    public double price() {
        double price = SUPREME_SMALL_PRICE;
        if(size == Size.MEDIUM){
            return price + Size.MEDIUM.getPriceAdd();
        }
        if(size == Size.LARGE){
            return price + Size.LARGE.getPriceAdd();
        }
        return price;
    }
}
