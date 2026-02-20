package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public class Orc implements Enemy {
    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;

    private String element;
    private String aiBehavior;

    private List<Ability> abilities;
    private LootTable lootTable;

    public Orc(String name) {
        this.name = (name == null || name.isBlank()) ? "Orc" : name;

        // Orc stats: high HP, high damage, low speed
        this.health = 220;
        this.damage = 35;
        this.defense = 18;
        this.speed = 15;

        this.element = "NONE";
        this.aiBehavior = "BASIC";

        this.abilities = new ArrayList<>();
        this.lootTable = null;
    }

    @Override public String getName() { return name; }
    @Override public int getHealth() { return health; }
    @Override public int getDamage() { return damage; }
    @Override public int getDefense() { return defense; }
    @Override public int getSpeed() { return speed; }
    @Override public String getElement() { return element; }
    @Override public String getAIBehavior() { return aiBehavior; }
    @Override public List<Ability> getAbilities() { return abilities; }
    @Override public LootTable getLootTable() { return lootTable; }

    @Override
    public void multiplyStats(double multiplier) {
        if (multiplier <= 0) return;
        this.health = (int) Math.round(this.health * multiplier);
        this.damage = (int) Math.round(this.damage * multiplier);
        this.defense = (int) Math.round(this.defense * multiplier);
        this.speed = (int) Math.round(this.speed * multiplier);
    }

    @Override
    public void addAbility(Ability ability) {
        if (ability != null) abilities.add(ability);
    }

    @Override
    public void setElement(String element) {
        this.element = (element == null || element.isBlank()) ? "NONE" : element;
    }

    @Override
    public void setLootTable(LootTable lootTable) {
        this.lootTable = lootTable;
    }

    @Override
    public void setAIBehavior(String aiBehavior) {
        this.aiBehavior = (aiBehavior == null || aiBehavior.isBlank()) ? "BASIC" : aiBehavior;
    }

    @Override
    public void displayInfo() {
        System.out.println("=== " + name + " (Orc) ===");
        System.out.println("Health: " + health + " | Damage: " + damage
                + " | Defense: " + defense + " | Speed: " + speed);
        System.out.println("Element: " + element + " | AI: " + aiBehavior);

        System.out.println("Abilities (" + abilities.size() + "):");
        for (Ability a : abilities) {
            System.out.println("  - " + a.getName() + " | dmg=" + a.getDamage()
                    + " | " + a.getDescription());
        }

        if (lootTable == null) {
            System.out.println("Loot: (none)");
        } else {
            System.out.println("Loot: " + lootTable.getItems()
                    + " | Gold: " + lootTable.getGoldDrop()
                    + " | XP: " + lootTable.getExperienceDrop());
        }
    }

    @Override
    public Enemy clone() {
        Orc copy = new Orc(this.name);

        copy.health = this.health;
        copy.damage = this.damage;
        copy.defense = this.defense;
        copy.speed = this.speed;

        copy.element = this.element;
        copy.aiBehavior = this.aiBehavior;

        copy.abilities = new ArrayList<>();
        for (Ability a : this.abilities) {
            copy.abilities.add(a == null ? null : a.clone());
        }

        copy.lootTable = (this.lootTable == null) ? null : this.lootTable.clone();

        return copy;
    }
}
