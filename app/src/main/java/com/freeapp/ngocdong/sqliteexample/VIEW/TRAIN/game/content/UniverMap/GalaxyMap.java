package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.UniverMap;

import android.graphics.Canvas;
import android.util.Log;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.BitmapData;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.Solider;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.GameScript.GameScript;

/**
 * Created by NgocDong on 16/03/2017.
 */

public class GalaxyMap {
    private final GameSurface gameSurface;
    private final BitmapData bitmapData;
    private final Configure configure;
    Door door;
    private boolean draw = false;
    private Integer idRoomSelected;
    private boolean isInMap = false;

    public GalaxyMap(GameSurface gameSurface, BitmapData bitmapData, Configure configure) {
        this.gameSurface = gameSurface;
        this.bitmapData = bitmapData;
        this.configure = configure;
        door = new Door(bitmapData, configure);
    }

    public void update() {
        if (draw) {
            // TODO UPDATE MAP
        } else {
            door.update();
        }
    }

    public void draw(Canvas canvas) {
        if (draw) {
            // TODO SHOW MAP
        } else {
            door.draw(canvas);
        }
    }

    public void openDoor(Solider solider) {
        door.openWormHole(solider);
    }


    public boolean isPassDoor() {
        return door.isPassDoor();
    }

    public void goMap(Solider solider) {
        if (!isInMap) {
            idRoomSelected = 0;
            openDoor(solider);
            if (isPassDoor()) {
                ShowMap();
            }
            isInMap = true;
        }
    }

    private void ShowMap() {
        draw = true;
    }

    public Integer outMap() {
        if (idRoomSelected > 0) {
            //draw = false;
            //isInMap = false;
        }
        return idRoomSelected;
    }

}
