package com.narxoz.rpg.floor;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.PoisonedState;
import com.narxoz.rpg.state.StunnedState;
import java.util.List;

public class TrapFloor extends TowerFloor {
    @Override
    protected void announce() {
        System.out.println("\n--- ️ DANGER: " + getFloorName() + "  ---");
    }

    @Override protected String getFloorName() { return "Corridor of Shadows"; }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("The floor is rigged with tripwires and gas vents...");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("Click! The traps activate!");

        if (party.size() > 0 && party.get(0).isAlive()) {
            System.out.println("A dart hits " + party.get(0).getName() + "!");
            party.get(0).setState(new PoisonedState());
        }
        if (party.size() > 1 && party.get(1).isAlive()) {
            System.out.println("A falling rock hits " + party.get(1).getName() + "!");
            party.get(1).setState(new StunnedState());
        }
        return new FloorResult(true, 0, "Traps survived.");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("Loot: Found 50 gold hidden in the mechanism.");
    }
}