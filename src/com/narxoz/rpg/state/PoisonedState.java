package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class PoisonedState implements HeroState {
    private int turnsRemaining = 2;

    @Override public String getName() { return "Poisoned"; }
    @Override public int modifyOutgoingDamage(int basePower) { return (int)(basePower * 0.8); }
    @Override public int modifyIncomingDamage(int rawDamage) { return rawDamage; }

    @Override
    public void onTurnStart(Hero hero) {
        System.out.println("[Poison] " + hero.getName() + " suffers 3 poison damage.");
        hero.takeDamage(3);
    }

    @Override
    public void onTurnEnd(Hero hero) {
        turnsRemaining--;
        if (turnsRemaining <= 0) {
            System.out.println(hero.getName() + " has recovered from poison.");
            hero.setState(new NormalState());
        }
    }

    @Override public boolean canAct() { return true; }
}