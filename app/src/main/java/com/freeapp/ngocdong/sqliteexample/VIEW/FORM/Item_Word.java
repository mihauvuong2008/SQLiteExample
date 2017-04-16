package com.freeapp.ngocdong.sqliteexample.VIEW.FORM;

import com.freeapp.ngocdong.sqliteexample.DBS.DAO.MEANING;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.WORD;

import java.io.Serializable;
import java.util.List;

/**
 * Created by NgocDong on 06/03/2017.
 */

public class Item_Word implements Serializable {

    private WORD word;
    private List<MEANING> meaning;

    public void setMeaning(List<MEANING> meaning) {
        this.meaning = meaning;
    }

    public List<MEANING> getMeaning() {
        return meaning;
    }

    public WORD getWord() {
        return word;
    }

    public void setWord(WORD word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return word.getCONTENT();
    }
}
