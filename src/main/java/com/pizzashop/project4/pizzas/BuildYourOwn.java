package com.pizzashop.project4.pizzas;

import com.pizzashop.project4.enums.Sauce;
import com.pizzashop.project4.enums.Size;

public class BuildYourOwn extends Pizza{
    private static final int MAX_TOPPINGS = 7;
    private static final double EXTRA_TOPPING_COST = 1.49;
    public BuildYourOwn() {
    }
    @Override
    public double price() {
        double price = Size.SMALL.getPriceAdd();
        if(size == Size.MEDIUM){
            price += Size.MEDIUM.getPriceAdd();
        }else
        if(size == Size.LARGE){
            price += Size.MEDIUM.getPriceAdd();
        }
        if(toppings.size() > 7){
            price += (toppings.size() - MAX_TOPPINGS)*EXTRA_TOPPING_COST;
        }
        return price;
    }
}
