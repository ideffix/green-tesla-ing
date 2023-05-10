package com.ideffix.green.tesla.ing.onlinegame;

import java.util.*;

public class OnlineGameSkippableService {

    public List<List<Clan>> calculateOrder(Players players) {
        players.clans().sort(Clan::compareTo);
        SkippableLinkedList<List<Clan>> result = new SkippableLinkedList<>();

        if (players.clans().size() == 0) {
            return new ArrayList<>();
        }

        Map<Integer, Integer> groupCountMap = new HashMap<>();
        result.add(new ArrayList<>());

        for (Clan clan : players.clans()) {
            var optionalNode = findFittingGroupNode(result, groupCountMap, clan, players.groupCount());
            SkippableLinkedList.Node<List<Clan>> node;
            if (optionalNode.isPresent()) {
                // Clan can be added to an existing group
                node = optionalNode.get();
                List<Clan> group = node.getValue();
                int newGroupCount = groupCountMap.getOrDefault(node.getIndex(), 0) + clan.numberOfPlayers();
                groupCountMap.put(node.getIndex(), newGroupCount);
                group.add(clan);
            } else {
                // Cannot add current clan to an existing groups - create new one.
                List<Clan> group = new ArrayList<>();
                group.add(clan);
                node = result.add(group);
                groupCountMap.put(node.getIndex(), clan.numberOfPlayers());
            }
            int groupCount = groupCountMap.getOrDefault(node.getIndex(), 0);
            // group is full, no need to iterate over it as it cannot get more clans
            if (groupCount == players.groupCount()) {
                node.skip();
            }
        }

        return result.toList();
    }

    private Optional<SkippableLinkedList.Node<List<Clan>>> findFittingGroupNode(SkippableLinkedList<List<Clan>> groupsSoFar, Map<Integer, Integer> groupCountMap, Clan clan, int groupCount) {
        for (SkippableLinkedList.Node<List<Clan>> group : groupsSoFar) {
            int playersInGroup = groupCountMap.getOrDefault(group.getIndex(), 0);
            int playersWithCurrentClan = playersInGroup + clan.numberOfPlayers();
            boolean canClanFitIntoGroup = playersWithCurrentClan <= groupCount;
            if (canClanFitIntoGroup) {
                return Optional.of(group);
            }
        }
        return Optional.empty();
    }
}
