package com.narxoz.rpg.factory;
import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.fire.FireShield;
import com.narxoz.rpg.combat.fire.FlameBreath;
import com.narxoz.rpg.combat.fire.MeteorStorm;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.loot.fire.FireLootTable;

import java.util.ArrayList;
import java.util.List;

public class FireComponentFactory implements EnemyComponentFactory{
    @Override
    public List<Ability> createAbilities(){
        List<Ability> abilities = new ArrayList<>();
        abilities.add(new FlameBreath());
        abilities.add(new FireShield());
        abilities.add(new MeteorStorm());
        return abilities;
    }
    @Override
    public LootTable createLootTable(){
        return new FireLootTable();
    }
    @Override
    public String createAIBehavior(){
        return "AGGRESSIVE";
    }


}
