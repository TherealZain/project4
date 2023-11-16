package com.pizzashop.project4;

import java.util.ArrayList;

public class StoreOrders {
    private ArrayList<Order> storeOrders;
    private static int nextOrderNum;

    public StoreOrders(){
        nextOrderNum = 0;
        storeOrders = new ArrayList<>();
    }

}
