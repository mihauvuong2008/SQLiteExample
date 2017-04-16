package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.GameScript;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.object_Text;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.BitmapData;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;

/**
 * Created by NgocDong on 08/03/2017.
 */

public class CAMBAT extends object_Text {
    private final Paint myPaint;

    // Dòng ảnh đang được sử dụng.

    // Vận tốc di chuyển của nhân vật (pixel/milisecond).
    private float VELOCITY = 0.1f;
    private final int color;

    private long lastDrawNanoTime = -1;
    private GameSurface gameSurface;
    private int Healthy = 1000;
    private Rect mybound;
    private int idCAMB = 0;
    private boolean killed;
    private boolean isAttack = false;
    private Bitmap[] myhealthyMap;
    public float movingVectorX = 10;
    public float movingVectorY = 5;
    private final Configure configure;
    private float deltaY;
    int currHealthy;
    int numOfPartHealthy;
    int widthparthealthy = 0;

    public CAMBAT(GameSurface gameSurface, String text, int color, BitmapData bitmapData,
                  Configure configure, float x, float y) {
        super(text, x, y);
        this.gameSurface = gameSurface;
        this.color = color;
        myhealthyMap = new Bitmap[]{
                bitmapData.ResizeBitmap(bitmapData.getHealthyBitmap()[0],
                        configure.getSizeConf().getHealthyBarSize() * 20, configure.getSizeConf().getHealthyBarSize()),
                bitmapData.ResizeBitmap(bitmapData.getHealthyBitmap()[1],
                        configure.getSizeConf().getHealthyBarSize(), configure.getSizeConf().getHealthyBarSize())
        };
        numOfPartHealthy = myhealthyMap[0].getWidth() / myhealthyMap[1].getWidth();
        widthparthealthy = myhealthyMap[1].getWidth();
        this.configure = configure;
        deltaY = configure.getSizeConf().getCAMBSize() + configure.getSizeConf().getHealthyBarSize()
                + configure.getSizeConf().getHealthyBarPaddingSize();

        myPaint = new Paint();
        myPaint.setColor(Color.rgb(255, 0, 0));
        myPaint.setStrokeWidth(5);
        myPaint.setStyle(Paint.Style.STROKE);
    }

    public void update() {
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

        double movingVectorLength = Math.sqrt(movingVectorX * movingVectorX + movingVectorY * movingVectorY);

        // Tính toán vị trí mới của nhân vật.
        this.x = (float) (x + (distance * movingVectorX / (movingVectorLength)));
        this.y = (float) (y + (distance * movingVectorY / (movingVectorLength)));

        // Khi nhân vật của game chạm vào cạnh của màn hình thì đổi hướng.
        if (this.x < 0) {
            this.x = 0;
            this.movingVectorX = -this.movingVectorX;
        } else if (this.x + getWidth() > this.gameSurface.getWidth()) {
            this.x = this.gameSurface.getWidth() - getWidth();
            this.movingVectorX = -this.movingVectorX;
        }

        if (this.y - getHeight() < 0) {
            this.y = getHeight();
            this.movingVectorY = -this.movingVectorY;
        } else if (this.y > this.gameSurface.getHeight()) {
            this.y = this.gameSurface.getHeight();
            this.movingVectorY = -this.movingVectorY;
        }
    }

    public void draw(Canvas canvas) {
        try {
            getPaint().setColor(color);
            canvas.drawText(text, x, y, getPaint());
            mybound = new Rect((int) getX(), (int) (getY() - getHeight()), (int) (getX() + getWidth()), (int) y);
            //canvas.drawRect(mybound, myPaint);
            if (Healthy <= 1000) {
                float drawY = y - deltaY;
                currHealthy = Healthy * numOfPartHealthy / 1000;
                canvas.drawBitmap(myhealthyMap[0], x, drawY, getPaint());
                float endPoint = x + myhealthyMap[0].getWidth();
                for (int i = 1; i <= currHealthy; i++) {
                    canvas.drawBitmap(myhealthyMap[1], endPoint - (i * widthparthealthy), drawY, getPaint());
                }
            }
        } catch (Exception e) {
            Log.e("TAG", "run() lockCanvas()", e);
        } finally {
            if (canvas != null) {
            }
        }
        this.lastDrawNanoTime = System.nanoTime();
    }

    public void setMovingVector(float movingVectorX, float movingVectorY) {
        this.movingVectorX = movingVectorX;
        this.movingVectorY = movingVectorY;
    }

    public void setVELOCITY(float VELOCITY) {
        this.VELOCITY = VELOCITY;
    }

    public float getVELOCITY() {
        return VELOCITY;
    }

    public Rect getMybound() {
        return mybound;
    }

    public void Attacked(int damage) {
        if (damage > 0) {
            Healthy -= damage;
        }
    }

    public int getHealthy() {
        return Healthy;
    }

    public void setIdCAMB(int idCAMB) {
        this.idCAMB = idCAMB;
    }

    public int getIdCAMB() {
        return idCAMB;
    }

    public boolean getKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }
}
