package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.DragonBoss;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BossEnemyBuilder implements EnemyBuilder {

    private String name;

    private Integer health;
    private Integer damage;
    private Integer defense;
    private Integer speed;

    private String element;
    private String aiBehavior;

    private final List<Ability> abilities = new ArrayList<>();
    private final Map<Integer, Integer> phases = new HashMap<>();

    private LootTable lootTable;

    private boolean canFly = true;
    private boolean hasBreathAttack = true;
    private int wingspan = 20;

    public BossEnemyBuilder setFlight(boolean canFly) { this.canFly = canFly; return this; }
    public BossEnemyBuilder setBreathAttack(boolean hasBreathAttack) { this.hasBreathAttack = hasBreathAttack; return this; }
    public BossEnemyBuilder setWingspan(int wingspan) { if (wingspan > 0) this.wingspan = wingspan; return this; }

    @Override public EnemyBuilder setName(String name) { this.name = name; return this; }
    @Override public EnemyBuilder setHealth(int health) { this.health = health; return this; }
    @Override public EnemyBuilder setDamage(int damage) { this.damage = damage; return this; }
    @Override public EnemyBuilder setDefense(int defense) { this.defense = defense; return this; }
    @Override public EnemyBuilder setSpeed(int speed) { this.speed = speed; return this; }

    @Override public EnemyBuilder setElement(String element) { this.element = element; return this; }

    @Override
    public EnemyBuilder setAIBehavior(String aiBehavior) {
        this.aiBehavior = aiBehavior;
        return this;
    }

    @Override public EnemyBuilder addAbility(Ability ability) { if (ability != null) abilities.add(ability); return this; }

    @Override
    public EnemyBuilder setAbilities(List<Ability> abilities) {
        this.abilities.clear();
        if (abilities != null) this.abilities.addAll(abilities);
        return this;
    }

    @Override
    public EnemyBuilder addPhase(int phaseNumber, int healthThreshold) {
        if (phaseNumber <= 0) return this;
        if (healthThreshold < 0) return this;
        phases.put(phaseNumber, healthThreshold);
        return this;
    }

    @Override
    public EnemyBuilder setLootTable(LootTable loot) {
        this.lootTable = loot;
        return this;
    }

    @Override
    public Enemy build() {
        if (name == null || name.isBlank()) throw new IllegalStateException("Boss name must not be empty.");
        if (health == null || health <= 0) throw new IllegalStateException("Boss health must be positive.");
        if (damage == null || damage < 0) throw new IllegalStateException("Boss damage must be >= 0.");
        if (defense == null || defense < 0) throw new IllegalStateException("Boss defense must be >= 0.");
        if (speed == null || speed < 0) throw new IllegalStateException("Boss speed must be >= 0.");

        String el = (element == null || element.isBlank()) ? "NONE" : element;
        String ai = (aiBehavior == null || aiBehavior.isBlank()) ? "BOSS" : aiBehavior;

        int p1 = phases.getOrDefault(1, (int) Math.round(health * 0.7));
        int p2 = phases.getOrDefault(2, (int) Math.round(health * 0.4));
        int p3 = phases.getOrDefault(3, (int) Math.round(health * 0.15));

        DragonBoss boss = new DragonBoss(
                name,
                health,
                damage,
                defense,
                speed,
                el,
                new ArrayList<>(abilities),
                p1, p2, p3,
                lootTable,
                ai,
                canFly,
                hasBreathAttack,
                wingspan
        );

        for (Map.Entry<Integer, Integer> e : phases.entrySet()) {
            if (e.getKey() > 3) boss.setPhase(e.getKey(), e.getValue());
        }

        return boss;
    }
}
