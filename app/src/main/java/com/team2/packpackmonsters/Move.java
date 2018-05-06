package com.team2.packpackmonsters;

/**
 * Created by Owner on 4/27/2018.
 */

public class Move {
    private String name;
    private String description;
    private int damage;
    private boolean basicMove;

    public Move(String name, String description, int damage, boolean basicMove) {
        this.name = name;
        this.description = description;
        this.damage = damage;
        this.basicMove = basicMove;
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

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean getBasicMove() {
        return basicMove;
    }

    public void setBasicMove(boolean basicMove) {
        this.basicMove = basicMove;
    }
}
