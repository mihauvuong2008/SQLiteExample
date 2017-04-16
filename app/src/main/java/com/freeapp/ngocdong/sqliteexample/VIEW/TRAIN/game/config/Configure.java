package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;

/**
 * Created by NgocDong on 14/03/2017.
 */

public class Configure {
    private GameSurface gameSurface;
    private SizeConf sizeConf;
    private PositionConf positionConf;

    public Configure(GameSurface gameSurface) {
        this.gameSurface = gameSurface;
        sizeConf = new SizeConf(gameSurface);
        positionConf = new PositionConf(gameSurface, sizeConf);
    }

    public SizeConf getSizeConf() {
        return sizeConf;
    }

    public PositionConf getPositionConf() {
        return positionConf;
    }
}
