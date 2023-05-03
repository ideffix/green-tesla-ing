package com.ideffix.green.tesla.ing.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;

public class Tester {
    private final Runnable runnable;
    private final int testRepetition;
    private final List<Long> executionTimes = new ArrayList<>();

    public Tester(Runnable runnable, int testRepetition) {
        this.runnable = runnable;
        this.testRepetition = testRepetition;
    }

    public LongSummaryStatistics run() {
        return run(false);
    }

    public LongSummaryStatistics run(boolean withPrint) {
        for (int i = 0; i < testRepetition; i++) {
            long start = System.currentTimeMillis();

            runnable.run();

            long stop = System.currentTimeMillis();
            executionTimes.add(stop-start);
        }

        var stats = executionTimes.stream().mapToLong(Long::valueOf).summaryStatistics();

        if (withPrint) {
            print(stats);
        }

        return stats;
    }

    private void print(LongSummaryStatistics stats) {
        System.out.println("Number of executions: " + stats.getCount());
        System.out.println("Average execution time: " + stats.getAverage() + " ms");
        System.out.println("Min execution time: " + stats.getMin() + " ms");
        System.out.println("Max execution time: " + stats.getMax() + " ms");
    }


}
