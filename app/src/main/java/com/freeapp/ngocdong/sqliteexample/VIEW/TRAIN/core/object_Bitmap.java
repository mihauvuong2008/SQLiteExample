package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by NgocDong on 08/03/2017.
 */

public abstract class object_Bitmap {
    protected Bitmap image;

    protected final int rowCount;
    protected final int colCount;

    protected final float WIDTH;
    protected final float HEIGHT;

    protected final float width;
    protected final float height;
    public float movingVectorX = 1;
    public float movingVectorY = 1;
    public Rect bound;
    protected float x;
    protected float y;

    public object_Bitmap(Bitmap image, int rowCount, int colCount, float x, float y) {

        this.image = image;
        this.rowCount = rowCount;
        this.colCount = colCount;

        this.x = x;
        this.y = y;

        this.WIDTH = image.getWidth();
        this.HEIGHT = image.getHeight();

        this.width = this.WIDTH / colCount;
        this.height = this.HEIGHT / rowCount;
    }

    protected Bitmap createSubImageAt(int row, int col) {
        // createBitmap(bitmap, x, y, width, height).
        float x = col * width;
        float y = row * height;
        Bitmap subImage = Bitmap.createBitmap(image, x < 0 ? 0 : (int) (x), y < 0 ? 0 : (int) (y),
                (int) (width + 0.5), (int) (height + 0.5));
        return subImage;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public Rect getBound() {
        return bound;
    }

    public void setMovingVector(float movingVectorX, float movingVectorY) {
        this.movingVectorX = movingVectorX;
        this.movingVectorY = movingVectorY;
    }
}
