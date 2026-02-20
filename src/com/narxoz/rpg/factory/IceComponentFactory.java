package com.narxoz.rpg.factory;
import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.ice.Blizzard;
import com.narxoz.rpg.combat.ice.FrostBreath;
import com.narxoz.rpg.combat.ice.IceShield;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.loot.ice.IceLootTable;

import java.util.ArrayList;
import java.util.List;

public class IceComponentFactory implements EnemyComponentFactory{
    @Override
    public List<Ability> createAbilities(){
        List<Ability> abilities = new ArrayList<>();
        abilities.add(new FrostBreath());
        abilities.add(new IceShield());
        abilities.add(new Blizzard());
        return abilities;
    }
    @Override
    public LootTable createLootTable(){
        return new IceLootTable();
    }
    @Override
    public String createAIBehavior(){
        return "DEFENSIVE";
    }

}
