package com.pizzashop.project4.pizzas;

import com.pizzashop.project4.enums.Sauce;
import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.enums.Toppings;
import java.util.ArrayList;

public class Pepperoni extends Pizza{

    private static final double PEPPERONI_SMALL_PRICE = 10.99;
    public Pepperoni(){
        toppings = new ArrayList<>();
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
        return null;
    }
}
