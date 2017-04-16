package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.skill;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.object_Bitmap;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.Solider;

/**
 * Created by NgocDong on 13/03/2017.
 */

public class blackHole extends object_Bitmap {

    private int rowIndex = 0;
    private int colIndex = -1;

    private boolean finish = false;

    private GameSurface gameSurface;
    private final float radius;
    private float soliderWidth;

    public blackHole(GameSurface GameSurface, Bitmap image, float x, float y) {
        super(image, 5, 5, x, y);
        this.gameSurface = GameSurface;
        radius = image.getWidth() / 10; // mảng 5 phần tử, chia đôi lấy bán kính
    }

    public void update(Solider solider) {
        this.x = solider.getX();
        this.y = solider.getY();
        this.colIndex++;
        if (this.colIndex >= this.colCount) {
            this.colIndex = 0;
            this.rowIndex++;

            if (this.rowIndex >= this.rowCount) {
                this.finish = true;
            }
        }
        this.soliderWidth = solider.getHeight();
    }

    public void draw(Canvas canvas) {
        if (!finish) {
            Bitmap bitmap = this.createSubImageAt(rowIndex, colIndex);
            if (colIndex >= 0)
                canvas.drawBitmap(bitmap, getTop(), getLeft(), null);
//            Paint myPaint = new Paint();
//            myPaint.setColor(Color.rgb(255, 0, 0));
//            myPaint.setStrokeWidth(2);
//            myPaint.setStyle(Paint.Style.STROKE);
            // canvas.drawCircle(getFocusX(), getFocusY(), (float) (getRadius() * 1.6), myPaint);
        }
    }


    public boolean isFinish() {
        return finish;
    }

    public float getRadius() {
        return radius;
    }

    public float getTop() {
        return (float) (getFocusX() - radius - 0.5);
    }

    public float getLeft() {
        return (float) (getFocusY() - radius - 1);
    }

    public float getFocusX() {
        return this.x + soliderWidth / 2;
    }

    public float getFocusY() {
        return this.y + soliderWidth / 2;
    }

}
