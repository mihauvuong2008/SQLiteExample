package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.UniverMap;

import android.graphics.Canvas;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.BitmapData;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.Solider;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;

/**
 * Created by NgocDong on 16/03/2017.
 */

public class Door {
    private final BitmapData bitmapData;
    private final Configure configure;
    boolean doorOpened = false;
    Wormhole wormhole;
    private boolean passDoor = false;

    public Door(BitmapData bitmapData, Configure configure) {
        this.bitmapData = bitmapData;
        this.configure = configure;
    }

    public void update() {
        if (wormhole != null)
            wormhole.update();
    }

    public void draw(Canvas canvas) {
        if (wormhole != null)
            wormhole.draw(canvas);
    }

    public void openWormHole(Solider solider) {
        if (!doorOpened) {
            wormhole = new Wormhole(bitmapData.getWormHole(), configure.getPositionConf().getWormHolePointX(),
                    configure.getPositionConf().getWormHolePointY());
            doorOpened = true;
            passDoor = false;
        }
    }

    public void closeWormHole() {
        wormhole = null;
        doorOpened = false;
    }

    public boolean isPassDoor() {
        return passDoor;
    }
}
