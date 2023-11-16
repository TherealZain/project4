package com.pizzashop.project4;

public enum Size {
    SMALL(0, 8.99),
    MEDIUM(1, 10.99),
    LARGE(2, 12.99);
    private final int sizeCode;
    private final double basePrice;

    private Size(int sizeCode, double basePrice) {
        this.sizeCode = sizeCode;
        this.basePrice = basePrice;
    }

    public int getSizeCode() {
        return this.sizeCode;
    }

    public double getBasePrice() {
        return this.basePrice;
    }
}
