package com.freeapp.ngocdong.sqliteexample.VIEW;

import com.freeapp.ngocdong.sqliteexample.DBS.DAO.TOPIC;

/**
 * Created by NgocDong on 07/03/2017.
 */

public class CheckItem {

    TOPIC topic;
    boolean selected;

    public CheckItem(TOPIC topic, boolean flag) {
        this.topic = topic;
        this.selected = flag;
    }

    public TOPIC getTopic() {
        return topic;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setTopic(TOPIC topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return topic.getCONTENT();
    }
}
