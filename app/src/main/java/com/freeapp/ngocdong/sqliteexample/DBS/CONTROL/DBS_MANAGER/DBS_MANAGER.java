package com.freeapp.ngocdong.sqliteexample.DBS.CONTROL.DBS_MANAGER;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.freeapp.ngocdong.sqliteexample.DBS.CONTROL.MyDatabaseHelper;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.MEANING;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.TOPIC;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.WORD;

import java.io.Serializable;

import static android.R.attr.data;

/**
 * Created by NgocDong on 11/03/2017.
 */

public class DBS_MANAGER {
    DBS_DICTIONARY_MANAGER dbs_dictionary_manager;
    DBS_TOPIC_MANAGER dbs_topic_manager;
    DBS_MEAN_MANAGER dbs_mean_manager;
    MyDatabaseHelper myDatabaseHelper;
    private int mData;

    public DBS_MANAGER(Context context) {
        myDatabaseHelper = new MyDatabaseHelper(context);
        dbs_dictionary_manager = new DBS_DICTIONARY_MANAGER(myDatabaseHelper);
        dbs_mean_manager = new DBS_MEAN_MANAGER(myDatabaseHelper);
        dbs_topic_manager = new DBS_TOPIC_MANAGER(myDatabaseHelper);
    }

    public void addDICT(TOPIC topic, WORD dict, MEANING meaning) {
        myDatabaseHelper.addDICT(topic, dict, meaning);
    }

    public DBS_DICTIONARY_MANAGER getDbs_dictionary_manager() {
        return dbs_dictionary_manager;
    }

    public DBS_TOPIC_MANAGER getDbs_topic_manager() {
        return dbs_topic_manager;
    }

    public DBS_MEAN_MANAGER getDbs_mean_manager() {
        return dbs_mean_manager;
    }

    public void close() {
        myDatabaseHelper.close();
    }

}
