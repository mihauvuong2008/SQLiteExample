package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.GameScript;

import com.freeapp.ngocdong.sqliteexample.DBS.DAO.MEANING;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.WORD;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NgocDong on 08/03/2017.
 */

public class DictionaryData {
    private WORD word;
    private ArrayList<MEANING> meaningArrayList;

    public void setWord(WORD word) {
        this.word = word;
    }

    public void setMeaningArrayList(List<MEANING> meaningArrayList) {
        this.meaningArrayList = new ArrayList<>();
        this.meaningArrayList.addAll(meaningArrayList);
    }

    public WORD getWord() {
        return word;
    }

    public ArrayList<MEANING> getMeaningArrayList() {
        return meaningArrayList;
    }
}
