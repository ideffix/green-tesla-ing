package com.ideffix.green.tesla.ing.transactions;

public record AccountRecord(String account,
                            int debitCount,
                            int creditCount,
                            float balance) {
}
