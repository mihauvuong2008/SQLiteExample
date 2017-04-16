package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.UniverMap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.object_Bitmap;

/**
 * Created by NgocDong on 16/03/2017.
 */

public class Wormhole {
    private Bitmap image;
    private final Matrix matrix;
    private boolean finish = false;
    private final float radius;
    private float x;
    private float y;
    float angle = 0;

    public Wormhole(Bitmap image, float x, float y) {
        radius = image.getWidth(); // mảng 5 phần tử, chia đôi lấy bán kính
        this.x = x;
        this.y = y;
        this.image = image;
        matrix = new Matrix();
        if (angle > 360) angle = 0;
        else angle += 0.5;
    }

    public void update() {
    }

    public void draw(Canvas canvas) {
        if (!finish) {
            matrix.postRotate((float) angle, x + image.getWidth() / 2, y + image.getHeight() / 2);
            Bitmap tmp = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
            canvas.drawBitmap(tmp, x, y, null);
        }
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
