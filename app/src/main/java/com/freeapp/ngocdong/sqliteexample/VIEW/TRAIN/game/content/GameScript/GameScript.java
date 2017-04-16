package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.GameScript;

import android.graphics.Canvas;

import com.freeapp.ngocdong.sqliteexample.DBS.CONTROL.MyDatabaseHelper;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.MEANING;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.TOPIC;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.WORD;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.BitmapData;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.Effect;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by NgocDong on 08/03/2017.
 */

public class GameScript {
    private ArrayList<DictionaryData> dictionaryData;
    private ArrayList<Room> room;
    final private GameSurface gameSurface;
    private int roomID = 0;
    final private Random Ran;
    final private Configure configure;
    final private BitmapData bitmapData;
    private boolean FailGame = false;
    private int MODE = 0;
    int numOfRoom;
    int numOfCambat;

    public GameScript(GameSurface gameSurface, Random Ran, BitmapData bitmapData, Configure configure, int numOfCambat, int numOfRoom, TOPIC t, int mode) {
        this.gameSurface = gameSurface;
        this.Ran = Ran;
        this.configure = configure;
        this.bitmapData = bitmapData;
        this.numOfRoom = numOfRoom;
        this.numOfCambat = numOfCambat;
        loadData(t);
        loadRoom(t);
    }

    private final void loadData(TOPIC t) {
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(gameSurface.getContext());
        dictionaryData = new ArrayList<>();
        List<WORD> listword = myDatabaseHelper.getAllWORD(t);
        for (WORD w : listword) {
            List<MEANING> listmean = myDatabaseHelper.getAllMEANING(w);
            DictionaryData d = new DictionaryData();
            d.setWord(w);
            d.setMeaningArrayList(listmean);
            dictionaryData.add(d);
        }
    }

    private final void loadRoom(TOPIC t) {
        room = new ArrayList<>();
        for (int i = 0; i < numOfRoom; i++) {
            room.add(new Room(gameSurface, Ran, bitmapData, configure, 5, 5, dictionaryData));
        }
    }

    public void update(Effect effect) {
        room.get(roomID).update(effect);
        FailGame = room.get(roomID).isFailGame();
    }

    public void draw(Canvas canvas) {
        room.get(roomID).draw(canvas);
    }

    public boolean isFailGame() {
        return FailGame;
    }

    public Room getcurrentRoom() {
        return room.get(roomID);
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public void setMODE(int MODE) {
        this.MODE = MODE;
    }

    public int getMODE() {
        return MODE;
    }

}
