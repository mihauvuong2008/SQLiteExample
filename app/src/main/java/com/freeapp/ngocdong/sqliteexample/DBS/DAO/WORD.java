package com.freeapp.ngocdong.sqliteexample.DBS.DAO;

import java.io.Serializable;

/**
 * Created by NgocDong on 03/03/2017.
 */

public class WORD implements Serializable {
    private int STT;
    private String CONTENT;
    private int TOPIC_KEY_REF;

    public int getTOPIC_KEY_REF() {
        return TOPIC_KEY_REF;
    }

    public void setTOPIC_KEY_REF(int TOPIC_KEY_REF) {
        this.TOPIC_KEY_REF = TOPIC_KEY_REF;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public int getSTT() {
        return STT;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    @Override
    public String toString() {
        return getCONTENT();
    }
}
