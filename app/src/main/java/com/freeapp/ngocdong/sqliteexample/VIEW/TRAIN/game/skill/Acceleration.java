package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.skill;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.BitmapData;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.Solider;

/**
 * Created by NgocDong on 13/03/2017.
 */

public class Acceleration {// Tang toc
    private final float basicSpeed;
    private final BitmapData bitmapData;
    private float degree;
    private final float Y;
    private final float X;
    private float DistanceX;
    private float DistanceY;
    private final Solider solider;
    private final int distance;
    boolean isvalid = false;
    private float D = 0;
    private boolean StartAccelerat;
    private float stopCount = 0;
    private Bitmap rulerBitmap;

    public Acceleration(int distance, Solider solider, BitmapData bitmapData) {
        this.DistanceX = solider.getX();
        this.DistanceY = solider.getY();
        this.basicSpeed = solider.getVELOCITY();
        this.solider = solider;
        this.distance = distance;
        this.bitmapData = bitmapData;
        isvalid = true;
        float unit = (float) Math.sqrt(solider.movingVectorX * solider.movingVectorX + solider.movingVectorY * solider.movingVectorY);
        degree = (float) (180 * Math.acos(solider.movingVectorX / unit) / Math.PI);
        Matrix matrix = new Matrix();

        if (solider.movingVectorX > 0) {
            if (solider.movingVectorY > 0) {
                matrix.postRotate(degree);
                rulerBitmap = Bitmap.createBitmap(bitmapData.getRuler(), 0, 0, distance,
                        bitmapData.getRuler().getHeight() * distance / bitmapData.getRuler().getWidth(), matrix, false);
                X = solider.getX() + solider.getWidth() / 2;
                Y = solider.getY() + solider.getHeight() / 2;

            } else {
                degree = degree * -1;
                matrix.postRotate(degree);
                rulerBitmap = Bitmap.createBitmap(bitmapData.getRuler(), 0, 0, distance,
                        bitmapData.getRuler().getHeight() * distance / bitmapData.getRuler().getWidth(), matrix, false);
                X = solider.getX() + solider.getWidth() / 2;
                Y = solider.getY() + solider.getHeight() / 2 - rulerBitmap.getHeight();
            }
        } else {
            if (solider.movingVectorY > 0) {
                matrix.postRotate(degree);
                rulerBitmap = Bitmap.createBitmap(bitmapData.getRuler(), 0, 0, distance,
                        bitmapData.getRuler().getHeight() * distance / bitmapData.getRuler().getWidth(), matrix, false);
                X = solider.getX() + solider.getWidth() / 2 - rulerBitmap.getWidth();
                Y = solider.getY() + solider.getHeight() / 2;
            } else {
                degree = degree * -1;
                matrix.postRotate(degree);
                rulerBitmap = Bitmap.createBitmap(bitmapData.getRuler(), 0, 0, distance,
                        bitmapData.getRuler().getHeight() * distance / bitmapData.getRuler().getWidth(), matrix, false);
                X = solider.getX() + solider.getWidth() / 2 - rulerBitmap.getWidth();
                Y = solider.getY() + solider.getHeight() / 2 - rulerBitmap.getHeight();
            }
        }

    }

    public void update() {
        float curDistanceX = solider.getX();
        float curDistanceY = solider.getY();
        D += (float) Math.sqrt(Math.pow(curDistanceX - DistanceX, 2) + Math.pow(curDistanceY - DistanceY, 2));
        // Chay duoc 1/5 quang duong thuc hien tang toc
        if (D >= distance / 5 && D < distance) {
            StartAccelerat = true;// cho phep tang toc
        }
        if (StartAccelerat) {
            DistanceX = curDistanceX;
            DistanceY = curDistanceY;
            if (D < distance) {
                solider.setVELOCITY(basicSpeed * 45);// tang 8 lan van toc co ban
            } else {// tang toc het quang duong, ket thuc hieu luc
                stopCount += 0.8;
                solider.setVELOCITY(solider.getVELOCITY() - basicSpeed * stopCount * stopCount);// ket thuc hieu ung
                if (solider.getVELOCITY() <= basicSpeed) {
                    solider.setVELOCITY(basicSpeed);
                    this.isvalid = false;
                }
            }
        }
    }

    public void draw(Canvas canvas) {
        if (isvalid) {
            if (!StartAccelerat) {
                canvas.drawBitmap(rulerBitmap, X, Y, null);
            }
        }
    }

    public boolean isvalid() {
        return isvalid;
    }
}
