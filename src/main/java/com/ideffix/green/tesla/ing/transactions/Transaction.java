package com.ideffix.green.tesla.ing.transactions;

public record Transaction(String debitAccount, String creditAccount, float amount) {
}
