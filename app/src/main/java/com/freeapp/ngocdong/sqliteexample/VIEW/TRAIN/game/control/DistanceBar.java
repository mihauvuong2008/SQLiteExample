package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.control;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.BitmapData;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;

/**
 * Created by NgocDong on 15/03/2017.
 */

public class DistanceBar {
    private final Configure configure;
    private final float X;
    private final float Y;
    private final float EndX;
    private final float partSize;
    private Bitmap lineDistance;
    private Bitmap PartLineDistance;
    private int MaxDist = 100;
    private int CurrDist = 100;
    private int nuMOfpart = 30;// 100%

    public DistanceBar(GameSurface gameSurface, BitmapData bitmapData, Configure configure) {
        this.configure = configure;
        lineDistance = bitmapData.ResizeBitmapWidth(bitmapData.getlineDistance(), configure.getSizeConf().getLineDistSize());
        PartLineDistance = bitmapData.ResizeBitmapWidth(bitmapData.getpartlineDistance(),
                configure.getSizeConf().getPartLineDistSize());
        X = configure.getPositionConf().getLineDistX();
        Y = configure.getPositionConf().getLineDistY();
        EndX = X + lineDistance.getWidth();
        partSize = PartLineDistance.getWidth();
    }

    public void update() {
        nuMOfpart = 30 * CurrDist / MaxDist;
    }

    public void draw(Canvas canvas) {
        try {
            canvas.drawBitmap(lineDistance, X, Y, null);
            for (int i = 1; i <= nuMOfpart + 1; i++) {
                canvas.drawBitmap(PartLineDistance, EndX - (i * partSize), Y, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
