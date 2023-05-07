package com.ideffix.green.tesla.ing.onlinegame;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ideffix.green.tesla.ing.tests.Json;
import com.ideffix.green.tesla.ing.tests.Range;
import com.ideffix.green.tesla.ing.tests.Tester;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OnlineGameServiceTest {
    private final OnlineGameService onlineGameService = new OnlineGameService();

    @Test
    public void example1() throws IOException {
        Players input = Json.fileToObject("src/test/resources/onlinegame/example_request.json",  new TypeReference<>(){});
        List<List<Clan>> expected = Json.fileToObject("src/test/resources/onlinegame/example_response.json", new TypeReference<>(){});

        List<List<Clan>> output = onlineGameService.calculateOrder(input);

        assertArrayEquals(expected.toArray(), output.toArray());
    }

    @Test
    public void performanceTest() {
        Players input = InputDataGenerator.generate(1000, 20_000, new Range(1, 1000), new Range(1, 100_00));

        Tester tester = new Tester(() -> onlineGameService.calculateOrder(input), 10);
        tester.run(/* withPrint=*/true);
    }

}