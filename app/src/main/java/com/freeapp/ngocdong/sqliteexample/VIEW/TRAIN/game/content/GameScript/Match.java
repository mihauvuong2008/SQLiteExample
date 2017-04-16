package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.GameScript;

import java.util.ArrayList;

/**
 * Created by NgocDong on 08/03/2017.
 */

public class Match {
    private ArrayList<BOSS> Boss;
    private ArrayList<CAMBAT> CAMBATANT;
    private boolean passed = false;
    private int currBOSSid = 0;

    public ArrayList<BOSS> getBoss() {
        return Boss;
    }

    public void setBoss(ArrayList<BOSS> boss) {
        this.Boss = boss;
    }

    public void setCAMBATANT(ArrayList<CAMBAT> CAMBATANT) {
        this.CAMBATANT = CAMBATANT;
    }

    public void addCAMBATANT(CAMBAT cambatant) {
        if (CAMBATANT == null) CAMBATANT = new ArrayList<>();
        CAMBATANT.add(cambatant);
    }

    public ArrayList<CAMBAT> getCAMBATANT() {
        return CAMBATANT;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public boolean isPassed() {
        return passed;
    }

    public int getCurrBOSSid() {
        return currBOSSid;
    }

    public void setCurrBOSSid(int currBOSSid) {
        this.currBOSSid = currBOSSid;
    }
}
