package com.ideffix.green.tesla.ing.tests;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class AlgoComparer {

    public static <T> void compare(
            String firstName,
            Consumer<T> firstAlgo,
            String secondName,
            Consumer<T> secondAlgo,
            Supplier<T> dataSupplier,
            int repetitions) {
        T data = dataSupplier.get();
        Tester tester1 = new Tester(() -> firstAlgo.accept(data) ,repetitions);
        Tester tester2 = new Tester(() -> secondAlgo.accept(data) ,repetitions);

        System.out.println(firstName + " statistics:");
        var stats1 = tester1.run(/* withPrint=*/true);
        System.out.println(secondName + " statistics:");
        var stats2 = tester2.run(/* withPrint=*/true);

        printBetterResult(stats1.getAverage() < stats2.getAverage(), firstName, secondName, "average time", (int) Math.abs(stats1.getAverage() - stats2.getAverage()));
        printBetterResult(stats1.getMin() < stats2.getMin(), firstName, secondName, "min time", (int) Math.abs(stats1.getMin() - stats2.getMin()));
        printBetterResult(stats1.getMax() < stats2.getMax(), firstName, secondName, "max time", (int) Math.abs(stats1.getMax() - stats2.getMax()));


    }

    private static void printBetterResult(boolean firstIsBetter, String name1, String name2, String desc, int by) {
        String betterName = firstIsBetter ? name1 : name2;
        System.out.printf("%s is better in %s by %s ms\n", betterName, desc, by);
    }
}
