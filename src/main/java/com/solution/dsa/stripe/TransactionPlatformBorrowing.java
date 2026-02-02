package com.solution.dsa.stripe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Question: Transactions and Platform Borrowing
 * Role Classification: SDE2, Senior, Staff Software Engineer.
 * <p>
 * Problem Statement:
 * <p>
 * Part 1: Given a series of transactions (account name, timestamp, currency, and amount),
 * calculate the final non-zero balances for all users.
 * <p>
 * Part 2: Modify the system to reject transactions that would cause an account's balance to fall below zero.
 * These should be stored in a rejected_transactions list.
 * <p>
 * Part 3: Introduce a platform_id account. If a user's transaction would result in a negative balance,
 * they can "borrow" from the platform to cover the exact deficit. You must track the max_reserve
 * (the peak cumulative amount borrowed from the platform at any single point in time) and reduce the platform's
 * balance accordingly.
 */
public class TransactionPlatformBorrowing {
    public static class Transaction {
        String accountId;
        int amount;
        long timestamp;

        public Transaction(String accountId, int amount, long timestamp) {
            this.accountId = accountId;
            this.amount = amount;
            this.timestamp = timestamp;
        }
    }

    public static class ProcessResult {
        Map<String, Integer> balance = new HashMap<>();
        List<Transaction> rejected = new ArrayList<>();
        int maxReserve;
    }

    public ProcessResult processTransactions(List<Transaction> transactions, String platformId) {
        ProcessResult result = new ProcessResult();
        for (Transaction transaction : transactions) {
            
        }
        return null;
    }
}
