package com.freeapp.ngocdong.sqliteexample.DBS.CONTROL.DBS_MANAGER;

import com.freeapp.ngocdong.sqliteexample.DBS.CONTROL.MyDatabaseHelper;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.TOPIC;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.WORD;
import com.freeapp.ngocdong.sqliteexample.VIEW.FORM.Item_Word;

import java.util.List;

/**
 * Created by NgocDong on 06/03/2017.
 */

public class DBS_DICTIONARY_MANAGER {
    private final MyDatabaseHelper myDatabaseHelper;

    public DBS_DICTIONARY_MANAGER(MyDatabaseHelper myDatabaseHelper) {
        this.myDatabaseHelper = myDatabaseHelper;
    }

    public void DEL(Item_Word selectedWORD) {
        myDatabaseHelper.deleteWORD(selectedWORD);
    }

    public List<WORD> ALL(TOPIC topic) {
        return myDatabaseHelper.getAllWORD(topic);
    }

    public void EDIT(WORD intentWORD) {
        myDatabaseHelper.editWORD(intentWORD);
    }
}
