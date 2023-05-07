package com.ideffix.green.tesla.ing.atm;

import com.ideffix.green.tesla.ing.tests.Rand;
import com.ideffix.green.tesla.ing.tests.Range;

import java.util.ArrayList;
import java.util.List;

public class InputDataGenerator {

    public static List<Task> generate(int numberOfTasks, Range regionRange, Range atmIdRange) {
        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i < numberOfTasks; i++) {
            tasks.add(new Task(Rand.randomInt(regionRange), randomRequestType(), Rand.randomInt(atmIdRange)));
        }

        return tasks;
    }

    private static RequestType randomRequestType() {
        return switch (Rand.randomInt(new Range(0, 3))) {
            case 0 -> RequestType.FAILURE_RESTART;
            case 1 -> RequestType.PRIORITY;
            case 2 -> RequestType.SIGNAL_LOW;
            default -> RequestType.STANDARD;
        };

    }
}
