package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.DragonBoss;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public class BossEnemyBuilder implements EnemyBuilder{
    private String name = "Dragon Boss";

    private int health = 1000;
    private int damage = 200;
    private int defense = 80;
    private int speed = 30;

    private String element = "FIRE";
    private String aiBehavior = "BOSS";

    private final List<Ability> abilities = new ArrayList<>();

    private int phase1 = 700;
    private int phase2 = 400;
    private int phase3 = 150;

    private LootTable lootTable;

    private boolean canFly = true;
    private boolean hasBreathAttack = true;
    private int wingspan = 20;

    @Override
    public EnemyBuilder setName(String name) {
        if (name != null && !name.isBlank()) this.name = name;
        return this;
    }
    @Override
    public EnemyBuilder setHealth(int health) {
        if (health > 0) this.health = health;
        return this;
    }
    @Override
    public EnemyBuilder setDamage(int damage) {
        if (damage >= 0) this.damage = damage;
        return this;
    }
    @Override
    public EnemyBuilder setDefense(int defense) {
        if (defense >= 0) this.defense = defense;
        return this;
    }
    @Override
    public EnemyBuilder setSpeed(int speed) {
        if (speed >= 0) this.speed = speed;
        return this;
    }
    @Override
    public EnemyBuilder setElement(String element) {
        if (element != null && !element.isBlank()) this.element = element;
        return this;
    }
    @Override
    public EnemyBuilder setAIBehavior(String aiBehavior) {
        if (aiBehavior != null && !aiBehavior.isBlank()) this.aiBehavior = aiBehavior;
        return this;
    }
    @Override
    public EnemyBuilder setAbilities(List<Ability> abilities) {
        this.abilities.clear();
        if (abilities != null) this.abilities.addAll(abilities);
        return this;
    }
    @Override
    public EnemyBuilder addAbility(Ability ability) {
        if (ability != null) this.abilities.add(ability);
        return this;
    }
    public BossEnemyBuilder setPhaseThresholds(int phase1, int phase2, int phase3) {
        if (phase1 > 0) this.phase1 = phase1;
        if (phase2 > 0) this.phase2 = phase2;
        if (phase3 > 0) this.phase3 = phase3;
        return this;
    }
    @Override
    public EnemyBuilder setLootTable(LootTable lootTable) {
        this.lootTable = lootTable;
        return this;
    }
    public BossEnemyBuilder setFlight(boolean canFly) {
        this.canFly = canFly;
        return this;
    }
    public BossEnemyBuilder setBreathAttack(boolean hasBreathAttack) {
        this.hasBreathAttack = hasBreathAttack;
        return this;
    }
    public BossEnemyBuilder setWingspan(int wingspan) {
        if (wingspan > 0) this.wingspan = wingspan;
        return this;
    }
    @Override
    public Enemy build() {
        return new DragonBoss(name, health, damage, defense, speed, element,
                new ArrayList<>(abilities), phase1, phase2, phase3, lootTable,
                aiBehavior, canFly, hasBreathAttack, wingspan
        );
    }

}
