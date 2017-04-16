package com.freeapp.ngocdong.sqliteexample.DBS.CONTROL.DBS_MANAGER;

import com.freeapp.ngocdong.sqliteexample.DBS.CONTROL.MyDatabaseHelper;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.MEANING;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.WORD;
import com.freeapp.ngocdong.sqliteexample.VIEW.FORM.Item_Word;

import java.util.List;

/**
 * Created by NgocDong on 07/03/2017.
 */

public class DBS_MEAN_MANAGER {
    private final MyDatabaseHelper myDatabaseHelper;

    public DBS_MEAN_MANAGER(MyDatabaseHelper myDatabaseHelper) {
        this.myDatabaseHelper = myDatabaseHelper;
    }

    public void DEL(MEANING meaning) {
        myDatabaseHelper.deleteMEAN(meaning);
    }

    public List<MEANING> ALL(WORD w) {
        return myDatabaseHelper.getAllMEANING(w);
    }

    public void editMEAN(MEANING intentMEAN) {
        myDatabaseHelper.editMEAN(intentMEAN);
    }

    public int ADD(MEANING intentMEAN, Item_Word intentWORD) {
        int key = myDatabaseHelper.addMEAN(intentMEAN, intentWORD);
        return key;
    }
}
