package com.freeapp.ngocdong.sqliteexample.DBS.DAO;

import java.io.Serializable;

/**
 * Created by NgocDong on 04/03/2017.
 */

public class TOPIC implements Serializable {
    private int STT = 0;
    private String CONTENT;

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public int getSTT() {
        return STT;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    @Override
    public String toString() {
        return this.getCONTENT();
    }
}
