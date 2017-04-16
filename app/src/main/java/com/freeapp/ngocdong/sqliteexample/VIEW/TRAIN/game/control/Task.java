package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.control;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.BitmapData;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.GameScript.BOSS;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;

/**
 * Created by NgocDong on 16/03/2017.
 */

public class Task {
    private final Configure configure;
    private final float X;
    private final float Y;
    private final Paint MyPaint;
    private final Bitmap uncompletesingletask;
    private final Bitmap complesingletask;
    private Bitmap TaskBackground;
    private final GameSurface gameSurface;
    BOSS Boss;
    private Rect bound;
    private int TaskWidth = 0;

    public Task(GameSurface gameSurface, BitmapData bitmapData, Configure configure, BOSS boss) {
        this.gameSurface = gameSurface;
        this.configure = configure;
        this.Boss = boss;

        this.X = configure.getPositionConf().getBOSS0PointX();
        this.Y = configure.getPositionConf().getBOSS0PointY() + configure.getSizeConf().getBOSSSize() * Boss.getIdBOSS();
        MyPaint = new Paint();
        MyPaint.setColor(Boss.getColor());
        MyPaint.setTypeface(Typeface.DEFAULT);// your preference here
        MyPaint.setTextSize(configure.getSizeConf().getBOSSSize() * 2 / 3);// have this the same as your text size
        bound = new Rect();
        MyPaint.getTextBounds(Boss.getBOSS(), 0, Boss.getBOSS().length(), bound);
        TaskWidth = (int) (bound.width() + MyPaint.getTextSize() + configure.getSizeConf().getBOSSSize());
        TaskBackground = bitmapData.ResizeBitmap(bitmapData.getTaskBackground(),
                TaskWidth, configure.getSizeConf().getBOSSSize());
        complesingletask = bitmapData.ResizeBitmap(bitmapData.getComplesingletask(),
                configure.getSizeConf().getBOSSSize(), configure.getSizeConf().getBOSSSize());
        uncompletesingletask = bitmapData.ResizeBitmap(bitmapData.getUncompletesingletask(),
                configure.getSizeConf().getBOSSSize(), configure.getSizeConf().getBOSSSize());
    }

    public void update() {
    }

    public void draw(Canvas canvas) {
        if (Boss != null)
            try {
                canvas.drawBitmap(TaskBackground, X, Y, null);
                if (Boss.getKilled())
                    canvas.drawBitmap(complesingletask, X + TaskWidth - complesingletask.getWidth(), Y, null);
                else
                    canvas.drawBitmap(uncompletesingletask, X + TaskWidth - uncompletesingletask.getWidth(), Y, null);
                MyPaint.setAlpha(210);
                canvas.drawText(Boss.getBOSS(), X + MyPaint.getTextSize() * 2 / 3, Y - (MyPaint.getTextSize() / 3)
                        + configure.getSizeConf().getBOSSSize(), MyPaint);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
