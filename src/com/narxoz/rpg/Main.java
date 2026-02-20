
package com.narxoz.rpg;

import com.narxoz.rpg.builder.BasicEnemyBuilder;
import com.narxoz.rpg.builder.BossEnemyBuilder;
import com.narxoz.rpg.builder.EnemyDirector;
import com.narxoz.rpg.combat.shadow.ShadowStrike;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.factory.FireComponentFactory;
import com.narxoz.rpg.factory.IceComponentFactory;
import com.narxoz.rpg.factory.ShadowComponentFactory;
import com.narxoz.rpg.prototype.EnemyRegistry;

public class Main {

    public static void main(String[] args) {

        var fireFactory = new FireComponentFactory();
        var iceFactory = new IceComponentFactory();
        var shadowFactory = new ShadowComponentFactory();

        EnemyDirector director = new EnemyDirector();

        EnemyRegistry registry = new EnemyRegistry();

        System.out.println("===== BUILD BASE TEMPLATES (BUILDER + ABSTRACT FACTORY) =====\n");

       BasicEnemyBuilder goblinBuilder = new BasicEnemyBuilder()
                .setType(BasicEnemyBuilder.BasicType.GOBLIN);
        Enemy baseGoblin = director.createMinion(goblinBuilder, "Base Goblin", "NONE", fireFactory);

        baseGoblin.displayInfo();
        System.out.println();

        registry.registerTemplate("goblin", baseGoblin);

      BossEnemyBuilder bossBuilder = new BossEnemyBuilder();
        Enemy baseDragon = director.createRaidBoss(bossBuilder, "Base Dragon", "NONE", fireFactory);

        baseDragon.displayInfo();
        System.out.println();

        registry.registerTemplate("dragon", baseDragon);

        BasicEnemyBuilder skeletonBuilder = new BasicEnemyBuilder()
                .setType(BasicEnemyBuilder.BasicType.SKELETON);
        Enemy baseSkeleton = director.createMinion(skeletonBuilder, "Base Skeleton", "NONE", iceFactory);

        baseSkeleton.displayInfo();
        System.out.println();

        registry.registerTemplate("skeleton", baseSkeleton);

        System.out.println("Registered templates: " + registry.listTemplates());
        System.out.println();

     System.out.println("===== PROTOTYPE VARIANTS (CLONES) =====\n");

        Enemy eliteGoblin = registry.createFromTemplate("goblin");
        eliteGoblin.multiplyStats(2.0);
        eliteGoblin.setAIBehavior("ELITE");
        eliteGoblin.setElement("NONE");
        System.out.println(">>> Elite Goblin (2x stats)");
        eliteGoblin.displayInfo();
        System.out.println();

        Enemy championGoblin = registry.createFromTemplate("goblin");
        championGoblin.multiplyStats(5.0);
        championGoblin.addAbility(new ShadowStrike()); // extra ability requirement
        championGoblin.setAIBehavior("CHAMPION");
        System.out.println(">>> Champion Goblin (5x stats + extra ability)");
        championGoblin.displayInfo();
        System.out.println();

        Enemy kingGoblin = registry.createFromTemplate("goblin");
        kingGoblin.multiplyStats(10.0);
        kingGoblin.setAIBehavior("KING");
       System.out.println(">>> Goblin King (10x stats + boss-ish behavior)");
        kingGoblin.displayInfo();
        System.out.println();

        Enemy fireDragon = registry.createFromTemplate("dragon");
        fireDragon.setElement("FIRE");
        fireDragon.setAIBehavior("AGGRESSIVE");
        System.out.println(">>> Fire Dragon (prototype clone)");
        fireDragon.displayInfo();
        System.out.println();

        Enemy iceDragon = registry.createFromTemplate("dragon");
        iceDragon.setElement("ICE");
        iceDragon.setAIBehavior("DEFENSIVE");
        System.out.println(">>> Ice Dragon (prototype clone)");
        iceDragon.displayInfo();
        System.out.println();

        Enemy shadowDragon = registry.createFromTemplate("dragon");
        shadowDragon.setElement("SHADOW");
        shadowDragon.setAIBehavior("TACTICAL");
        System.out.println(">>> Shadow Dragon (prototype clone)");
        shadowDragon.displayInfo();
        System.out.println();


        Enemy fastSkeleton = registry.createFromTemplate("skeleton");
        fastSkeleton.multiplyStats(1.3);
        fastSkeleton.setAIBehavior("FAST");
        System.out.println(">>> Fast Skeleton (1.3x stats)");
        fastSkeleton.displayInfo();
        System.out.println();

        Enemy tankSkeleton = registry.createFromTemplate("skeleton");
        tankSkeleton.multiplyStats(2.0);
        tankSkeleton.setAIBehavior("TANK");
        System.out.println(">>> Tank Skeleton (2x stats)");
        tankSkeleton.displayInfo();
        System.out.println();

      System.out.println("===== DEEP COPY VERIFICATION =====\n");
        Enemy clone = registry.createFromTemplate("goblin");
        clone.addAbility(new ShadowStrike());

        System.out.println(">>> Clone modified: added ShadowStrike");
        clone.displayInfo();
        System.out.println();

        System.out.println(">>> Original template should NOT change:");
        Enemy originalFromRegistry = registry.createFromTemplate("goblin");
        originalFromRegistry.displayInfo();

        System.out.println("\nDone.");
    }
}