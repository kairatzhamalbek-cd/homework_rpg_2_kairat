package com.narxoz.rpg.combat.fire;

import com.narxoz.rpg.combat.Ability;

public class FlameBreath implements Ability {
    private final String name = "FlameBreath";
    private final int damage = 120;
    private final String description = "AoE fire damage with burn effect";

    @Override public String getName(){return name;}
    @Override public int getDamage() {return damage;}
    @Override public String getDescription() {return description;}

    @Override public Ability clone(){
        return new FlameBreath();
    }

}
