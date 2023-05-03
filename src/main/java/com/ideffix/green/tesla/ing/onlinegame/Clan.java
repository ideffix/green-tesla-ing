package com.ideffix.green.tesla.ing.onlinegame;

public record Clan(int numberOfPlayers, int points) implements Comparable<Clan> {

    @Override
    public int compareTo(Clan otherClan) {
        if (points == otherClan.points) {
            return numberOfPlayers - otherClan.numberOfPlayers;
        }
        return otherClan.points - points;
    }
}
