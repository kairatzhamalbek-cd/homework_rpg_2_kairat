package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.enemy.Orc;
import com.narxoz.rpg.enemy.Skeleton;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;


public class BasicEnemyBuilder implements EnemyBuilder {

    public enum BasicType { GOBLIN, SKELETON, ORC }

    private BasicType type = BasicType.GOBLIN;

    private String name;

    private Integer health;
    private Integer damage;
    private Integer defense;
    private Integer speed;

    private String element;
    private String aiBehavior;

    private final List<Ability> abilities = new ArrayList<>();
    private LootTable lootTable;

    public BasicEnemyBuilder setType(BasicType type) {
        if (type != null) this.type = type;
        return this;
    }

    @Override
    public EnemyBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override public EnemyBuilder setHealth(int health) { this.health = health; return this; }
    @Override public EnemyBuilder setDamage(int damage) { this.damage = damage; return this; }
    @Override public EnemyBuilder setDefense(int defense) { this.defense = defense; return this; }
    @Override public EnemyBuilder setSpeed(int speed) { this.speed = speed; return this; }

    @Override
    public EnemyBuilder setElement(String element) {
        this.element = element;
        return this;
    }

    @Override
    public EnemyBuilder setAIBehavior(String aiBehavior) {
        this.aiBehavior = aiBehavior;
        return this;
    }

    @Override
    public EnemyBuilder addAbility(Ability ability) {
        if (ability != null) abilities.add(ability);
        return this;
    }

    @Override
    public EnemyBuilder setAbilities(List<Ability> abilities) {
        this.abilities.clear();
        if (abilities != null) this.abilities.addAll(abilities);
        return this;
    }

    @Override
    public EnemyBuilder addPhase(int phaseNumber, int healthThreshold) {
        return this;
    }

    @Override
    public EnemyBuilder setLootTable(LootTable loot) {
        this.lootTable = loot;
        return this;
    }

    @Override
    public Enemy build() {
        if (name == null || name.isBlank()) {
            throw new IllegalStateException("Enemy name must not be empty.");
        }
        if (health != null && health <= 0) {
            throw new IllegalStateException("Health must be positive.");
        }

        Enemy enemy;
        switch (type) {
            case SKELETON -> enemy = new Skeleton(name);
            case ORC -> enemy = new Orc(name);
            default -> enemy = new Goblin(name);
        }

        enemy.setElement(element == null ? "NONE" : element);
        enemy.setAIBehavior(aiBehavior == null ? "BASIC" : aiBehavior);

        if (health != null) enemy.multiplyStats((double) health / Math.max(1, enemy.getHealth()));
        if (damage != null) enemy.multiplyStats((double) damage / Math.max(1, enemy.getDamage()));
        if (defense != null) enemy.multiplyStats((double) defense / Math.max(1, enemy.getDefense()));
        if (speed != null) enemy.multiplyStats((double) speed / Math.max(1, enemy.getSpeed()));

        for (Ability a : abilities) enemy.addAbility(a);
        if (lootTable != null) enemy.setLootTable(lootTable);

        return enemy;
    }
}
