package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.GameScript;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.BitmapData;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.Effect;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.Explosion_objectBitmap;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by NgocDong on 15/03/2017.
 */

public class Room {
    private final BitmapData bitmapData;
    private final Configure configure;
    private final GameSurface gameSurface;
    private final Random Ran;
    private final ArrayList<DictionaryData> dictionary;
    private final Rect sourc;
    private final Rect dest;
    int DistanceLimit;
    int numOfBOSS;
    int numOfCAMBAT;
    private Match match;
    private boolean FailGame;

    public Room(GameSurface gameSurface, Random Ran, BitmapData bitmapData, Configure configure,
                int numOfBOSS, int numOfCAMBAT, ArrayList<DictionaryData> dictionary) {
        this.dictionary = dictionary;
        this.gameSurface = gameSurface;
        this.bitmapData = bitmapData;
        this.configure = configure;
        sourc = new Rect(0, 0, bitmapData.getBackground01().getWidth(), bitmapData.getBackground01().getHeight());
        dest = new Rect(0, 0, configure.getSizeConf().getBackgroundWidth(), configure.getSizeConf().getBackgroundHeight());
        this.Ran = Ran;
        match = setMatch();
    }

    public Match setMatch() {
        ArrayList<BOSS> boss = new ArrayList<>();
        ArrayList<CAMBAT> CAMB = new ArrayList<>();
        Match match = new Match();
        int[] idx_ = new int[5];
        int len = dictionary.size();
        if (len >= 5)
            for (int id = 0; id < 5; id++) {
                boolean finish = false;
                int idx = 0;
                while (!finish) {
                    finish = true;
                    idx = Ran.nextInt(len);
                    for (int i = 0; i < 5; i++) {
                        if (idx == idx_[i]) {// idx da co trong mang, lap lai lan nua
                            finish = false;
                            break;
                        }
                    }
                }
                idx_[id] = idx;
            }
        for (int id = 0; id < 5; id++) {
            create(boss, CAMB, id + 1, idx_[id]);
        }
        match.setBoss(boss);
        match.setCAMBATANT(CAMB);
        return match;
    }


    private void create(ArrayList<BOSS> BOSS, ArrayList<CAMBAT> CAMB, int id, int idx) {
        DictionaryData d = dictionary.get(idx);
        int r = Ran.nextInt(155), g = Ran.nextInt(255), b = 765 - (r + g);
        int color = Color.rgb(r, g, b);
        BOSS boss = new BOSS(d.getWord().getCONTENT(), color, configure);
        boss.setIdBOSS(id);
        BOSS.add(boss);
        r = Ran.nextInt(155);
        g = Ran.nextInt(255);
        b = 765 - (r + g);
        color = Color.rgb(r, g, b);
        // kich thuoc mặc định
        idx = Ran.nextInt(d.getMeaningArrayList().size());
        CAMBAT camb = new CAMBAT(gameSurface, d.getMeaningArrayList().get(idx).getMEANING(),
                color, bitmapData, configure, Ran.nextInt(configure.getSizeConf().getBackgroundWidth()),
                Ran.nextInt(configure.getSizeConf().getBackgroundHeight()));
        camb.setIdCAMB(id);
        camb.setSize(configure.getSizeConf().getCAMBSize());
        CAMB.add(camb);
    }

    public Match getMatch() {
        return match;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmapData.getBackground01(), sourc, dest, null);
        for (CAMBAT t : match.getCAMBATANT()) {
            t.draw(canvas);
        }

    }

    public void update(Effect effect) {
        int destroyidBoss = 0;
        if (!match.isPassed()) {// update khi chưa pass
            Iterator<CAMBAT> iterator = match.getCAMBATANT().iterator();
            while (iterator.hasNext()) {
                CAMBAT CAMBAT = iterator.next();
                if (CAMBAT.getHealthy() <= 0) {
                    Explosion_objectBitmap explosion = new Explosion_objectBitmap(gameSurface,
                            bitmapData.getExplose(), (int) CAMBAT.getX(), (int) CAMBAT.getY());
                    effect.addNew(explosion);
                    iterator.remove();
                    if (CAMBAT.getIdCAMB() != getCurrBOSS()) {
                        FailGame = true;
                    } else {
                        setKilled(CAMBAT.getIdCAMB());
                        match.setCurrBOSSid(CAMBAT.getIdCAMB());
                    }
                }
                CAMBAT.update();
                if (CAMBAT.getIdCAMB() == match.getCurrBOSSid()) {
                    destroyidBoss = match.getCurrBOSSid();
                }
            }
            if (destroyidBoss >= 5) {
                match.setPassed(true);
            }
        }
    }

    int getCurrBOSS() {
        int rs = 10;
        for (BOSS boss : match.getBoss()) {
            if (boss.getIdBOSS() < rs && boss.getKilled() == false) rs = boss.getIdBOSS();
        }
        return rs;
    }

    public void setKilled(int killed) {
        for (BOSS boss : match.getBoss()) {
            if (boss.getIdBOSS() == killed)
                boss.setKilled(true);
        }
    }


    public boolean isFailGame() {
        return FailGame;
    }
}
