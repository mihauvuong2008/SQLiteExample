package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.control;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.BitmapData;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;

/**
 * Created by NgocDong on 12/03/2017.
 */

public class Attack_Button {

    private final Configure configure;
    private final float focusX;
    private final float focusY;
    private final float radius;
    private Bitmap selectedImage;
    private final GameSurface gameSurface;
    private boolean attack = false;
    private boolean press;
    private boolean longAttack;
    Bitmap Button;
    Bitmap ButtonPressed;

    public Attack_Button(GameSurface gameSurface, BitmapData bitmapData, Configure configure) {
        this.gameSurface = gameSurface;
        this.configure = configure;
        Button = bitmapData.ResizeBitmap(bitmapData.getAttackbutton(),
                configure.getSizeConf().getAttackButtonSize(), configure.getSizeConf().getAttackButtonSize());
        ButtonPressed = bitmapData.ResizeBitmap(bitmapData.getAttackbutton2(),
                configure.getSizeConf().getAttackButtonSize(), configure.getSizeConf().getAttackButtonSize());
        this.selectedImage = Button;
        radius = configure.getSizeConf().getAttackButtonSize() / 2;
        focusX = configure.getPositionConf().getAttactButtonX() + radius;
        focusY = configure.getPositionConf().getAttactButtonY() + radius;
    }

    public void update() {
        if (press)
            selectedImage = Button;
        else
            selectedImage = ButtonPressed;
    }

    public void draw(Canvas canvas) {
        try {
            Paint myPaint = new Paint();
            myPaint.setColor(Color.rgb(255, 0, 0));
            myPaint.setStrokeWidth(1);
            myPaint.setStyle(Paint.Style.STROKE);
            canvas.drawBitmap(selectedImage, focusX - radius, focusY - radius, null);
            //canvas.drawCircle(focusX, focusY, getRadius(), myPaint);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAttack(boolean attack) {
        this.attack = attack;
    }

    public boolean isAttack() {
        return attack;
    }

    public float getRadius() {
        return radius;
    }

    public void onPress(MotionEvent e) {
        // tinh khoang cach den tam
        double d = Math.sqrt(Math.pow(e.getX() - focusX, 2) + Math.pow(e.getY() - focusY, 2));
        if (d <= radius) {
            press = true;
        }
    }

    public void onTapUp(MotionEvent e) {
        // tinh khoang cach den tam
        double d = Math.sqrt(Math.pow(e.getX() - focusX, 2) + Math.pow(e.getY() - focusY, 2));
        if (d <= radius) {
            press = false;
        }
    }

    public void setLongPress(MotionEvent e) {
        double d = Math.sqrt(Math.pow(e.getX() - focusX, 2) + Math.pow(e.getY() - focusY, 2));
        if (d <= radius) {
            attack = true;
            press = false;
        }
    }

    public void onSingleTapConfirmed(MotionEvent e) {
        double d = Math.sqrt(Math.pow(e.getX() - focusX, 2) + Math.pow(e.getY() - focusY, 2));
        if (d <= radius) {
            longAttack = true;
            attack = true;
        }
    }
}
