package com.solution.dsa.stripe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Input {
    List<Account> accounts;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder().append("\n>>> Account <<<\n");
        for (Account acc : accounts) {
            builder.append(acc).append("\n");
        }
        return builder.toString();
    }
}
