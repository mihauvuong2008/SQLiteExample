package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.control;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;

/**
 * Created by NgocDong on 13/03/2017.
 */

public class Skill_button {
    private final Bitmap image2;
    private final Bitmap image1;
    private final float pointX;
    private final float pointY;
    private Bitmap selectedImage;
    private final GameSurface gameSurface;
    private boolean attack = false;
    private boolean pressed;
    private float radius;

    public Skill_button(GameSurface gameSurface, Bitmap image1, Bitmap image2, int x, int y) {
        this.gameSurface = gameSurface;
        this.image1 = image1;
        this.image2 = image2;
        this.selectedImage = image1;
        radius = selectedImage.getWidth() / 2;
        pointX = x;
        pointY = y;
    }

    public void update() {
        if (pressed)
            selectedImage = image2;
        else
            selectedImage = image1;
    }

    public void draw(Canvas canvas) {
        try {
            Paint myPaint = new Paint();
            myPaint.setColor(Color.rgb(255, 0, 0));
            myPaint.setStrokeWidth(2);
            myPaint.setStyle(Paint.Style.STROKE);
            canvas.drawBitmap(selectedImage, pointX - radius, pointY - radius, null);
            // canvas.drawCircle(pointX, pointY, getRadius(), myPaint);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float getPointX() {
        return pointX;
    }

    public float getPointY() {
        return pointY;
    }

    public float getRadius() {
        return selectedImage.getWidth() / 2;
    }

    public void deSelect() {
        pressed = false;
    }

    public boolean onPress(MotionEvent e) {
        // tinh khoang cach den tam
        double d = Math.sqrt(Math.pow(e.getX() - pointX, 2) + Math.pow(e.getY() - pointY, 2));
        if (d <= radius) {
            pressed = true;
            return true;
        }
        return false;
    }

}
