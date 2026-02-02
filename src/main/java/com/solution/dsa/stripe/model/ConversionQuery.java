package com.solution.dsa.stripe.model;

import lombok.Data;

@Data
public class ConversionQuery {
    private String from;
    private String to;

    public ConversionQuery(String from, String to) {
        this.from = from;
        this.to = to;
    }
}
