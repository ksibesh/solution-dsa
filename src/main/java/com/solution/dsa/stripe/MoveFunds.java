package com.solution.dsa.stripe;

import com.solution.dsa.stripe.model.Account;
import com.solution.dsa.stripe.model.Input;
import com.solution.dsa.stripe.model.Output;
import com.solution.dsa.stripe.model.Transaction;

import java.util.*;

public class MoveFunds {

    public Output moveFunds(Input input, int thresholdBalance) {
        // TODO: validate the input

        Comparator<Account> accountComparator = Comparator.comparingInt(Account::getBalance);

        PriorityQueue<Account> belowThreshold = new PriorityQueue<>(accountComparator);
        PriorityQueue<Account> aboveThreshold = new PriorityQueue<>(accountComparator.reversed());

        for (Account acc : input.getAccounts()) {
            if (acc.getBalance() > thresholdBalance) aboveThreshold.add(acc);
            else if (acc.getBalance() < thresholdBalance) belowThreshold.add(acc);
        }

        List<Transaction> transactions = new ArrayList<>();
        while (!belowThreshold.isEmpty()) {
            Account acc = belowThreshold.poll();
            int requiredAmount = thresholdBalance - acc.getBalance();
            while (requiredAmount > 0 && !aboveThreshold.isEmpty()) {
                Account borrowingAcc = aboveThreshold.poll();
                int availableAmountToBorrow = borrowingAcc.getBalance() - thresholdBalance;
                int lendAmount = Math.min(availableAmountToBorrow, requiredAmount);

                borrowingAcc.setBalance(borrowingAcc.getBalance() - lendAmount);
                acc.setBalance(acc.getBalance() + lendAmount);
                if (availableAmountToBorrow > requiredAmount) aboveThreshold.add(borrowingAcc);
                requiredAmount -= lendAmount;
                transactions.add(new Transaction(borrowingAcc.getName(), acc.getName(), lendAmount));
            }
        }

        return new Output(transactions, input.getAccounts());
    }

}
