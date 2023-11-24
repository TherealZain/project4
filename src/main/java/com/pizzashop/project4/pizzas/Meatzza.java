package com.pizzashop.project4.pizzas;

import com.pizzashop.project4.enums.Sauce;
import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.enums.Toppings;
import java.util.ArrayList;

public class Meatzza extends Pizza{

    private static final double MEATZZA_SMALL_PRICE = 16.99;
    public Meatzza(){
        toppings = new ArrayList<>();
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.SAUSAGE);
        toppings.add(Toppings.PEPPERONI);
        toppings.add(Toppings.BEEF);
        toppings.add(Toppings.HAM);
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
