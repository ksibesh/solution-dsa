package com.solution.dsa.stripe.model;

import lombok.Data;

@Data
public class ConversionRate {
    private String from;
    private String to;
    private double rate;
    private int category;   // 0 - received, 1 - calculated

    public ConversionRate(String from, String to, double rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.category = 0;
    }

    public ConversionRate(String from, String to, double rate, int category) {
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.category = category;
    }
}
