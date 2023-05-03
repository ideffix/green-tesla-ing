package com.ideffix.green.tesla.ing.atm;

public enum RequestType {
    STANDARD(3), PRIORITY(1), SIGNAL_LOW(2), FAILURE_RESTART(0);

    private final int priority;

    RequestType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
