package com.pizzashop.project4.pizzas;

import com.pizzashop.project4.enums.Sauce;
import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.enums.Toppings;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Pizza {
    protected ArrayList<Toppings> toppings; //Topping is a enum class
    protected Size size; //Size is a enum class
    protected Sauce sauce; //Sauce is a enum class
    protected boolean extraSauce;
    protected boolean extraCheese;

    public abstract double price(); //polymorphism

    public abstract void addToppings(Toppings topping);
    public abstract void removeToppings(Toppings topping);

    public void setSize(Size newSize) {
    }
    public void setSauce(Sauce newSauce) {
    }
    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }
    public void setExtraSauce(boolean extraSauce){
        this.extraSauce = extraSauce;
    }
    public ArrayList<Toppings> getToppings() {
        return toppings;
    }
    public Sauce getSauce() {
        return sauce;
    }
    @Override
    public abstract String toString();
}
