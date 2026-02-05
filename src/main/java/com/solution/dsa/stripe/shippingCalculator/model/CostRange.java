package com.solution.dsa.stripe.shippingCalculator.model;

import lombok.Data;

@Data
public class CostRange {
    private int start;
    private int end;
    private double cost;
    private CostRangeType type;

    public CostRange(int start, int end, double cost, CostRangeType type) {
        this.start = start;
        this.end = end;
        this.cost = cost;
        this.type = type;
    }
}
