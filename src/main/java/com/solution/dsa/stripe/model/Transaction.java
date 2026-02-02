package com.solution.dsa.stripe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    private String from;
    private String to;
    private int amount;

    @Override
    public String toString() {
        return "from: " + from + ", to: " + to + ", amount: " + amount;
    }
}
