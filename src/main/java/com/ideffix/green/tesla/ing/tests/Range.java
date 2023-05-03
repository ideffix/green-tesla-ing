package com.ideffix.green.tesla.ing.tests;

public record Range(int min, int max) {

    public int generate() {
        return (int) ((Math.random() * (max + 1 - min)) + min);
    }
}
