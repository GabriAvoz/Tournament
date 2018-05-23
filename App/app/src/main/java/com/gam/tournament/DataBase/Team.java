package com.gam.tournament.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Team {
    @PrimaryKey
    @NonNull
    int id;
    String name;
    String player;
    int won;
    int drawn;
    int lost;
    int gf;
    int ga;
    int nextMatch;

    public Team(int id, String name, String player, int nextMatch) {
        this.id = id;
        this.name = name;
        this.player = player;
        this.won = 0;
        this.drawn = 0;
        this.lost = 0;
        this.gf = 0;
        this.ga = 0;
        this.nextMatch = nextMatch;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getDrawn() {
        return drawn;
    }

    public void setDrawn(int drawn) {
        this.drawn = drawn;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getGf() {
        return gf;
    }

    public void setGf(int gf) {
        this.gf = gf;
    }

    public int getGa() {
        return ga;
    }

    public void setGa(int ga) {
        this.ga = ga;
    }

    public int getPlayed(){
        return won + drawn + lost;
    }

    public int getPoints(){
        return 3*won + drawn;
    }

    public int getGD(){
        return gf - ga;
    }

    public int getNextMatch() {
        return nextMatch;
    }

    public void setNextMatch(int nextMatch) {
        this.nextMatch = nextMatch;
    }
}
