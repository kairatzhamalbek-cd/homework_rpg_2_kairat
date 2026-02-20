package com.narxoz.rpg.builder;
import com.narxoz.rpg.factory.EnemyComponentFactory;

public class EnemyDirector {
    public void constructBasicEnemy(EnemyBuilder builder, String name, String element,
                                    EnemyComponentFactory factory) {

        builder.setName(name)
                .setElement(element)
                .setAIBehavior(factory.createAIBehavior())
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable());
    }
    public void constructBossEnemy(EnemyBuilder builder, String name, String element,
                                   EnemyComponentFactory factory) {

        builder.setName(name)
                .setElement(element)
                .setAIBehavior(factory.createAIBehavior())
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable());
    }
}
