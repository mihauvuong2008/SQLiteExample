package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.control;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.BitmapData;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;

/**
 * Created by NgocDong on 13/03/2017.
 */

public class List_Skill_button {
    private final Configure configure;
    Skill_button A;
    Skill_button B;
    Skill_button C;
    Skill_button D;
    int Skill = 0;

    int Min(int a, int b) {
        if (a < b) return a;
        else return b;
    }

    public List_Skill_button(GameSurface gameSurface, BitmapData bitmapData, Configure configure) {
//        A = new Skill_button(gameSurface, ScaleBitmap(bitmapData.getBitmapA(), size, size), ScaleBitmap(bitmapData.getBitmapA2(), size, size), (int) (maxX - 4.4 * size), maxY);
//        B = new Skill_button(gameSurface, ScaleBitmap(bitmapData.getBitmapB(), size, size), ScaleBitmap(bitmapData.getBitmapB2(), size, size), (int) (maxX - 3.2 * size), maxY);
//        C = new Skill_button(gameSurface, ScaleBitmap(bitmapData.getBitmapC(), size, size), ScaleBitmap(bitmapData.getBitmapC2(), size, size), maxX - 2 * size, maxY);
//        D = new Skill_button(gameSurface, ScaleBitmap(bitmapData.getBitmapD(), size, size), ScaleBitmap(bitmapData.getBitmapD2(), size, size), (int) (maxX - 0.8 * size), maxY);
        this.configure = configure;
        A = new Skill_button(gameSurface, ScaleBitmap(bitmapData.getBitmapA(),
                configure.getSizeConf().getSkillButtonSize(), configure.getSizeConf().getSkillButtonSize()),
                ScaleBitmap(bitmapData.getBitmapA2(), configure.getSizeConf().getSkillButtonSize(),
                        configure.getSizeConf().getSkillButtonSize()), (int) configure.getPositionConf().getSkillButtonX() - 3 * (configure.getSizeConf().getSkillButtonSize() + configure.getSizeConf().getSkillButtonPadding()), (int) configure.getPositionConf().getSkillButtonY());
        B = new Skill_button(gameSurface, ScaleBitmap(bitmapData.getBitmapB(),
                configure.getSizeConf().getSkillButtonSize(), configure.getSizeConf().getSkillButtonSize()),
                ScaleBitmap(bitmapData.getBitmapB2(), configure.getSizeConf().getSkillButtonSize(),
                        configure.getSizeConf().getSkillButtonSize()), (int) configure.getPositionConf().getSkillButtonX() - 2 * (configure.getSizeConf().getSkillButtonSize() + configure.getSizeConf().getSkillButtonPadding()), (int) configure.getPositionConf().getSkillButtonY());
        C = new Skill_button(gameSurface, ScaleBitmap(bitmapData.getBitmapC(),
                configure.getSizeConf().getSkillButtonSize(), configure.getSizeConf().getSkillButtonSize()),
                ScaleBitmap(bitmapData.getBitmapC2(), configure.getSizeConf().getSkillButtonSize(),
                        configure.getSizeConf().getSkillButtonSize()), (int) configure.getPositionConf().getSkillButtonX() - configure.getSizeConf().getSkillButtonSize() - configure.getSizeConf().getSkillButtonPadding(), (int) configure.getPositionConf().getSkillButtonY());
        D = new Skill_button(gameSurface, ScaleBitmap(bitmapData.getBitmapD(),
                configure.getSizeConf().getSkillButtonSize(), configure.getSizeConf().getSkillButtonSize()),
                ScaleBitmap(bitmapData.getBitmapD2(), configure.getSizeConf().getSkillButtonSize(),
                        configure.getSizeConf().getSkillButtonSize()), (int) configure.getPositionConf().getSkillButtonX(), (int) configure.getPositionConf().getSkillButtonY());

    }

    Bitmap ScaleBitmap(Bitmap bitmap, int Width, int Height) {
        return Bitmap.createScaledBitmap(bitmap, Width, Height, true);
    }

    public void draw(Canvas canvas) {
        A.draw(canvas);
        B.draw(canvas);
        C.draw(canvas);
        D.draw(canvas);
    }

    public void update() {
        A.update();
        B.update();
        C.update();
        D.update();
    }

    public void onPress(MotionEvent e) {
        if (A.onPress(e)) {
            B.deSelect();
            C.deSelect();
            D.deSelect();
            Skill = 1;
        } else if (B.onPress(e)) {
            A.deSelect();
            C.deSelect();
            D.deSelect();
            Skill = 2;
        } else if (C.onPress(e)) {
            B.deSelect();
            A.deSelect();
            D.deSelect();
            Skill = 3;
        } else if (D.onPress(e)) {
            B.deSelect();
            C.deSelect();
            A.deSelect();
            Skill = 4;
        }

    }

    public int getSkill() {
        return Skill;
    }
}
