package com.ideffix.green.tesla.ing.transactions;

import com.ideffix.green.tesla.ing.tests.Rand;
import com.ideffix.green.tesla.ing.tests.Range;

import java.util.ArrayList;
import java.util.List;

public class InputDataGenerator {

    private static final Range CAPITAL_LETTERS_RANGE = new Range('A', 'Z');

    public static List<Transaction> generate(int transactionCount) {
        List<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < transactionCount; i++) {
            transactions.add(
                    new Transaction(
                            Rand.randomWord(2, CAPITAL_LETTERS_RANGE),
                            Rand.randomWord(2, CAPITAL_LETTERS_RANGE),
                            Rand.randomFloat(new Range(0, 10_000))));
        }

        return transactions;
    }
}
