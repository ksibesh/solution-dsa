package com.solution.dsa.stripe.shippingCalculator.model;

import lombok.Data;

import java.util.List;

@Data
public class ShippingMatrix {
    private String productCode;
    private List<CostRange> costRanges;

    public ShippingMatrix(String productCode, List<CostRange> costRanges) {
        this.productCode = productCode;
        this.costRanges = costRanges;
    }
}
