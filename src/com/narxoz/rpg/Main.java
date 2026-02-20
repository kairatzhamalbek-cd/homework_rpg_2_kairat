package com.narxoz.rpg;
import com.narxoz.rpg.builder.BasicEnemyBuilder;
import com.narxoz.rpg.builder.BossEnemyBuilder;
import com.narxoz.rpg.builder.EnemyDirector;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.factory.FireComponentFactory;
import com.narxoz.rpg.factory.IceComponentFactory;
import com.narxoz.rpg.factory.ShadowComponentFactory;
import com.narxoz.rpg.prototype.EnemyRegistry;


public class Main {
    public static void main(String[] args) {
        FireComponentFactory fireFactory = new FireComponentFactory();
        IceComponentFactory iceFactory = new IceComponentFactory();
        ShadowComponentFactory shadowFactory = new ShadowComponentFactory();
        EnemyDirector director = new EnemyDirector();
        BasicEnemyBuilder goblinBuilder = new BasicEnemyBuilder();
        director.constructBasicEnemy(
                goblinBuilder,
                "Goblin Grunt",
                "FIRE",
                fireFactory
        );
        Enemy fireGoblin = goblinBuilder.build();

        goblinBuilder = new BasicEnemyBuilder();
        director.constructBasicEnemy(
                goblinBuilder,
                "Goblin Scout",
                "ICE",
                iceFactory
        );
        Enemy iceGoblin = goblinBuilder.build();

        goblinBuilder = new BasicEnemyBuilder();
        director.constructBasicEnemy(
                goblinBuilder,
                "Goblin Assassin",
                "SHADOW",
                shadowFactory
        );
        Enemy shadowGoblin = goblinBuilder.build();

        System.out.println("\n===== BUILDER + ABSTRACT FACTORY (BASIC ENEMIES) =====");
        fireGoblin.displayInfo();
        System.out.println();
        iceGoblin.displayInfo();
        System.out.println();
        shadowGoblin.displayInfo();

        BossEnemyBuilder bossBuilder = new BossEnemyBuilder();
        director.constructBossEnemy(
                bossBuilder,
                "Ancient Dragon",
                "FIRE",
                fireFactory
        );
        Enemy fireDragonBoss = bossBuilder.build();

        System.out.println("\n===== BUILDER + ABSTRACT FACTORY (BOSS) =====");
        fireDragonBoss.displayInfo();

        EnemyRegistry registry = new EnemyRegistry();
        registry.registerPrototype("FIRE_GOBLIN", fireGoblin);
        registry.registerPrototype("ICE_GOBLIN", iceGoblin);
        registry.registerPrototype("SHADOW_GOBLIN", shadowGoblin);
        registry.registerPrototype("FIRE_DRAGON_BOSS", fireDragonBoss);

        Enemy eliteFireGoblin = registry.createEnemy("FIRE_GOBLIN");
        if (eliteFireGoblin != null) {
            eliteFireGoblin.multiplyStats(1.5);
            eliteFireGoblin.setAIBehavior("AGGRESSIVE_ELITE");
        }
        Enemy miniBossIceGoblin = registry.createEnemy("ICE_GOBLIN");
        if (miniBossIceGoblin != null) {
            miniBossIceGoblin.multiplyStats(2.0);
            miniBossIceGoblin.setAIBehavior("MINI_BOSS");
        }
        Enemy enragedDragon = registry.createEnemy("FIRE_DRAGON_BOSS");
        if (enragedDragon != null) {
            enragedDragon.multiplyStats(1.25);
            enragedDragon.setAIBehavior("ENRAGED_BOSS");
        }
        System.out.println("\n===== PROTOTYPE (CLONES / VARIANTS) =====");
        if (eliteFireGoblin != null) {
            eliteFireGoblin.displayInfo();
            System.out.println();
        }
        if (miniBossIceGoblin != null) {
            miniBossIceGoblin.displayInfo();
            System.out.println();
        }
        if (enragedDragon != null) {
            enragedDragon.displayInfo();
        }
        System.out.println("\nRegistered prototypes: " + registry.getRegisteredKeys());

    }

    }
