package com.freeapp.ngocdong.sqliteexample.DBS.CONTROL.DBS_MANAGER;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.freeapp.ngocdong.sqliteexample.DBS.CONTROL.MyDatabaseHelper;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.TOPIC;
import com.freeapp.ngocdong.sqliteexample.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by NgocDong on 04/03/2017.
 */

public class DBS_TOPIC_MANAGER {

    private final MyDatabaseHelper myDatabaseHelper;

    public DBS_TOPIC_MANAGER(MyDatabaseHelper myDatabaseHelper) {
        this.myDatabaseHelper = myDatabaseHelper;
    }

    public void DEL(TOPIC selectedTOPIC) {
        myDatabaseHelper.deleteTOPIC(selectedTOPIC);
        myDatabaseHelper.close();
    }

    public void EDIT(TOPIC topic_old, TOPIC topic) {
        myDatabaseHelper.editTOPIC(topic_old, topic);
    }

    public int ADD(TOPIC topic) {
        return myDatabaseHelper.addTOPIC(topic);
    }

    public List<TOPIC> ALL() {
        return myDatabaseHelper.getAllTOPIC();
    }
}
