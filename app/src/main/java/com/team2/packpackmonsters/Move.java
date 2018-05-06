package com.team2.packpackmonsters;

/**
 * Created by Owner on 4/27/2018.
 */

public class Move {
    private String name;
    private String description;
    private int damage;
    private int moveType;

    public Move(String name, String description, int damage, int moveType) {
        this.name = name;
        this.description = description;
        this.damage = damage;
        this.moveType = moveType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isElementalMove() {
        return moveType == 1;
    }

    public boolean isUltimateMove() {
        return moveType == 2;
    }

    public boolean isSpecialMove() {
        return moveType == 3;
    }

    public boolean isBasicMove() {
        return this.moveType == 4;
    }
}
