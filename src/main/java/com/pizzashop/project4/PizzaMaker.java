package com.pizzashop.project4;

import com.pizzashop.project4.pizzas.BuildYourOwn;
import com.pizzashop.project4.pizzas.Pizza;

public class PizzaMaker {
    public static Pizza createPizza(String pizzaType) {
        if(pizzaType.equals("BYO")){
            return new BuildYourOwn();
        }
        return new BuildYourOwn();
    }
}
