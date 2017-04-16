package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by NgocDong on 09/03/2017.
 */

public class Effect {
    private ArrayList<Explosion_objectBitmap> explosions_list = new ArrayList<>();

    public ArrayList<Explosion_objectBitmap> getExplosions_list() {
        return explosions_list;
    }

    public void setExplosions_list(ArrayList<Explosion_objectBitmap> explosions_list) {
        this.explosions_list = explosions_list;
    }

    public int size() {
        return explosions_list.size();
    }

    public void remove(Explosion_objectBitmap explosion_object) {
        explosions_list.remove(explosion_object);
    }

    public void update() {
        if (this.getExplosions_list().size() > 0)
            for (int i = 0; i < this.getExplosions_list().size(); i++) {
                if (this.getExplosions_list().get(i).isFinish()) {
                    this.getExplosions_list().remove(this.getExplosions_list().get(i));
                } else {
                    this.getExplosions_list().get(i).update();
                }
            }

        Iterator<Explosion_objectBitmap> iterator = this.getExplosions_list().iterator();
        while (iterator.hasNext()) {
            Explosion_objectBitmap explosion = iterator.next();
            if (explosion.isFinish()) {
                // Nếu explosion đã hoàn thành, loại nó ra khỏi iterator & list.
                // (Loại bỏ phần tử hiện thời ra khỏi bộ lặp).
                iterator.remove();
                continue;
            }
        }
    }

    public void draw(Canvas canvas) {
        if (this.getExplosions_list().size() > 0)
            for (Explosion_objectBitmap e : this.getExplosions_list()) {
                e.draw(canvas);
            }
    }

    public void addNew(Explosion_objectBitmap explosion) {
        explosions_list.add(explosion);
    }
}
