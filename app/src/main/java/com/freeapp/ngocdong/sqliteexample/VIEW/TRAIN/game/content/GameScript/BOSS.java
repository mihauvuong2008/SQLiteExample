package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.GameScript;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;

/**
 * Created by NgocDong on 16/03/2017.
 */

public class BOSS {
    private final int color;
    private int idBOSS = 0;
    private boolean killed;
    private boolean isAttack = false;
    private final Configure configure;
    private final String BOSS;
    private int pointGold = 1000;

    public BOSS(String BOSS, int color, Configure configure) {
        this.BOSS = BOSS;
        this.color = color;
        this.configure = configure;
    }

    public String getBOSS() {
        return BOSS;
    }

    public int getIdBOSS() {
        return idBOSS;
    }

    public boolean getKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public void setIdBOSS(int idBOSS) {
        this.idBOSS = idBOSS;
    }

    public int getColor() {
        return color;
    }
}
