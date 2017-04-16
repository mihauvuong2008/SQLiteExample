package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;

/**
 * Created by NgocDong on 09/03/2017.
 */

public class Solider {
    public static float VELOCITY = 0.1f;
    private final Paint myPaint;
    private final Bitmap bitmapPush1;
    private final Bitmap bitmapPush2;
    private final Bitmap bitmapPush3;
    private final Bitmap bitmapPush4;

    private long lastDrawNanoTime = -1;
    private final GameSurface gameSurface;
    private boolean fling;
    private boolean StartFly;
    private final int TO_TOP_TO_RIGHT = 1;
    private final int TO_TOP_TO_LEFT = 2;
    private final int TO_BOT_TO_RIGHT = 3;
    private final int TO_BOT_TO_LEFT = 4;
    private int push = 0;
    private final float deltaXpush1;
    private final float deltaYpush1;
    private final float deltaXpush2;
    private final float deltaYpush2;
    private final float deltaXpush3;
    private final float deltaYpush3;
    private final float deltaXpush4;
    private final float deltaYpush4;
    public float movingVectorX = 1;
    public float movingVectorY = -1;
    private float x;
    private float y;
    private final Bitmap image;
    private Rect bound;

    public Solider(GameSurface gameSurface, Configure configure, BitmapData bitmapData, float x, float y) {
        this.gameSurface = gameSurface;
        myPaint = new Paint();
        myPaint.setColor(Color.rgb(255, 0, 0));
        myPaint.setStrokeWidth(2);
        myPaint.setStyle(Paint.Style.STROKE);
        this.image = Bitmap.createScaledBitmap(bitmapData.getSolider(),
                configure.getSizeConf().getSoliderSize() /**columncount*/, configure.getSizeConf().getSoliderSize(), true);
        this.x = x;
        this.y = y;

        bitmapPush1 = bitmapData.ResizeBitmapWidth(bitmapData.getPush1(), image.getWidth() / 3);
        bitmapPush2 = bitmapData.ResizeBitmapHeight(bitmapData.getPush2(), image.getWidth() / 3);
        bitmapPush3 = bitmapData.ResizeBitmapWidth(bitmapData.getPush3(), image.getWidth() / 3);
        bitmapPush4 = bitmapData.ResizeBitmapHeight(bitmapData.getPush4(), image.getWidth() / 3);

        int width = image.getWidth();
        int height = image.getHeight();

        deltaXpush3 = (width - bitmapPush3.getWidth()) / 2;
        deltaYpush3 = -bitmapPush3.getHeight() * 5 / 3;
        deltaXpush2 = -bitmapPush2.getWidth() * 5 / 3;
        deltaYpush2 = (height - bitmapPush2.getHeight()) / 2;
        deltaXpush1 = (width - bitmapPush1.getWidth()) / 2;
        deltaYpush1 = height + bitmapPush1.getHeight() * 2 / 3;
        deltaXpush4 = (width + bitmapPush4.getWidth() * 2 / 3);
        deltaYpush4 = (height - bitmapPush4.getHeight()) / 2;
    }

    public void update() {
        try {
            if (isStartFly()) {
                long now = System.nanoTime();

                // Chưa vẽ lần nào.
                if (lastDrawNanoTime == -1) {
                    lastDrawNanoTime = now;
                }

                // Đổi nano giây ra mili giây (1 nanosecond = 1000000 millisecond).
                int deltaTime = (int) ((now - lastDrawNanoTime) / 1000000);

                // Quãng đường mà nhân vật đi được (fixel).
                float distance = VELOCITY * deltaTime;

                double movingVectorLength = Math.sqrt(movingVectorX * movingVectorX + movingVectorY * movingVectorY);
                // Tính toán vị trí mới của nhân vật.
                float deltax = (float) (distance * movingVectorX / movingVectorLength);
                float deltay = (float) (distance * movingVectorY / movingVectorLength);
                if (deltax > 0) {
                    if (deltay > 0) {
                        push = TO_BOT_TO_RIGHT;
                    } else {
                        push = TO_TOP_TO_RIGHT;
                    }
                } else {
                    if (deltay > 0) {
                        push = TO_BOT_TO_LEFT;
                    } else {
                        push = TO_TOP_TO_LEFT;
                    }
                }
                this.x = (x + deltax);
                this.y = (y + deltay);

                // Khi nhân vật của game chạm vào cạnh của màn hình thì đổi hướng.
                if (this.x < 0) {
                    this.x = 0;
                    this.movingVectorX = -this.movingVectorX;
                } else if (this.x + (getWidth()) > this.gameSurface.getWidth()) {
                    this.x = this.gameSurface.getWidth() - getWidth();
                    this.movingVectorX = -this.movingVectorX;
                }

                if (this.y < 0) {
                    this.y = 0;
                    this.movingVectorY = -this.movingVectorY;
                } else if (this.y + getHeight() > this.gameSurface.getHeight()) {
                    this.y = this.gameSurface.getHeight() - getHeight();
                    this.movingVectorY = -this.movingVectorY;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return image.getWidth();
    }

    public float getVELOCITY() {
        return VELOCITY;
    }

    public void setVELOCITY(float VELOCITY) {
        Solider.VELOCITY = VELOCITY;
    }

    public void draw(Canvas canvas) {
        try {
            canvas.drawBitmap(image, x, y, null);
            if (isStartFly()) {
                if (push == TO_BOT_TO_RIGHT) { // push3 , push2
                    canvas.drawBitmap(bitmapPush3, x + deltaXpush3, y + deltaYpush3, null);// 1x2
                    canvas.drawBitmap(bitmapPush2, x + deltaXpush2, y + deltaYpush2, null);//2x1
                } else if (push == TO_BOT_TO_LEFT) {// push3 , push4
                    canvas.drawBitmap(bitmapPush3, x + deltaXpush3, y + deltaYpush3, null);//2x1
                    canvas.drawBitmap(bitmapPush4, x + deltaXpush4, y + deltaYpush4, null);// 1x2
                } else if (push == TO_TOP_TO_RIGHT) {// push1 , push2
                    canvas.drawBitmap(bitmapPush2, x + deltaXpush2, y + deltaYpush2, null);// 1x2
                    canvas.drawBitmap(bitmapPush1, x + deltaXpush1, y + deltaYpush1, null);//2x1
                } else if (push == TO_TOP_TO_LEFT) {// push1 , push4
                    canvas.drawBitmap(bitmapPush1, x + deltaXpush1, y + deltaYpush1, null);//2x1
                    canvas.drawBitmap(bitmapPush4, x + deltaXpush4, y + deltaYpush4, null);// 1x2
                }
            }
            bound = new Rect((int) x, (int) y, (int) (x + getWidth()), (int) ((int) y + getHeight()));
            // Thời điểm vẽ cuối cùng (Nano giây).
            this.lastDrawNanoTime = System.nanoTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFling(boolean fling) {
        this.fling = fling;
    }

    public boolean isFling() {
        return fling;
    }

    public void setStartFly(boolean StartFly) {
        this.StartFly = StartFly;
    }

    public boolean isStartFly() {
        return StartFly;
    }

    public float getHeight() {
        return image.getHeight();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rect getBound() {
        return bound;
    }

    public void setMovingVector(float movingVectorX, float movingVectorY) {
        this.movingVectorX = movingVectorX;
        this.movingVectorY = movingVectorY;
    }
}
