package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

/**
 * Created by NgocDong on 08/03/2017.
 */

public class object_Text {

    protected float WIDTH;
    protected float HEIGHT;
    private final Paint paint;

    protected float x;
    protected float y;
    private float movingVectorX;
    private float movingVectorY;
    protected String text;
    private int size = 80;
    private Rect bound;

    public object_Text(String text, float x, float y) {
        this.text = text;
        this.x = x;
        this.y = y;
        paint = new Paint();
        paint.setTypeface(Typeface.DEFAULT);// your preference here
        paint.setTextSize(size);// have this the same as your text size
        bound = new Rect();
        paint.getTextBounds(text, 0, text.length(), bound);
        setBoundsSize();
    }

    private void setBoundsSize() {
        paint.getTextBounds(text, 0, text.length(), bound);
        this.WIDTH = bound.width();
        this.HEIGHT = bound.height();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getHeight() {
        return HEIGHT;
    }

    public float getWidth() {
        return WIDTH;
    }

    public Paint getPaint() {
        return paint;
    }


    public void setSize(int size) {
        this.size = size;
        paint.setTextSize(size);// have this the same as your text size
        setBoundsSize();
    }
}
