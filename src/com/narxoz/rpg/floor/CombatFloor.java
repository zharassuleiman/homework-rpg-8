package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;
import java.util.List;

public class CombatFloor extends TowerFloor {
    private Monster enemy;

    @Override protected String getFloorName() { return "Goblin Barracks"; }

    @Override
    protected void setup(List<Hero> party) {
        enemy = new Monster("Armored Goblin", 45, 12);
        System.out.println("A hostile " + enemy.getName() + " readies its weapon!");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        int initialHpSum = party.stream().mapToInt(Hero::getHp).sum();

        while(enemy.isAlive() && party.stream().anyMatch(Hero::isAlive)) {

            for (Hero h : party) {
                if (h.isAlive()) h.takeTurn(enemy);
                if (!enemy.isAlive()) break;
            }

            if (enemy.isAlive()) {
                Hero target = party.stream().filter(Hero::isAlive).findFirst().orElse(null);
                if (target != null) enemy.attack(target);
            }
        }

        int damageTaken = initialHpSum - party.stream().mapToInt(Hero::getHp).sum();
        boolean cleared = !enemy.isAlive();
        return new FloorResult(cleared, Math.max(0, damageTaken), cleared ? "Enemy vanquished." : "Party was defeated.");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("Loot: Found minor health potions! Everyone heals 10 HP.");
        party.stream().filter(Hero::isAlive).forEach(h -> h.heal(10));
    }
}
