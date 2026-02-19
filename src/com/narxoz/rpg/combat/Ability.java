package com.narxoz.rpg.combat;
import java.util.List;


public interface Ability {
    String getName();
    int getDamage();
    String getDescription();

    Ability clone();


}
