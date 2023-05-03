package com.ideffix.green.tesla.ing.transactions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ideffix.green.tesla.ing.server.HttpController;

import java.util.List;

public class TransactionController extends HttpController<List<Transaction>, List<AccountRecord>> {
    private final TransactionService service = new TransactionService();

    public TransactionController(String path) {
        super(path,  new TypeReference<>() {});
    }

    @Override
    public List<AccountRecord> execute(List<Transaction> transactions) {
        return service.executeReport(transactions);
    }
}
