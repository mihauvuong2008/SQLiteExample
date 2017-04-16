package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.freeapp.ngocdong.sqliteexample.DBS.CONTROL.DBS_MANAGER.DBS_MANAGER;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.TOPIC;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.listener.MyListener;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.BitmapData;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.Effect;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.Army;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.GameScript.BOSS;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.CollisionHandler;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.UniverMap.GalaxyMap;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.control.GameControl;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.GameScript.GameScript;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.option.Menu;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by NgocDong on 08/03/2017.
 */

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {
    final private MyListener myListener;
    private DBS_MANAGER dbs_manager;
    private GameThread gameThread;
    final private Random Ran;
    private Menu menu;
    private int MODE = 0;
    private GameControl gameControl;
    private GameScript gameScript;
    private GalaxyMap galaxyMap;
    private Effect effect;
    private Army army_solider;
    private CollisionHandler collisionHandler;
    private BitmapData bitmapData;
    private Configure configure;

    public GameSurface(Context context) {
        super(context);
        // Đảm bảo Game Surface có thể focus để điều khiển các sự kiện.
        this.setFocusable(true);
        this.getHolder().addCallback(this);
        // Khởi tạo thông số
        Ran = new Random();
        // sét bộ lắng nghe
        myListener = new MyListener(this);
    }

    // xử lý sự kiện chạm vào solider
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        myListener.setTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            MODE = menu.isPressed(x, y);
            army_solider.setPressed(x, y);
            if (menu.isMenuComplete()) {
                if (gameScript == null) {
                    LoadDataAndStartGameScript(MODE);
                }
            }
            return true;
        }
        return false;
    }


    private void LoadDataAndStartGameScript(int mode) {
        // TO DO LOAD GAME
        dbs_manager = new DBS_MANAGER(this.getContext());
        army_solider.setSolider();
        TOPIC t = (dbs_manager.getDbs_topic_manager().ALL()).get(0);
        gameScript = new GameScript(this, Ran, bitmapData, configure, 5, 100, t, mode);
        collisionHandler.setGameScript(gameScript);
        gameControl.getGameTask().setNewTaskList(gameScript.getcurrentRoom().getMatch().getBoss());
        dbs_manager.close();
    }

    public void update() {
        // TO DO CẬP NHẬT TRẠNG THÁI
        try {
            menu.update();
            effect.update();
            updateGameScript();
            galaxyMap.update();
            army_solider.update(/*icon giữa chính giữa solider: */gameControl.getMyListSkillbutton().getSkill(), effect);
            gameControl.update(army_solider);// Ra hiệu cho solider tấn công
            collisionHandler.updatePoint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateGameScript() {
        if (gameScript != null) {
            gameScript.update(effect);
            checkStageComplete_orFailGame();
        }
    }

    private void checkStageComplete_orFailGame() {
        if (gameScript.isFailGame()) {
            // TO DO FAIL GAME
            gameScript.getcurrentRoom().getMatch().setBoss(new ArrayList<BOSS>());
            gameControl.getGameTask().setNewTaskList(gameScript.getcurrentRoom().getMatch().getBoss());
        } else if (gameScript.getcurrentRoom().getMatch().isPassed()) {
//        } else if (gameScript.getcurrentRoom().getMatch().isPassed()) {
            // Game ko Fail va hoan thanh room hiện tại
            // TODO CODE GAME - GAME COMPLETE STAGE
            // Mo Map, Dừng Game để chuyển qua màn mới

            galaxyMap.goMap(army_solider.getSolider());
            if (galaxyMap.outMap() > 0) {
                gameScript.setRoomID(galaxyMap.outMap());
                gameControl.getGameTask().setNewTaskList(gameScript.getcurrentRoom().getMatch().getBoss());
            }

            //===============================
//            new Thread() {
//                public void run() {
//                    while (effect.getExplosions_list().size() > 0) {
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    gameScript.setRoomID(gameScript.getRoomID() + 1);
//                    gameControl.getGameTask().setNewTaskList(gameScript.getcurrentRoom().getMatch().getBoss());
//                }
//            }.start();
        }

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        try {
            menu.draw(canvas);
            drawGameScript(canvas);
            galaxyMap.draw(canvas);
            effect.draw(canvas);
            army_solider.draw(canvas);
            gameControl.draw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawGameScript(Canvas canvas) {
        if (gameScript != null)
            gameScript.draw(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        bitmapData = new BitmapData(this);
        configure = new Configure(this);
        menu = new Menu(bitmapData, configure);
        menu.Create(this);
        galaxyMap = new GalaxyMap(this, bitmapData, configure);
        gameControl = new GameControl(this, bitmapData, configure);
        effect = new Effect();
        army_solider = new Army(this, bitmapData, configure);
        collisionHandler = new CollisionHandler(army_solider, configure, this);
        myListener.setListenParamater(army_solider, gameControl.getAttack_button(), gameControl.getMyListSkillbutton());
        this.gameThread = new GameThread(this, holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                this.gameThread.setRunning(false);
                // Luồng cha, cần phải tạm dừng chờ GameThread kết thúc.
                this.gameThread.join();
                ((Activity) getContext()).finish();
                retry = false;
                dbs_manager.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
