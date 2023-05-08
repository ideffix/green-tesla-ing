package com.ideffix.green.tesla.ing.onlinegame;

import java.util.*;

public class OnlineGameService {

    public List<List<Clan>> calculateOrder(Players players) {
        players.clans().sort(Clan::compareTo);
        List<List<Clan>> result = new ArrayList<>();

        if (players.clans().size() == 0) {
            return result;
        }

        Map<Integer, Integer> groupCountMap = new HashMap<>();
        result.add(new ArrayList<>());

        for (Clan clan : players.clans()) {
            int fittingGroupIndex = findFittingGroupIndex(result, groupCountMap, clan, players.groupCount());
            if (fittingGroupIndex >= 0) {
                // Clan can be added to an existing group
                groupCountMap.put(fittingGroupIndex, groupCountMap.getOrDefault(fittingGroupIndex, 0) + clan.numberOfPlayers());
                result.get(fittingGroupIndex).add(clan);
            } else {
                // Cannot add current clan to an existing groups - create new one.
                List<Clan> newGroup = new ArrayList<>();
                newGroup.add(clan);
                groupCountMap.put(result.size(), clan.numberOfPlayers());
                result.add(newGroup);
            }
        }

        return result;
    }

    private Integer findFittingGroupIndex(List<List<Clan>> groupsSoFar, Map<Integer, Integer> groupCountMap, Clan clan, int groupCount) {
        for (int i = 0; i < groupsSoFar.size(); i++) {
            int playersInGroup = groupCountMap.getOrDefault(i, 0);
            int playersWithCurrentClan = playersInGroup + clan.numberOfPlayers();
            boolean canClanFitIntoGroup = playersWithCurrentClan <= groupCount;
            if (canClanFitIntoGroup) {
                return i;
            }
        }
        return -1;
    }
}
