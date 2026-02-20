package com.narxoz.rpg.combat.shadow;

import com.narxoz.rpg.combat.Ability;

public class DarkNova implements Ability {
    private final String name = "DarkNova";
    private final int damage = 220;
    private final String description = "Ultimate AoE: shadow explosion damage";
    @Override public String getName(){return name;}
    @Override public int getDamage(){return damage;}
    @Override public String getDescription(){return description;}

    @Override
    public Ability clone(){
        return new DarkNova();
    }
}
