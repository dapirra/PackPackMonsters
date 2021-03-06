package com.team2.packpackmonsters;

import com.team2.packpackmonsters.data.PacPacMonstersContract.PacPacMonsterEntry;

import java.util.ArrayList;

/**
 * Created by Owner on 4/27/2018.
 */

public class Monster {
    public static int damageDealt;
    private String name;
    private String description;
    private int maxHp;
    private int currentHp;
    private int type;
    private ArrayList<Move> moves;
    private int image;
    private String typeString;
    private boolean ultimateUsed;

    public Monster(String name, String description, int maxHp, int type, ArrayList<Move> moves) {
        this.name = name;
        this.description = description;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.type = type;
        this.moves = moves;
        figureOutType();
        figureOutImage();
    }

    public void doMove(Monster opponent, Move selectedMove) {
        if (!selectedMove.isBasicMove()) {
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

        if (selectedMove.isUltimateMove()) {
            ultimateUsed = true;
        }

        // Prevent hp from going below 0
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
        //@formatter:off
        switch (type) {
            case 1: typeString = "Fire"; break;
            case 2: typeString = "Water"; break;
            case 3: typeString = "Wind"; break;
            case 4: typeString = "Earth"; break;
        }
        //@formatter:on
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
        return new Monster(name, description, maxHp, type, moves);
    }

    private void figureOutImage() {
        //@formatter:off
        switch (name) {
            case "Moultin": this.image = R.drawable.moultin; break;
            case "Aerou": this.image = R.drawable.aerou; break;
            case "Ahkwa": this.image = R.drawable.ahkwa; break;
            case "Aiyce": this.image = R.drawable.aiyce; break;
            case "Blough": this.image = R.drawable.blough; break;
            case "Dyurt": this.image = R.drawable.dyurt; break;
            case "Ignaight": this.image = R.drawable.ignaight; break;
            case "Kwayk": this.image = R.drawable.kwayk; break;
            case "Peyero": this.image = R.drawable.peyero; break;
            case "Rakh": this.image = R.drawable.rakh; break;
            case "Vaypour": this.image = R.drawable.vaypour; break;
            case "Whoush": this.image = R.drawable.whoush; break;
        }
        //@formatter:on
    }

    public int getImage() {
        return this.image;
    }

    public boolean isUltimateUsed() {
        return ultimateUsed;
    }

    public void setUltimateUsed(boolean ultimateUsed) {
        this.ultimateUsed = ultimateUsed;
    }
}
