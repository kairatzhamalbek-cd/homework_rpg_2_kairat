package com.narxoz.rpg.builder;

import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.factory.EnemyComponentFactory;


public class EnemyDirector {

    public Enemy createMinion(EnemyBuilder builder, String name, String element, EnemyComponentFactory factory) {
        builder.setName(name)
                .setElement(element)
                .setAIBehavior(factory.createAIBehavior())
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable());

        return builder.build();
    }

    public Enemy createElite(EnemyBuilder builder, String name, String element, EnemyComponentFactory factory) {
        builder.setName(name)
                .setElement(element)
                .setAIBehavior(factory.createAIBehavior() + "_ELITE")
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .setHealth(200);

        return builder.build();
    }

    public Enemy createMiniBoss(EnemyBuilder builder, String name, String element, EnemyComponentFactory factory) {
        builder.setName(name)
                .setElement(element)
                .setAIBehavior(factory.createAIBehavior() + "_MINIBOSS")
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .setHealth(350);

        return builder.build();
    }

    public Enemy createRaidBoss(EnemyBuilder builder, String name, String element, EnemyComponentFactory factory) {
        builder.setName(name)
                .setElement(element)
                .setAIBehavior(factory.createAIBehavior() + "_RAIDBOSS")
                .setAbilities(factory.createAbilities())
                .addPhase(1, 700)
                .addPhase(2, 400)
                .addPhase(3, 150)
                .setLootTable(factory.createLootTable())
                .setHealth(1200)
                .setDamage(250)
                .setDefense(90)
                .setSpeed(35);

        return builder.build();
    }
}
