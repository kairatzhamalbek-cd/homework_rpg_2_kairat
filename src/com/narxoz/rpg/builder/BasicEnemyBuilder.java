package com.narxoz.rpg.builder;
import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;


public class BasicEnemyBuilder  implements EnemyBuilder{
    private String name;
    private Integer health;
    private Integer damage;
    private Integer defense;
    private Integer speed;
    private String element;
    private String aiBehavior;

    private final List<Ability> abilities = new ArrayList<>();
    private LootTable lootTable;

    @Override
    public EnemyBuilder setName(String name) {
        this.name = name;
        return this;
    }
    @Override
    public EnemyBuilder setHealth(int health) {
        this.health = health;
        return this;
    }
    @Override
    public EnemyBuilder setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    @Override
    public EnemyBuilder setDefense(int defense) {
        this.defense = defense;
        return this;
    }
    @Override
    public EnemyBuilder setSpeed(int speed) {
        this.speed = speed;
        return this;
    }
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
    @Override
    public EnemyBuilder setLootTable(LootTable lootTable) {
        this.lootTable = lootTable;
        return this;
    }
    @Override
    public Enemy build() {
        // Start from default Goblin baseline
        Goblin goblin = new Goblin(name == null || name.isBlank() ? "Goblin" : name);
        if (element != null) goblin.setElement(element);
        if (aiBehavior != null) goblin.setAIBehavior(aiBehavior);
        if (lootTable != null) goblin.setLootTable(lootTable);

        if (health != null) {
            double m = (double) health / goblin.getHealth();
            goblin.multiplyStats(m);
        }
        if (damage != null && goblin.getDamage() > 0) {
            double m = (double) damage / goblin.getDamage();
            goblin.multiplyStats(m);
        }
        if (defense != null && goblin.getDefense() > 0) {
            double m = (double) defense / goblin.getDefense();
            goblin.multiplyStats(m);
        }
        if (speed != null && goblin.getSpeed() > 0) {
            double m = (double) speed / goblin.getSpeed();
            goblin.multiplyStats(m);
        }
        for (Ability a : abilities) {
            goblin.addAbility(a);
        }
        return goblin;

    }
}
