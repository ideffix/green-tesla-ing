package com.ideffix.green.tesla.ing.atm;

import java.util.*;

public class AtmService {

    public List<ATM> calculateOrder(List<Task> tasks) {
        tasks.sort(new TaskComparator());

        List<ATM> result = new ArrayList<>();

        List<ATM> failureRestartAtms = new ArrayList<>();
        List<ATM> priorityAtms = new ArrayList<>();
        List<ATM> signalLowAtms = new ArrayList<>();
        List<ATM> standardAtms = new ArrayList<>();

        Set<ATM> alreadyUsedAtm = new HashSet<>();

        int lastRegion = -1;
        for (Task task : tasks) {
            if (task.region() != lastRegion) {
                result.addAll(failureRestartAtms);
                result.addAll(priorityAtms);
                result.addAll(signalLowAtms);
                result.addAll(standardAtms);

                failureRestartAtms = new ArrayList<>();
                priorityAtms = new ArrayList<>();
                signalLowAtms = new ArrayList<>();
                standardAtms = new ArrayList<>();
            }

            ATM atm = new ATM(task.region(), task.atmId());
            if (alreadyUsedAtm.contains(atm)) {
                continue;
            }
            alreadyUsedAtm.add(atm);

            switch (task.requestType()) {
                case STANDARD -> standardAtms.add(atm);
                case PRIORITY -> priorityAtms.add(atm);
                case SIGNAL_LOW -> signalLowAtms.add(atm);
                case FAILURE_RESTART -> failureRestartAtms.add(atm);
            }
        }
        result.addAll(failureRestartAtms);
        result.addAll(priorityAtms);
        result.addAll(signalLowAtms);
        result.addAll(standardAtms);

        return result;
    }

}
