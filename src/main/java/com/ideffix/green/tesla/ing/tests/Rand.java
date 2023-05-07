package com.ideffix.green.tesla.ing.tests;

public class Rand {

    public static int randomInt(Range range) {
        return (int) ((Math.random() * (range.max() + 1 - range.min())) + range.min());
    }

    public static float randomFloat(Range range) {
        return (float)((Math.random() * (range.max() + 1 - range.min())) + range.min());
    }

    public static char randomChar(Range range) {
        return (char) randomInt(range);
    }

    public static String randomWord(int characterCount, Range charRange) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < characterCount; i++) {
            sb.append(randomChar(charRange));
        }

        return sb.toString();
    }
}
