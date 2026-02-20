package com.narxoz.rpg.loot.shadow;

import com.narxoz.rpg.loot.LootTable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShadowLootTable implements LootTable {
    private final ArrayList<String> items = new ArrayList<>(
            Arrays.asList("Shadow Gem","Dark Essence", "Shadow Rune")
    );
    private final int goldDrop = 350;
    private final int experienceDrop = 600;

    @Override public List<String> getItems(){return new ArrayList<>(items);}
    @Override public int getGoldDrop(){return goldDrop;}
    @Override public int getExperienceDrop(){return experienceDrop;}

    @Override
    public LootTable clone(){
        return new ShadowLootTable();
    }
}
