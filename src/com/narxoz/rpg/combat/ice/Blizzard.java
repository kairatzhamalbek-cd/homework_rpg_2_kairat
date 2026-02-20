package com.narxoz.rpg.combat.ice;

import com.narxoz.rpg.combat.Ability;

public class Blizzard implements Ability {
    private final String name = "Blizzard";
    private final int damage = 220;
    private final String description = "Ultimate AoE: heavy ice damage over area.";
    @Override public String getName(){return name;}
    @Override public int getDamage(){return damage;}
    @Override public String getDescription(){return description;}

    @Override
    public Ability clone(){
        return new Blizzard();
    }

}
