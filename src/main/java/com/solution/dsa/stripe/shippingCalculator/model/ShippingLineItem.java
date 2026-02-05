package com.solution.dsa.stripe.shippingCalculator.model;

import lombok.Data;

@Data
public class ShippingLineItem {
    private String productCode;
    private int qty;

    public ShippingLineItem(String productCode, int qty) {
        this.productCode = productCode;
        this.qty = qty;
    }
}
