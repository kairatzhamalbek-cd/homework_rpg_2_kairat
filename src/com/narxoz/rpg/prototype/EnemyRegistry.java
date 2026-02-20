package com.narxoz.rpg.prototype;
import com.narxoz.rpg.enemy.Enemy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EnemyRegistry {
    private final Map<String, Enemy> prototypes = new HashMap<>();
    public void registerPrototype(String key, Enemy prototype) {
        if (key == null || key.isBlank() || prototype == null) return;
        prototypes.put(key, prototype);
    }
    public Enemy createEnemy(String key) {
        Enemy proto = prototypes.get(key);
        if (proto == null) return null;
        return proto.clone();}
    public Set<String> getRegisteredKeys() {
        return Collections.unmodifiableSet(prototypes.keySet());
    }
    public Map<String, Enemy> getPrototypesView() {
        return Collections.unmodifiableMap(prototypes);
    }
}
