package com.ideffix.green.tesla.ing.transactions;

import java.math.BigDecimal;

public class Account {
    private final String account;
    private int debitCount;
    private int creditCount;
    private BigDecimal balance = new BigDecimal(0);

    public Account(String account) {
        this.account = account;
    }

    public void debit(float amount) {
        balance = balance.subtract(BigDecimal.valueOf(amount));
        debitCount++;
    }

    public void credit(float amount) {
        balance = balance.add(BigDecimal.valueOf(amount));
        creditCount++;
    }

    public String getAccount() {
        return account;
    }

    public AccountRecord toRecord() {
        return new AccountRecord(account, debitCount, creditCount, balance.floatValue());
    }

}
