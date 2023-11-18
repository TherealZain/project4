package com.pizzashop.project4.enums;

public enum Size {
    SMALL(8.99),
    MEDIUM(2.00),
    LARGE(4.00);
    private final double priceAdd;

    private Size(double priceAdd) {
        this.priceAdd = priceAdd;
    }

    public double getPriceAdd() {
        return this.priceAdd;
    }
}
