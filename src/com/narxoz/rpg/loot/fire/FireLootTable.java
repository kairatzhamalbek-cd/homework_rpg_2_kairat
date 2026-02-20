package com.narxoz.rpg.loot.fire;

import com.narxoz.rpg.loot.LootTable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FireLootTable implements LootTable {
    private final List<String> items = new ArrayList<>(
            Arrays.asList("Fire Gem", "Dragon Scale", "Flame Rune")
    );
    private final int goldDrop = 300;
    private final int experienceDrop = 500;

    @Override public List<String> getItems(){return new  ArrayList<>(items);}
    @Override public int getGoldDrop(){return goldDrop;}
    @Override public int getExperienceDrop(){return experienceDrop;}
    @Override
    public LootTable clone(){
        return new FireLootTable();
    }

}
