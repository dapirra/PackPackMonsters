package com.team2.packpackmonsters;

import com.team2.packpackmonsters.data.PacPacMonstersContract;
import com.team2.packpackmonsters.data.PacPacMonstersContract.PacPacMonsterEntry;

import java.util.ArrayList;

/**
 * Created by Owner on 4/27/2018.
 */

public class Monster {
    private String name;
    private String description;
    private int hp;
    private int type;
    private String imagePath;
    private ArrayList<Move> moves;

    public Monster(String name, String description, int hp, int type, String imagePath, ArrayList<Move> moves) {
        this.name = name;
        this.description = description;
        this.hp = hp;
        this.type = type;
        this.imagePath = imagePath;
        this.moves = moves;
    }

    public void doMove (Monster opponent, Move selectedMove) {
        if (!selectedMove.isBuff()) {
            if (this.getType() == PacPacMonsterEntry.FIRE && opponent.getType() == PacPacMonsterEntry.WATER) {
                opponent.setHp(opponent.getHp() - selectedMove.getDamage()  - 3);
            }
            else if(this.getType() == PacPacMonsterEntry.WATER && opponent.getType() == PacPacMonsterEntry.FIRE) {
                opponent.setHp(opponent.getHp() - selectedMove.getDamage() + 3);
            }
            else if(this.getType() == PacPacMonsterEntry.FIRE && opponent.getType() == PacPacMonsterEntry.EARTH) {
                opponent.setHp(opponent.getHp() - selectedMove.getDamage() + 3);
            }
            else if(this.getType() == PacPacMonsterEntry.EARTH && opponent.getType() == PacPacMonsterEntry.FIRE) {
                opponent.setHp(opponent.getHp() - selectedMove.getDamage() - 3);
            } else {
                opponent.setHp(opponent.getHp() - selectedMove.getDamage());
            }
            // TODO finish all conditions
        } else {
            // TODO Figure out what to do for buffs
        }
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getType() {
        return type;
    }

    public String getTypeString() {
        switch (type) {
            case 1:
                return "Fire";
            case 2:
                return "Water";
            case 3:
                return "Wind";
            case 4:
                return "Earth";
            default:
                return null;
        }
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public boolean isAlive () {
        return this.hp > 0;
    }

    @Override
    protected Monster clone() {
        return new Monster(name, description, hp, type, imagePath, moves);
    }
}
