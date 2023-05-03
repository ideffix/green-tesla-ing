package com.ideffix.green.tesla.ing.onlinegame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnlineGameService {

    public List<List<Clan>> calculateOrder(Players players) {
        players.clans().sort(Clan::compareTo);
        List<List<Clan>> result = new ArrayList<>();

        if (players.clans().size() == 0) {
            return result;
        }

        Map<Integer, Integer> groupSizeMap = new HashMap<>();

        result.add(new ArrayList<>());

        for (Clan clan : players.clans()) {
            for (int i = 0; i < result.size(); i++) {
                List<Clan> group = result.get(i);
                int playersCountSoFar = groupSizeMap.getOrDefault(i, 0);
                int playersCountTogether = playersCountSoFar + clan.numberOfPlayers();
                if (playersCountTogether <= players.groupCount()) {
                    groupSizeMap.put(i, playersCountTogether);
                    group.add(clan);
                    break;
                }
                boolean isLast = i == result.size() - 1;
                if (isLast) {
                    List<Clan> newGroup = new ArrayList<>();
                    newGroup.add(clan);
                    groupSizeMap.put(i+1, clan.numberOfPlayers());
                    result.add(newGroup);
                    break;
                }
            }
        }

        return result;
    }
}
