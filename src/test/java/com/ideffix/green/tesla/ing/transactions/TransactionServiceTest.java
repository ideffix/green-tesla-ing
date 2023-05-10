package com.ideffix.green.tesla.ing.transactions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ideffix.green.tesla.ing.tests.Json;
import com.ideffix.green.tesla.ing.tests.Tester;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {
    private final TransactionService transactionService = new TransactionService();

    @Test
    public void example1() throws IOException {
        List<Transaction> input = Json.fileToObject("src/test/resources/transactions/example_request.json",  new TypeReference<>(){});
        List<AccountRecord> expected = Json.fileToObject("src/test/resources/transactions/example_response.json", new TypeReference<>(){});

        List<AccountRecord> output = transactionService.executeReport(input);

        assertArrayEquals(expected.toArray(), output.toArray());
    }

    @Test
    public void performanceTest() {
        List<Transaction> input = InputDataGenerator.generate(1_000_000);

        Tester tester = new Tester(() -> transactionService.executeReport(input), 10);
        tester.run(/* withPrint=*/true);
    }

}