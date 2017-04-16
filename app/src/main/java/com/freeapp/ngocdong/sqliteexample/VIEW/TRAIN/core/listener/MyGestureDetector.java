package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.Army;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.control.Attack_Button;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.control.List_Skill_button;

/**
 * Created by NgocDong on 04/03/2017.
 */

public class MyGestureDetector {
    private MyOnGestureListener myOnGestureListener;
    private MyOnDoubleTapListener myOnDoubleTapListener;
    Army army;
    Attack_Button ab;
    List_Skill_button mskill;

    public MyGestureDetector() {
        myOnDoubleTapListener = new MyOnDoubleTapListener();
        myOnGestureListener = new MyOnGestureListener();
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public void setAb(Attack_Button ab) {
        this.ab = ab;
    }

    public void setMskill(List_Skill_button mskill) {
        this.mskill = mskill;
    }

    public GestureDetector.OnDoubleTapListener getMyOnDoubleTapListener() {
        return myOnDoubleTapListener;
    }

    public MyOnGestureListener getMyOnGestureListener() {
        return myOnGestureListener;
    }

    public class MyOnGestureListener implements GestureDetector.OnGestureListener {

        private static final String TAG = "TAG";

        @Override
        public boolean onDown(MotionEvent e) {
            ab.onPress(e);
            mskill.onPress(e);
            return true;
        }


        @Override
        public void onShowPress(MotionEvent e) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            ab.onTapUp(e);
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            textEvt1.setText("Scroll");
//            textEvt2.setText(e1.getX()+":"+ e1.getY() +"  "+ e2.getX()+":"+ e2.getY());
//            Log.e(TAG, "onScroll");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            ab.setLongPress(e);
        }

        //
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (army.getSolider().isFling()) {
                army.getSolider().setMovingVector(e2.getX() - e1.getX(), e2.getY() - e1.getY());
                army.getSolider().setStartFly(true);
            }
            ab.onTapUp(e2);
            return true;
        }

    }

    public class MyOnDoubleTapListener implements GestureDetector.OnDoubleTapListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            ab.onSingleTapConfirmed(e);
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            ab.onTapUp(e);
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            ab.onTapUp(e);
            return true;
        }
    }
}