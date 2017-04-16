package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config;

import android.util.Log;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;

/**
 * Created by NgocDong on 14/03/2017.
 */

public class PositionConf {
    private final GameSurface gameSurface;

    private final int BackgroundWidth;
    private final int BackgroundHeight;
    private final float Menu0PointX;
    private final float Menu0PointY;
    private final float BOSS0PointX;
    private final float BOSS0PointY;
    private final int AttactButtonX;
    private final int AttactButtonY;
    private final double SkillButtonX;
    private final double SkillButtonY;
    private float lineDistX;
    private float lineDistY;
    private float wormHolePointX;
    private float wormHolePointY;

    public PositionConf(GameSurface gameSurface, SizeConf sizeConf) {
        this.gameSurface = gameSurface;
        BackgroundWidth = gameSurface.getWidth();
        BackgroundHeight = gameSurface.getHeight();
        Menu0PointX = BackgroundWidth / 2;
        Menu0PointY = BackgroundHeight / 2 - (sizeConf.getOptionSizeHeight() + sizeConf.getMenuPadding());
        BOSS0PointX = (float) (sizeConf.getBOSSSize() * 0.6);
        BOSS0PointY = (float) (sizeConf.getBOSSSize() * 3.8);
        AttactButtonX = sizeConf.getAttackButtonSize() / 5;
        AttactButtonY = (int) (BackgroundHeight - sizeConf.getAttackButtonSize() - AttactButtonX * 1.5);
        SkillButtonX = BackgroundWidth - sizeConf.getSkillButtonSize() * 1.5;
        SkillButtonY = BackgroundHeight - sizeConf.getSkillButtonSize() * 0.8;
        lineDistX = (BackgroundWidth - sizeConf.getLineDistSize()) / 2;
        lineDistY = (float) (sizeConf.getUnitSize() * 0.8);
        wormHolePointX = getMenu0PointX();
        wormHolePointY = getMenu0PointY();
    }

    public float getMenu0PointX() {
        return Menu0PointX;
    }

    public float getMenu0PointY() {
        return Menu0PointY;
    }

    public float getBOSS0PointX() {
        return BOSS0PointX;
    }

    public float getBOSS0PointY() {
        return BOSS0PointY;
    }

    public int getAttactButtonX() {
        return AttactButtonX;
    }

    public int getAttactButtonY() {
        return AttactButtonY;
    }

    public double getSkillButtonX() {
        return SkillButtonX;
    }

    public double getSkillButtonY() {
        return SkillButtonY;
    }

    public float getLineDistX() {
        return lineDistX;
    }

    public float getLineDistY() {
        return lineDistY;
    }

    public float getWormHolePointX() {
        return wormHolePointX;
    }

    public float getWormHolePointY() {
        return wormHolePointY;
    }
}
