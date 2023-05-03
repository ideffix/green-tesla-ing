package com.ideffix.green.tesla.ing.transactions;

import java.util.*;

public class TransactionService {

    public List<AccountRecord> executeReport(List<Transaction> transactions) {
        Map<String, Account> accountMap = new HashMap<>();
        PriorityQueue<Account> sortedAccounts = new PriorityQueue<>(Comparator.comparing(Account::getAccount));

        for (var transaction : transactions) {
            if (!accountMap.containsKey(transaction.creditAccount())) {
                var account = new Account(transaction.creditAccount());
                sortedAccounts.add(account);
                accountMap.put(account.getAccount(), account);
            }
            if (!accountMap.containsKey(transaction.debitAccount())) {
                var account = new Account(transaction.debitAccount());
                sortedAccounts.add(account);
                accountMap.put(account.getAccount(), account);
            }

            var creditAccount = accountMap.get(
                    transaction.creditAccount());
            var debitAccount = accountMap.get(transaction.debitAccount());

            creditAccount.credit(transaction.amount());
            debitAccount.debit(transaction.amount());
        }

        List<AccountRecord> result = new ArrayList<>();
        while (!sortedAccounts.isEmpty()) {
            result.add(sortedAccounts.poll().toRecord());
        }

        return result;
    }
}
