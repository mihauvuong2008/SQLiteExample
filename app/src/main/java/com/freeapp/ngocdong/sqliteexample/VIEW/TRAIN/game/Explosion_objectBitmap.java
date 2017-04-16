package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.object_Bitmap;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;

/**
 * Created by NgocDong on 08/03/2017.
 */

public class Explosion_objectBitmap extends object_Bitmap {
    private int rowIndex = 0;
    private int colIndex = -1;

    private boolean finish = false;
    private GameSurface gameSurface;

    public Explosion_objectBitmap(GameSurface GameSurface, Bitmap image, int x, int y) {
        super(image, 5, 5, x, y);

        this.gameSurface = GameSurface;
    }

    public void update() {
        this.colIndex++;

        if (this.colIndex >= this.colCount) {
            this.colIndex = 0;
            this.rowIndex++;

            if (this.rowIndex >= this.rowCount) {
                this.finish = true;
            }
        }
    }

    public void draw(Canvas canvas) {
        if (!finish) {
            Bitmap bitmap = this.createSubImageAt(rowIndex, colIndex);
            canvas.drawBitmap(bitmap, this.x - getWidth() / 2, this.y - getHeight() / 2, null);
        }
    }

    public boolean isFinish() {
        return finish;
    }
}
