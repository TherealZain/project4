package com.pizzashop.project4.pizzas;

import com.pizzashop.project4.enums.Sauce;
import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.enums.Toppings;

import java.util.ArrayList;

public class BuildYourOwn extends Pizza{
    private static final int MAX_TOPPINGS = 7;
    private ArrayList<Toppings> toppings = new ArrayList<>();
    private static final double EXTRA_TOPPING_COST = 1.49;
    public BuildYourOwn() {
        this.toppings = new ArrayList<>();
    }

    public void setSize(Size size) {
        this.size = size; // Assuming 'size' is a field in the Pizza class
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
        return price;
    }

    public void addToppings(Toppings topping){
        toppings.add(topping);
    }

    public void removeToppings(Toppings topping){

        toppings.remove(topping);
    }
}

