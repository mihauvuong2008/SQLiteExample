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

public class BreakAll extends object_Bitmap {
    private final float dist;
    private int rowIndex = 0;
    private int colIndex = -1;

    private boolean complete_draw = false;

    private GameSurface gameSurface;
    private final float radius;
    private float soliderWidth;
    private final float firstX;
    private final float firstY;
    private boolean finish = false;

    public BreakAll(GameSurface GameSurface, Bitmap image, float x, float y, float dist) {
        super(image, 1, 9, x, y);
        this.gameSurface = GameSurface;
        radius = image.getWidth() / 18; // mảng 5 phần tử, chia đôi lấy bán kính
        firstX = x;
        firstY = y;
        this.dist = dist;
    }

    boolean reloop = false;

    public void update(Solider solider) {
        this.x = solider.getX();
        this.y = solider.getY();
        if (!reloop)
            this.colIndex++;
        else
            this.colIndex--;
        if (this.colIndex >= this.colCount - 1) {
            reloop = true;
            if (reloop && this.colIndex == 0) {
                rowIndex++;
            }
            if (this.rowIndex >= this.rowCount) {
                this.complete_draw = true;
            }
        }
        float deltaX = this.x - firstX;
        float deltaY = this.y - firstY;
        float D = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if (D >= dist) {
            this.finish = true;
        }
        this.soliderWidth = solider.getWidth();
    }

    public void draw(Canvas canvas) {
        if (!complete_draw) {
            Bitmap bitmap = this.createSubImageAt(rowIndex, colIndex);
            if (colIndex >= 0)
                canvas.drawBitmap(bitmap, getTop(), getLeft(), null);
            Paint myPaint = new Paint();
            myPaint.setColor(Color.rgb(255, 0, 0));
            myPaint.setStrokeWidth(10);
            myPaint.setStyle(Paint.Style.STROKE);
            //canvas.drawCircle(getFocusX(), getFocusY(), (float) (getRadius() * 1.6), myPaint);
        }
    }

    public boolean isComplete_draw() {
        return complete_draw;
    }

    public boolean isFinish() {
        return finish;
    }

    public float getRadius() {
        return radius;
    }

    public float getTop() {
        return getFocusX() - radius;
    }

    public float getLeft() {
        return getFocusY() - radius;
    }

    public float getFocusX() {
        return this.x + soliderWidth / 2;
    }

    public float getFocusY() {
        return this.y + soliderWidth / 2;
    }

}
