package com.ideffix.green.tesla.ing.atm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ideffix.green.tesla.ing.server.HttpController;
import java.util.List;


public class AtmController extends HttpController<List<Task>, List<ATM>> {
    private final AtmService service = new AtmService();

    public AtmController(String path) {
        super(path, new TypeReference<>() {});
    }

    @Override
    public List<ATM> execute(List<Task> tasks) {
        return service.calculateOrder(tasks);
    }
}
