package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.control;

import android.graphics.Canvas;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.BitmapData;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.GameScript.BOSS;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;

import java.util.ArrayList;

/**
 * Created by NgocDong on 16/03/2017.
 */

public class GameTask {
    ArrayList<Task> GameTaskList;
    GameSurface gameSurface;
    Configure configure;
    BitmapData bitmapData;

    public GameTask(GameSurface gameSurface, BitmapData bitmapData, Configure configure) {
        this.gameSurface = gameSurface;
        this.configure = configure;
        this.bitmapData = bitmapData;
        GameTaskList = new ArrayList<>();
    }

    public void setNewTaskList(ArrayList<BOSS> getBoss) {
        GameTaskList = new ArrayList<>();
        for (BOSS boss : getBoss) {
            GameTaskList.add(new Task(gameSurface, bitmapData, configure, boss));
        }
    }

    public void update() {
        for (Task t : GameTaskList) {
            t.update();
        }
    }

    public void draw(Canvas canvas) {
        for (Task t : GameTaskList) {
            t.draw(canvas);
        }
    }
}
