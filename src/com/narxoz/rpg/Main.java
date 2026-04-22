package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.*;
import com.narxoz.rpg.state.PoisonedState;
import com.narxoz.rpg.tower.TowerRunResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== The Haunted Tower: Ascending the Floors ===\n");

        Hero suleiman = new Hero("Suleiman", 80, 16, 2);
        Hero zharas = new Hero("Zharas", 60, 12, 1);
        zharas.setState(new PoisonedState());

        List<Hero> party = new ArrayList<>(Arrays.asList(suleiman, zharas));

        List<TowerFloor> tower = new ArrayList<>();
        tower.add(new CombatFloor());
        tower.add(new RestFloor());
        tower.add(new TrapFloor());
        tower.add(new CombatFloor());

        int floorsCleared = 0;
        boolean reachedTop = true;

        for (TowerFloor floor : tower) {
            if (party.stream().noneMatch(Hero::isAlive)) {
                System.out.println("\n[GAME OVER] The entire party has fallen...");
                reachedTop = false;
                break;
            }

            FloorResult result = floor.explore(party);

            if (result.isCleared()) {
                floorsCleared++;
            } else {
                reachedTop = false;
                break;
            }
        }

        int survivingHeroes = (int) party.stream().filter(Hero::isAlive).count();
        TowerRunResult runResult = new TowerRunResult(floorsCleared, survivingHeroes, reachedTop);

        System.out.println("\n===== TOWER RUN RESULT =====");
        System.out.println("Floors Cleared:  " + runResult.getFloorsCleared() + " / " + tower.size());
        System.out.println("Heroes Survived: " + runResult.getHeroesSurviving());
        System.out.println("Tower Conquered: " + (runResult.isReachedTop() ? "YES" : "NO"));
        System.out.println("============================");
    }
}
