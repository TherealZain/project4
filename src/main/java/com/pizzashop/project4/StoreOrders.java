package com.pizzashop.project4;

import java.util.ArrayList;

public class StoreOrders {
    private ArrayList<Order> storeOrders;
    private static int nextOrderNum;

    public StoreOrders(){
        nextOrderNum = 1;
        storeOrders = new ArrayList<>();
    }

    public int getNextOrderNum(){
        int currOrderNum = nextOrderNum;
        nextOrderNum++;
        return currOrderNum;
    }

}
