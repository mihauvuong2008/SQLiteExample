package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.Army;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.control.Attack_Button;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.control.List_Skill_button;

/**
 * Created by NgocDong on 16/03/2017.
 */

public class MyListener {
    final private MyGestureDetector myGestureDetector;
    final private GestureDetector gestureDetector;
    GameSurface gameSurface;

    public MyListener(GameSurface gameSurface) {
        this.gameSurface = gameSurface;
        myGestureDetector = new MyGestureDetector();
        GestureDetector.OnGestureListener gestureListener = myGestureDetector.getMyOnGestureListener();
        GestureDetector.OnDoubleTapListener doubleTapListener = myGestureDetector.getMyOnDoubleTapListener();
        this.gestureDetector = new GestureDetector(gameSurface.getContext(), gestureListener);
        this.gestureDetector.setOnDoubleTapListener(doubleTapListener);
    }

    public MyGestureDetector getMyGestureDetector() {
        return myGestureDetector;
    }

    public void setTouchEvent(MotionEvent e) {
        gestureDetector.onTouchEvent(e);
    }

    public void setListenParamater(Army army_solider, Attack_Button attack_button, List_Skill_button myListSkillbutton) {
        myGestureDetector.setArmy(army_solider);
        myGestureDetector.setAb(attack_button);
        myGestureDetector.setMskill(myListSkillbutton);
    }
}
