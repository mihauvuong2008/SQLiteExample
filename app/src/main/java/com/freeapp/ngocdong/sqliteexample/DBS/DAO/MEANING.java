package com.freeapp.ngocdong.sqliteexample.DBS.DAO;

import java.io.Serializable;

/**
 * Created by NgocDong on 03/03/2017.
 */

public class MEANING implements Serializable {
    int STT;
    int TYPE;
    String VOICE;
    String MEANING;

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    public void setVOICE(String VOICE) {
        this.VOICE = VOICE;
    }

    public void setMEANING(String MEANING) {
        this.MEANING = MEANING;
    }

    public int getSTT() {
        return STT;
    }

    public int getTYPE() {
        return TYPE;
    }

    public String getVOICE() {
        return VOICE;
    }

    public String getMEANING() {
        return MEANING;
    }

}
