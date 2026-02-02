package com.solution.dsa.stripe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Output {
    private List<Transaction> transactions;
    private List<Account> updatedAccounts;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder().append("\n>>>> Transactions <<<<\n");
        for (Transaction txn : transactions) builder.append(txn).append("\n");

        builder.append("\n>>>>> Updated Account <<<<<\n");
        for (Account acc : updatedAccounts) builder.append(acc).append("\n");

        return builder.toString();
    }
}
