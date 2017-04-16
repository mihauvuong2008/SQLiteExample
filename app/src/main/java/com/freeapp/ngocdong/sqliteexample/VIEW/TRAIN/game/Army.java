package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.skill.Acceleration;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.skill.Pencil_Bullet;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.skill.blackHole;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.skill.BreakAll;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by NgocDong on 10/03/2017.
 */

public class Army {//quan doi
    private final BitmapData bitmapData;
    private final Configure configure;
    private GameSurface gameSurface;
    private ArrayList<Pencil_Bullet> pencil_bullet_list = new ArrayList<>();
    private Acceleration acceleration;// tang toc
    private blackHole blackhole;// hố đen
    private BreakAll breakAll;
    private Solider solider;

    public Army(GameSurface gameSurface, BitmapData bitmapData, Configure configure) {
        this.gameSurface = gameSurface;
        this.bitmapData = bitmapData;
        this.configure = configure;
    }

    public void setSolider() {
        this.solider = new Solider(gameSurface, configure, bitmapData,
                gameSurface.getWidth() / 2, gameSurface.getHeight() / 2);
        solider.setVELOCITY((float) (configure.getSizeConf().getBasicVelocity() * 0.95));
    }

    public void draw(Canvas canvas) {
        if (solider != null) solider.draw(canvas);
        for (Pencil_Bullet p : pencil_bullet_list) {
            p.draw(canvas);
        }
        if (acceleration != null) {
            acceleration.draw(canvas);
        }
        if (blackhole != null) blackhole.draw(canvas);
        if (breakAll != null) breakAll.draw(canvas);
    }

    public Solider getSolider() {
        return solider;
    }

    public void setPressed(int x, int y) {
        // Kiểm tra xem có click vào solider nào không.
        if (solider != null)
            if (solider.getX() < x && x < solider.getX() + solider.getWidth()
                    && solider.getY() < y && y < solider.getY() + solider.getHeight()) {
                solider.setFling(true);
            }
        return;
    }

    public void update(int skill, Effect effect) {
        if (solider != null) solider.update();
        for (Pencil_Bullet p : pencil_bullet_list) {
            p.update(effect);
        }
        Iterator<Pencil_Bullet> iterator = pencil_bullet_list.iterator();
        // Kiểm tra xem có ra khoi man hinh không.
        while (iterator.hasNext()) {
            Pencil_Bullet op = iterator.next();
            if (op.getX() < 0 || op.getX() > gameSurface.getWidth() || op.getY() > gameSurface.getHeight() || op.isDestroyed()) {
                iterator.remove();
            }
        }
        if (acceleration != null) {
            acceleration.update();
            if (!acceleration.isvalid()) {
                acceleration = null;
            }
        }
        if (blackhole != null) {
            blackhole.update(solider);
            if (blackhole.isFinish())
                blackhole = null;

        }
        if (breakAll != null) {
            breakAll.update(solider);
            if (breakAll.isFinish())
                breakAll = null;

        }
    }

    public ArrayList<Pencil_Bullet> getPencil_bullet_list() {
        return pencil_bullet_list;
    }

    public void attack(int skill) {
        if (solider != null) {
            if (skill == 1) {
                Pencil_Bullet pencil_bullet = new Pencil_Bullet(gameSurface, bitmapData.getPencil(),
                        solider.getX(), solider.getY(), solider.movingVectorX, solider.movingVectorY,
                        configure.getSizeConf().getPencilSize());
                pencil_bullet_list.add(pencil_bullet);
            } else if (skill == 2) {
                blackhole = new blackHole(gameSurface, bitmapData.ResizeBitmap(bitmapData.getBlackHole(),
                        configure.getSizeConf().getBlackHoleSize(), configure.getSizeConf().getBlackHoleSize()),
                        solider.getX(), solider.getY());
            } else if (skill == 3) {
                acceleration = new Acceleration(configure.getSizeConf().getAccelerationSize(), solider, bitmapData);// tang toc quang duong = 1/16 chieu rong nhat man hinh
            } else if (skill == 4) {
                breakAll = new BreakAll(gameSurface, bitmapData.getBreakSkill(), solider.getX(), solider.getY(),
                        configure.getSizeConf().getBreakAllDistanceSize()); // Dừng tất cả trong quãng duong = 1/5 chieu rong nhat man hinh
            }
        }
    }

    public blackHole getBlackhole() {
        return blackhole;
    }

    public BreakAll getBreakAll() {
        return breakAll;
    }
}
