package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.option;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.object_Bitmap;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;

/**
 * Created by NgocDong on 08/03/2017.
 */

public class option extends object_Bitmap {
    private int colUsing;
    private Bitmap[] row_;

    private long lastDrawNanoTime = -1;
    private GameSurface gameSurface;
    private boolean selected = false;

    private int MODE = 0;

    public option(GameSurface gameSurface, Bitmap image, int x, int y) {
        super(image, 1, 3, x, y);
        this.gameSurface = gameSurface;
        this.row_ = new Bitmap[colCount]; // 3

        for (int col = 0; col < this.colCount; col++) {
            this.row_[col] = this.createSubImageAt(0, col);
        }
    }

    public Bitmap[] getMoveBitmaps() {
        return this.row_;
    }

    public Bitmap getCurrentMoveBitmap() {
        Bitmap[] bitmaps = this.getMoveBitmaps();
        return bitmaps[this.colUsing];
    }

    public void update() {
        if (!selected)
            this.colUsing++;
        if (colUsing >= this.colCount) {
            this.colUsing = 0;
        }

        // Thời điểm hiện tại theo nano giây.
        long now = System.nanoTime();

        // Chưa vẽ lần nào.
        if (lastDrawNanoTime == -1) {
            lastDrawNanoTime = now;
        }
    }

    public void draw(Canvas canvas) {
        Bitmap bitmap = this.getCurrentMoveBitmap();
        canvas.drawBitmap(bitmap, x, y, null);
        if (selected) {
            Paint myPaint = new Paint();
            myPaint.setColor(Color.rgb(255, 0, 0));
            myPaint.setStrokeWidth(2);
            myPaint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(x, y, x + width, y + height, myPaint);
        }
        // Thời điểm vẽ cuối cùng (Nano giây).
        this.lastDrawNanoTime = System.nanoTime();
    }

    public void setSelected(boolean b) {
        this.selected = b;
    }

    public boolean getSelected() {
        return this.selected;
    }

    public int getMODE() {
        return MODE;
    }

    public void setMODE(int MODE) {
        this.MODE = MODE;
    }

}
