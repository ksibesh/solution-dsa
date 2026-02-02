package com.solution.dsa.stripe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private String name;
    private int balance;

    @Override
    public String toString() {
        return name + ": " + balance;
    }
}
