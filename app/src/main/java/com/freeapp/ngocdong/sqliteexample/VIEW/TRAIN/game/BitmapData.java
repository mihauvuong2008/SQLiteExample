package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.freeapp.ngocdong.sqliteexample.R;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;

/**
 * Created by NgocDong on 14/03/2017.
 */

public class BitmapData {
    private final GameSurface gameSurface;
    private final Bitmap[] HealthyBitmap;
    private final Bitmap explose;
    private final Bitmap breakSkill;
    private final Bitmap blackHole;
    private final Bitmap pencil;
    private final Bitmap solider;
    private final Bitmap attackbutton;
    private final Bitmap attackbutton2;
    private final Bitmap bitmapA;
    private final Bitmap bitmapA2;
    private final Bitmap bitmapB;
    private final Bitmap bitmapB2;
    private final Bitmap bitmapC;
    private final Bitmap bitmapC2;
    private final Bitmap bitmapD;
    private final Bitmap bitmapD2;
    private final Bitmap option_hard;
    private final Bitmap optiom_medium;
    private final Bitmap optiom_easy;
    private final Bitmap push1;
    private final Bitmap push2;
    private final Bitmap push3;
    private final Bitmap push4;
    private Bitmap partlineDistance;
    private Bitmap lineDistance;
    private Bitmap taskBackground;
    private Bitmap complesingletask;
    private Bitmap uncompletesingletask;
    private Bitmap wormHole;
    private Bitmap background01;
    private Bitmap ruler;

    public BitmapData(GameSurface gameSurface) {
        this.gameSurface = gameSurface;
        // menu
        option_hard = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.option_hard);
        optiom_medium = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.option_medium);
        optiom_easy = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.option_easy);

        // Nút tấn công
        this.attackbutton = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.attackbutton);
        this.attackbutton2 = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.attackbutton2);
        // nút skill
        bitmapA = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.a);
        bitmapA2 = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.a2);
        bitmapB = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.b);
        bitmapB2 = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.b2);
        bitmapC = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.c);
        bitmapC2 = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.c2);
        bitmapD = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.d);
        bitmapD2 = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.d2);
        HealthyBitmap = new Bitmap[]{
                BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.h0),
                BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.hunit)
        };
        explose = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.explose);
        blackHole = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.map_blackhole);
        pencil = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.pencil);
        solider = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.solider);
        push1 = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.push1);
        push2 = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.push2);
        push3 = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.push3);
        push4 = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.push4);
        breakSkill = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.breakskill);
        partlineDistance = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.partlinedistance);
        lineDistance = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.linedistance);
        taskBackground = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.taskbackground);
        complesingletask = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.complesingletask);
        uncompletesingletask = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.uncompletesingletask);
        wormHole = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.wormhole);
        background01 = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.background01);
        ruler = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.ruler);
    }

    public Bitmap ResizeBitmap(Bitmap temp, int Width, int Height) {
        return Bitmap.createScaledBitmap(temp, Width, Height, true);
    }

    public Bitmap ResizeBitmapHeight(Bitmap temp, int Height) {
        int Width = Height * temp.getWidth() / temp.getHeight();
        return Bitmap.createScaledBitmap(temp, Width, Height, true);
    }

    public Bitmap ResizeBitmapWidth(Bitmap temp, int Width) {
        int Height = Width * temp.getHeight() / temp.getWidth();
        return Bitmap.createScaledBitmap(temp, Width, Height, true);
    }

    public Bitmap getOption_hard() {
        return option_hard;
    }

    public Bitmap getOptiom_medium() {
        return optiom_medium;
    }

    public Bitmap getOptiom_easy() {
        return optiom_easy;
    }

    public Bitmap[] getHealthyBitmap() {
        return HealthyBitmap;
    }

    public Bitmap getExplose() {
        return explose;
    }

    public Bitmap getBreakSkill() {
        return breakSkill;
    }

    public Bitmap getBlackHole() {
        return blackHole;
    }

    public Bitmap getPencil() {
        return pencil;
    }

    public Bitmap getSolider() {
        return solider;
    }

    public Bitmap getPush1() {
        return push1;
    }

    public Bitmap getPush2() {
        return push2;
    }

    public Bitmap getPush3() {
        return push3;
    }

    public Bitmap getPush4() {
        return push4;
    }

    public Bitmap getAttackbutton() {
        return attackbutton;
    }

    public Bitmap getAttackbutton2() {
        return attackbutton2;
    }

    public Bitmap getBitmapA() {
        return bitmapA;
    }

    public Bitmap getBitmapA2() {
        return bitmapA2;
    }

    public Bitmap getBitmapB() {
        return bitmapB;
    }

    public Bitmap getBitmapB2() {
        return bitmapB2;
    }

    public Bitmap getBitmapC() {
        return bitmapC;
    }

    public Bitmap getBitmapC2() {
        return bitmapC2;
    }

    public Bitmap getBitmapD() {
        return bitmapD;
    }

    public Bitmap getBitmapD2() {
        return bitmapD2;
    }

    public Bitmap getpartlineDistance() {
        return partlineDistance;
    }

    public Bitmap getlineDistance() {
        return lineDistance;
    }

    public Bitmap getTaskBackground() {
        return taskBackground;
    }

    public Bitmap getComplesingletask() {
        return complesingletask;
    }

    public Bitmap getUncompletesingletask() {
        return uncompletesingletask;
    }

    public Bitmap getWormHole() {
        return wormHole;
    }


    public Bitmap getBackground01() {
        return background01;
    }

    public Bitmap getRuler() {
        return ruler;
    }
}
