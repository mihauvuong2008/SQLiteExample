package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.skill;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.freeapp.ngocdong.sqliteexample.R;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.Effect;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.Explosion_objectBitmap;

/**
 * Created by NgocDong on 12/03/2017.
 */

public class Pencil_Bullet {
    public static final float VELOCITY = 0.32f;
    private final float movingVectorX;
    private final float movingVectorY;
    private final int PencilSize;
    private Bitmap pencil_bitmap;
    private float x;
    private float y;
    private Bitmap image;

    private long lastDrawNanoTime = -1;
    private final GameSurface gameSurface;
    private Rect bound;
    int countDraw = 1;
    private boolean destroyed = false;
    private Bitmap exploseBitmap;
    private int damage = 120;

    public Pencil_Bullet(GameSurface gameSurface, Bitmap image, float x, float y, float movingVectorX, float movingVectorY, int PencilSize) {
        this.image = image;
//        image = Bitmap.createScaledBitmap(image, 200, 200, true);
        this.gameSurface = gameSurface;
        this.movingVectorX = movingVectorX;
        this.movingVectorY = (float) (movingVectorX * -0.5);
        this.x = x;
        this.y = y;
        this.PencilSize = PencilSize;
        exploseBitmap = BitmapFactory.decodeResource(gameSurface.getResources(), R.drawable.explose);

        Matrix m = new Matrix();
        m.preScale(movingVectorX < 0 ? 1 : -1, 1);
        pencil_bitmap = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), m, false);
    }

    public void draw(Canvas canvas) {
        Paint myPaint = new Paint();
        myPaint.setColor(Color.rgb(255, 0, 0));
        myPaint.setStrokeWidth(5);
        myPaint.setStyle(Paint.Style.STROKE);
        pencil_bitmap = Bitmap.createScaledBitmap(pencil_bitmap, PencilSize, PencilSize, true);
        canvas.drawBitmap(pencil_bitmap, drawXX(), y, myPaint);
        bound = new Rect((int) drawXX(), (int) y, (int) drawXX() + pencil_bitmap.getWidth(), (int) y + pencil_bitmap.getHeight());
        //canvas.drawRect(bound, myPaint);
        // Thời điểm vẽ cuối cùng (Nano giây).
        this.lastDrawNanoTime = System.nanoTime();
    }

    private float drawXX() {
        return movingVectorX < 0 ? x : x + PencilSize * 5 / 2;
    }

    public Rect getBound() {
        return bound;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public void update(Effect effect) {
        if (destroyed) {
            Explosion_objectBitmap explosion = new Explosion_objectBitmap(gameSurface,
                    Bitmap.createScaledBitmap(exploseBitmap, exploseBitmap.getWidth() / 2, exploseBitmap.getHeight() / 2, true),
                    (int) drawXX(), (int) getY());
            effect.addNew(explosion);
        }
        countDraw++;
        // Thời điểm hiện tại theo nano giây.
        long now = System.nanoTime();

        // Chưa vẽ lần nào.
        if (lastDrawNanoTime == -1) {
            lastDrawNanoTime = now;
        }

        // Đổi nano giây ra mili giây (1 nanosecond = 1000000 millisecond).
        int deltaTime = (int) ((now - lastDrawNanoTime) / 1000000);

        // Quãng đường mà nhân vật đi được (fixel).
        float distance = VELOCITY * deltaTime;

        double movingVectorLength = Math.sqrt(movingVectorX * movingVectorX);
        // Tính toán vị trí mới của nhân vật.
        this.x = (float) (x + (distance * movingVectorX / movingVectorLength));
        this.y = (float) (y + (distance * 0.0068 * countDraw * countDraw));

    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void Destroy() {
        this.destroyed = true;
    }

    public int getDamage() {
        return damage;
    }
}
