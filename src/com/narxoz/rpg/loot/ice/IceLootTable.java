package com.narxoz.rpg.loot.ice;

import com.narxoz.rpg.loot.LootTable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IceLootTable implements LootTable {
    private final List<String> items = new ArrayList<>(
            Arrays.asList("Ice Gem", "Frost Scale", "Ice Rune")
    );
    private final int goldDrop = 280;
    private final int experienceDrop = 520;

    @Override public List<String> getItems(){return new ArrayList<>(items);}
    @Override public int getGoldDrop(){return goldDrop;}
    @Override public int getExperienceDrop(){return experienceDrop;}

    @Override
    public LootTable clone(){
        return new IceLootTable();
    }
}
