package com.solution.dsa.stripe;

import com.solution.dsa.stripe.model.Account;
import com.solution.dsa.stripe.model.Input;
import com.solution.dsa.stripe.model.Output;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveFundsTest {

    @Test
    void testBalanceThreshold() {
        MoveFunds obj = new MoveFunds();
        List<Account> accounts = new ArrayList<>() {{
            add(new Account("AU", 80));
            add(new Account("FR", 70));
            add(new Account("US", 140));
            add(new Account("MX", 110));
            add(new Account("SG", 120));
        }};
        Input input = new Input(accounts);
        System.out.println(input);

        int thresholdBalance = 100;
        Output output = obj.moveFunds(input, thresholdBalance);
        System.out.println(output);
        for (Account acc : output.getUpdatedAccounts()) {
            assertTrue(acc.getBalance() >= thresholdBalance);
        }
    }
}
