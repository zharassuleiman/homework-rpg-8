package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class StunnedState implements HeroState {
    private int duration = 1;

    @Override public String getName() { return "Stunned"; }
    @Override public int modifyOutgoingDamage(int basePower) { return 0; }
    @Override public int modifyIncomingDamage(int rawDamage) { return rawDamage + 5; }

    @Override public void onTurnStart(Hero hero) { }

    @Override
    public void onTurnEnd(Hero hero) {
        duration--;
        if (duration <= 0) {
            hero.setState(new NormalState());
        }
    }

    @Override public boolean canAct() { return false; }
}
