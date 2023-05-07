package com.ideffix.green.tesla.ing.onlinegame;

import com.ideffix.green.tesla.ing.tests.Rand;
import com.ideffix.green.tesla.ing.tests.Range;

import java.util.ArrayList;

public class InputDataGenerator {

    public static Players generate(int groupCount, int clansCount, Range numberOfPlayersRange, Range pointsRange) {
        var result = new Players(groupCount, new ArrayList<>());

        for (int i = 0; i < clansCount; i++) {
            result.clans().add(new Clan(Rand.randomInt(numberOfPlayersRange), Rand.randomInt(pointsRange)));
        }

        return result;
    }
}
