package com.team2.packpackmonsters;

import com.team2.packpackmonsters.data.PacPacMonstersContract.PacPacMonsterEntry;

import java.util.ArrayList;

/**
 * Created by Owner on 4/27/2018.
 */

public class Monster {
    private String name;
    private String description;
    private int maxHp;
    private int currentHp;
    private int type;
    private String imagePath;
    private ArrayList<Move> moves;
    private int image;
    private String typeString;
    public static int damageDealt;


    public Monster(String name, String description, int maxHp, int type, String imagePath, ArrayList<Move> moves) {
        this.name = name;
        this.description = description;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.type = type;
        this.imagePath = imagePath;
        this.moves = moves;
        figureOutType();
        figureOutImage();
    }

    public Monster(String name, String description, int maxHp, int currentHp, int type, String imagePath, ArrayList<Move> moves) {
        this.name = name;
        this.description = description;
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.type = type;
        this.imagePath = imagePath;
        this.moves = moves;
        figureOutType();
        figureOutImage();
    }

    public void doMove (Monster opponent, Move selectedMove) {
        if (!selectedMove.isBuff()) {
            if (!selectedMove.getBasicMove()) {
                if (this.getType() == PacPacMonsterEntry.FIRE && opponent.getType() == PacPacMonsterEntry.WATER) {
                    damageDealt = selectedMove.getDamage() - 2;
                    opponent.setCurrentHp(opponent.getCurrentHp() - damageDealt);
                } else if (this.getType() == PacPacMonsterEntry.WATER && opponent.getType() == PacPacMonsterEntry.FIRE) {
                    damageDealt = selectedMove.getDamage() + 2;
                    opponent.setCurrentHp(opponent.getCurrentHp() - damageDealt);
                } else if (this.getType() == PacPacMonsterEntry.FIRE && opponent.getType() == PacPacMonsterEntry.EARTH) {
                    damageDealt = selectedMove.getDamage() + 2;
                    opponent.setCurrentHp(opponent.getCurrentHp() - damageDealt);
                } else if (this.getType() == PacPacMonsterEntry.EARTH && opponent.getType() == PacPacMonsterEntry.FIRE) {
                    damageDealt = selectedMove.getDamage() - 2;
                    opponent.setCurrentHp(opponent.getCurrentHp() - damageDealt);
                } else if (this.getType() == PacPacMonsterEntry.WIND && opponent.getType() == PacPacMonsterEntry.WATER) {
                    damageDealt = selectedMove.getDamage() + 2;
                    opponent.setCurrentHp(opponent.getCurrentHp() - damageDealt);
                } else if (this.getType() == PacPacMonsterEntry.WATER && opponent.getType() == PacPacMonsterEntry.WIND) {
                    damageDealt = selectedMove.getDamage() - 2;
                    opponent.setCurrentHp(opponent.getCurrentHp() - damageDealt);
                } else if (this.getType() == PacPacMonsterEntry.EARTH && opponent.getType() == PacPacMonsterEntry.WIND) {
                    damageDealt = selectedMove.getDamage() + 2;
                    opponent.setCurrentHp(opponent.getCurrentHp() - damageDealt);
                } else if (this.getType() == PacPacMonsterEntry.WIND && opponent.getType() == PacPacMonsterEntry.EARTH) {
                    damageDealt = selectedMove.getDamage() - 2;
                    opponent.setCurrentHp(opponent.getCurrentHp() - damageDealt);
                } else {
                    damageDealt = selectedMove.getDamage();
                    opponent.setCurrentHp(opponent.getCurrentHp() - damageDealt);
                }
            } else {
                damageDealt = selectedMove.getDamage();
                opponent.setCurrentHp(opponent.getCurrentHp() - damageDealt);
            }
        }
        else {
            // TODO Figure out what to do for buffs
        }

        if (opponent.getCurrentHp() < 0) {
            opponent.setCurrentHp(0);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int getType() {
        return type;
    }

    private void figureOutType() {
        switch (type) {
            case 1: typeString = "Fire"; break;
            case 2: typeString = "Water"; break;
            case 3: typeString = "Wind"; break;
            case 4: typeString = "Earth"; break;
        }
    }

    public String getTypeString() {
        return typeString;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public boolean isDead() {
        return this.currentHp <= 0;
    }

    @Override
    public Monster clone() {
        return new Monster(name, description, maxHp, type, imagePath, moves);
    }

    private void figureOutImage() {
        switch (name) {
            case "Moultin": this.image = R.drawable.moultin; break;
            case "Aerou": this.image = R.drawable.aerou; break;
            case "Ahkwa": this.image = R.drawable.ahkwa; break;
            case "Aiyce": this.image = R.drawable.aiyce; break;
            case "Blough": this.image = R.drawable.blough; break;
            case "Dyurt": this.image = R.drawable.dyurt; break;
            case "Ignaight": this.image = R.drawable.ignaight; break;
            case "Kwayk": this.image = R.drawable.kwayk; break;
            case "Pevero": this.image = R.drawable.pevero; break;
            case "Rakh": this.image = R.drawable.rakh; break;
            case "Vaypour": this.image = R.drawable.vaypour; break;
            case "Whoush": this.image = R.drawable.whoush; break;
        }
    }

    public int getImage() {
        return this.image;
    }
}
