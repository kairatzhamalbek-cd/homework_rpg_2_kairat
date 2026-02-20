package com.narxoz.rpg.prototype;

import com.narxoz.rpg.enemy.Enemy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class EnemyRegistry {

    private final Map<String, Enemy> templates = new HashMap<>();

    public void registerTemplate(String key, Enemy template) {
        if (key == null || key.isBlank() || template == null) return;
        templates.put(key, template);
    }

    public Enemy createFromTemplate(String key) {
        Enemy proto = templates.get(key);
        if (proto == null) return null;
        return proto.clone();
    }

    public Set<String> listTemplates() {
        return Collections.unmodifiableSet(templates.keySet());
    }

    public Map<String, Enemy> viewTemplates() {
        return Collections.unmodifiableMap(templates);
    }
}