package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.option;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.freeapp.ngocdong.sqliteexample.R;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.BitmapData;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.SizeConf;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by NgocDong on 09/03/2017.
 */

public class Menu {
    private final Configure configure;
    private final BitmapData bitmapData;
    private ArrayList<option> option_list = new ArrayList<>();

    public Menu(BitmapData bitmapData, Configure configure) {
        this.bitmapData = bitmapData;
        this.configure = configure;
    }

    public ArrayList<option> getOption_list() {
        return option_list;
    }

    public void addMenu(option op) {
        option_list.add(op);
    }

    public void clearMenu() {
        option_list = new ArrayList<>();
    }

    public void Create(GameSurface gameSurface) {
        option hard = new option(gameSurface, bitmapData.getOptiom_easy(), (int) configure.getPositionConf().getMenu0PointX() - bitmapData.getOption_hard().getWidth() / 6,
                (int) configure.getPositionConf().getMenu0PointY());
        hard.setMODE(1);
        option medium = new option(gameSurface, bitmapData.getOptiom_medium(), (int) configure.getPositionConf().getMenu0PointX() - bitmapData.getOptiom_medium().getWidth() / 6,
                (int) configure.getPositionConf().getMenu0PointY() + configure.getSizeConf().getOptionSizeHeight() + configure.getSizeConf().getMenuPadding());
        medium.setMODE(2);

        option easy = new option(gameSurface, bitmapData.getOption_hard(), (int) configure.getPositionConf().getMenu0PointX() - bitmapData.getOptiom_easy().getWidth() / 6,
                (int) configure.getPositionConf().getMenu0PointY() + (configure.getSizeConf().getOptionSizeHeight() + configure.getSizeConf().getMenuPadding()) * 2);
        easy.setMODE(3);
        addMenu(hard);
        addMenu(medium);
        addMenu(easy);
    }

    public void update() {
        if (getOption_list().size() > 0)
            for (option o : getOption_list()) {
                o.update();
            }
    }

    public void draw(Canvas canvas) {
        if (getOption_list().size() > 0)
            for (option o : getOption_list()) {
                o.draw(canvas);
            }
    }

    public int isPressed(int x, int y) {
        int MODE = 0;
        Iterator<option> iterator = getOption_list().iterator();
        // Kiểm tra xem có click vào menu nào không.
        boolean flag = false;
        while (iterator.hasNext()) {
            option op = iterator.next();
            if (op.getX() < x && x < op.getX() + op.getWidth()
                    && op.getY() < y && y < op.getY() + op.getHeight()) {
                flag = true;
                op.setSelected(true);
                MODE = op.getMODE();
                clearMenu();
                addMenu(op);
                break;
            }
        }
        if (flag) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearMenu();
        }
        return MODE;
    }

    public boolean isMenuComplete() {
        if (getOption_list().size() == 0) return true;
        else return false;
    }
}
