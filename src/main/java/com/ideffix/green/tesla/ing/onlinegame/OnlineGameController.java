package com.ideffix.green.tesla.ing.onlinegame;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ideffix.green.tesla.ing.server.HttpController;

import java.util.List;

public class OnlineGameController extends HttpController<Players, List<List<Clan>>> {
    private final OnlineGameService service = new OnlineGameService();

    public OnlineGameController(String path) {
        super(path, new TypeReference<>() {});
    }

    @Override
    public List<List<Clan>> execute(Players players) {
        return service.calculateOrder(players);
    }
}
