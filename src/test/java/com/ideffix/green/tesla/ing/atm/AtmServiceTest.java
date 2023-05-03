package com.ideffix.green.tesla.ing.atm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ideffix.green.tesla.ing.tests.Json;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AtmServiceTest {

    private final AtmService atmService = new AtmService();

    @Test
    public void example1() throws IOException {
        List<Task> input = Json.fileToObject("src/test/resources/atm/example_1_request.json",  new TypeReference<>(){});
        List<ATM> expected = Json.fileToObject("src/test/resources/atm/example_1_response.json", new TypeReference<>(){});

        List<ATM> output = atmService.calculateOrder(input);

        assertArrayEquals(expected.toArray(), output.toArray());
    }

    @Test
    public void example2() throws IOException {
        List<Task> input = Json.fileToObject("src/test/resources/atm/example_2_request.json",  new TypeReference<>(){});
        List<ATM> expected = Json.fileToObject("src/test/resources/atm/example_2_response.json", new TypeReference<>(){});

        List<ATM> output = atmService.calculateOrder(input);

        assertArrayEquals(expected.toArray(), output.toArray());
    }

}