package com.pizzashop.project4.pizzas;

import com.pizzashop.project4.PizzaMaker;
import com.pizzashop.project4.enums.Size;
import com.pizzashop.project4.enums.Toppings;

import static org.junit.Assert.*;

public class BuildYourOwnTest {
    private static final double EXTRA_TOPPING_COST = 1.49;

    @org.junit.Test
    public void testSmallThreeToppings(){
        Pizza test = PizzaMaker.createPizza("BYO");
        test.setSize(Size.SMALL);
        test.addToppings(Toppings.PEPPERONI);
        test.addToppings(Toppings.BEEF);
        test.addToppings(Toppings.HAM);
        double expectedPrice = Size.SMALL.getPriceAdd();
        assertEquals(expectedPrice, test.price(), 0.01);
    }

    @org.junit.Test
    public void testSmallSevenToppingsExtraCheeseExtraSauce(){
        Pizza test = PizzaMaker.createPizza("BYO");
        int EXTRA_TOPPINGS = 4;
        test.setSize(Size.SMALL);
        test.setExtraCheese(true);
        test.setExtraSauce(true);
        test.addToppings(Toppings.CRABMEAT);
        test.addToppings(Toppings.SHRIMP);
        test.addToppings(Toppings.SAUSAGE);
        test.addToppings(Toppings.BEEF);
        test.addToppings(Toppings.BLACKOLIVE);
        test.addToppings(Toppings.CRABMEAT);
        test.addToppings(Toppings.CHICKEN);
        double expectedPrice = Size.SMALL.getPriceAdd();
        expectedPrice += EXTRA_TOPPINGS*EXTRA_TOPPING_COST;
        if (test.extraCheese) {
            expectedPrice++;
        }
        if (test.extraSauce) {
            expectedPrice++;
        }
        assertEquals(expectedPrice, test.price(), 0.01);
    }

    @org.junit.Test
    public void testMediumExtraSauceFourToppings(){
        Pizza test = PizzaMaker.createPizza("BYO");
        test.setSize(Size.MEDIUM);
        test.setExtraSauce(true);
        test.addToppings(Toppings.PEPPERONI);
        test.addToppings(Toppings.BLACKOLIVE);
        test.addToppings(Toppings.CRABMEAT);
        test.addToppings(Toppings.SHRIMP);
        double expectedPrice = Size.SMALL.getPriceAdd() + Size.MEDIUM.getPriceAdd();
        expectedPrice += EXTRA_TOPPING_COST;
        if (test.extraSauce) {
            expectedPrice++;
        }
        assertEquals(expectedPrice, test.price(), 0.01);
    }

    @org.junit.Test
    public void testMediumSixToppings(){
        Pizza test = PizzaMaker.createPizza("BYO");
        int EXTRA_TOPPINGS = 3;
        test.setSize(Size.MEDIUM);
        test.addToppings(Toppings.CRABMEAT);
        test.addToppings(Toppings.SHRIMP);
        test.addToppings(Toppings.SAUSAGE);
        test.addToppings(Toppings.MUSHROOM);
        test.addToppings(Toppings.PINEAPPLE);
        test.addToppings(Toppings.BEEF);
        double expectedPrice = Size.SMALL.getPriceAdd() + Size.MEDIUM.getPriceAdd();
        expectedPrice += EXTRA_TOPPINGS*EXTRA_TOPPING_COST;
        assertEquals(expectedPrice, test.price(), 0.01);
    }

    @org.junit.Test
    public void testLargeFiveToppingsExtraSauce(){
        Pizza test = PizzaMaker.createPizza("BYO");
        test.setSize(Size.LARGE);
        test.setExtraSauce(true);
        int EXTRA_TOPPINGS = 2;
        test.addToppings(Toppings.PEPPERONI);
        test.addToppings(Toppings.BLACKOLIVE);
        test.addToppings(Toppings.CRABMEAT);
        test.addToppings(Toppings.SHRIMP);
        test.addToppings(Toppings.SAUSAGE);
        double expectedPrice = Size.SMALL.getPriceAdd() + Size.LARGE.getPriceAdd();
        expectedPrice += EXTRA_TOPPINGS*EXTRA_TOPPING_COST;
        if (test.extraSauce) {
            expectedPrice++;
        }
        assertEquals(expectedPrice, test.price(), 0.01);
    }

    @org.junit.Test
    public void testLargeSevenToppingsExtraCheese(){
        Pizza test = PizzaMaker.createPizza("BYO");
        test.setSize(Size.LARGE);
        test.setExtraCheese(true);
        int EXTRA_TOPPINGS = 4;
        test.addToppings(Toppings.PEPPERONI);
        test.addToppings(Toppings.BLACKOLIVE);
        test.addToppings(Toppings.CRABMEAT);
        test.addToppings(Toppings.SHRIMP);
        test.addToppings(Toppings.SAUSAGE);
        test.addToppings(Toppings.CHICKEN);
        test.addToppings(Toppings.HAM);
        double expectedPrice = Size.SMALL.getPriceAdd() + Size.LARGE.getPriceAdd();
        expectedPrice += EXTRA_TOPPINGS*EXTRA_TOPPING_COST;
        if (test.extraCheese) {
            expectedPrice++;
        }assertEquals(expectedPrice, test.price(), 0.01);
    }

}