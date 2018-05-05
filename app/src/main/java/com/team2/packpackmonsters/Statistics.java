package com.team2.packpackmonsters;

public class Statistics {
    public String name;
    public int wins;
    public int losses;
    public int surrenders;

    public Statistics() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getSurrenders() {
        return surrenders;
    }

    public void setSurrenders(int surrenders) {
        this.surrenders = surrenders;
    }
}
