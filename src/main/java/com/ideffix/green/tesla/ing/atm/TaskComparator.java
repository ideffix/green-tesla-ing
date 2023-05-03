package com.ideffix.green.tesla.ing.atm;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task>  {

    @Override
    public int compare(Task t1, Task t2) {
        if (t1.region() == t2.region()) {
            return t1.requestType().getPriority() - t2.requestType().getPriority();
        }
        return t1.region() - t2.region();
    }
}
