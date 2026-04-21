package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import java.util.List;

public class RestFloor extends TowerFloor {
    @Override protected String getFloorName() { return "Safe Haven"; }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("A quiet room with a warm campfire.");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("The party rests peacefully for the night.");
        for (Hero h : party) {
            if (h.isAlive()) h.heal(25);
        }
        return new FloorResult(true, 0, "Party is fully rested.");
    }

    @Override
    protected boolean shouldAwardLoot(FloorResult result) {
        return false;
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
    }
}
