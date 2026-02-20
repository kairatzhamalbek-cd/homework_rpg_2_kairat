package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


public class DragonBoss implements Enemy {

    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;

    private String element;

    private List<Ability> abilities;

    private Map<Integer, Integer> phases;

    private LootTable lootTable;

    private String aiBehavior;

    private boolean canFly;
    private boolean hasBreathAttack;
    private int wingspan;


    public DragonBoss(String name, int health, int damage, int defense,
                      int speed, String element,
                      List<Ability> abilities,
                      int phase1Threshold, int phase2Threshold, int phase3Threshold,
                      LootTable lootTable, String aiBehavior,
                      boolean canFly, boolean hasBreathAttack, int wingspan) {

        this.name = name;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
        this.element = (element == null || element.isBlank()) ? "NONE" : element;
        this.abilities = (abilities != null) ? abilities : new ArrayList<>();
        this.phases = new HashMap<>();
        this.phases.put(1, phase1Threshold);
        this.phases.put(2, phase2Threshold);
        this.phases.put(3, phase3Threshold);
        this.lootTable = lootTable;
        this.aiBehavior =  (aiBehavior == null || aiBehavior.isBlank()) ? "BOSS" : aiBehavior;
        this.canFly = canFly;
        this.hasBreathAttack = hasBreathAttack;
        this.wingspan = wingspan;
    }


    @Override public String getName() {
        return name;
    }
    @Override public int getHealth() {
        return health;
    }
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

        Map<Integer, Integer> newPhases = new HashMap<>();
        for (Map.Entry<Integer, Integer> e : this.phases.entrySet()) {
            newPhases.put(e.getKey(), (int) Math.round(e.getValue() * multiplier));

        }
        this.phases = newPhases;
    }
    @Override
    public void addAbility(Ability ability) {
        if (ability != null) {
            this.abilities.add(ability);
        }
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
        this.aiBehavior = (aiBehavior == null || aiBehavior.isBlank()) ? this.aiBehavior : aiBehavior;
    }

    public void displayInfo() {
        System.out.println("=== " + name + " (Dragon Boss) ===");
        System.out.println("Health: " + health + " | Damage: " + damage
                + " | Defense: " + defense + " | Speed: " + speed);
        System.out.println("Element: " + element);
        System.out.println("Abilities (" + abilities.size() + "):");
        for (Ability a : abilities) {
            System.out.println("  - " + a.getName() + " | dmg=" + a.getDamage()
                    + " | " + a.getDescription());
        }
        System.out.println("Boss Phases: " + phases.size());
        for (Map.Entry<Integer, Integer> phase : phases.entrySet()) {
            System.out.println("  Phase " + phase.getKey()
                    + ": triggers at " + phase.getValue() + " HP");
        }
        System.out.println("AI Behavior: " + aiBehavior);
        System.out.println("Can Fly: " + canFly
                + " | Breath Attack: " + hasBreathAttack
                + " | Wingspan: " + wingspan);

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
        List<Ability> abilityCopy = new ArrayList<>();
        for (Ability a : this.abilities) {
            abilityCopy.add(a == null ? null : a.clone());
        }

        LootTable lootCopy = (this.lootTable == null) ? null : this.lootTable.clone();
        DragonBoss copy = new DragonBoss(
                this.name,
                this.health,
                this.damage,
                this.defense,
                this.speed,
                this.element,
                abilityCopy,
                this.phases.getOrDefault(1, 0),
                this.phases.getOrDefault(2, 0),
                this.phases.getOrDefault(3, 0),
                lootCopy,
                this.aiBehavior,
                this.canFly,
                this.hasBreathAttack,
                this.wingspan
        );

        copy.phases = new HashMap<>(this.phases);


        return copy;}
    public void setPhase(int phaseNumber, int thresholdHp) {
        if (phaseNumber <= 0) return;
        if (this.phases == null) this.phases = new HashMap<>();
        this.phases.put(phaseNumber, thresholdHp);
    }

    public Map<Integer, Integer> getPhases() {
        return phases;
    }


}
