package com.ideffix.green.tesla.ing.atm;

import java.util.*;

public class AtmService {

    public List<ATM> calculateOrder(List<Task> tasks) {
        tasks.sort(new TaskComparator());

        Set<ATM> seen = new HashSet<>();
        List<ATM> result = new ArrayList<>();

        for (Task task : tasks) {
            ATM atm = new ATM(task.region(), task.atmId());
            if (!seen.contains(atm)) {
                seen.add(atm);
                result.add(atm);
            }
        }

        return result;
    }

}
