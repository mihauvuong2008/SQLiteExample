package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.control;

import android.graphics.Canvas;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.Army;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.BitmapData;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;

/**
 * Created by NgocDong on 15/03/2017.
 */

public class GameControl {
    Attack_Button attack_button;
    List_Skill_button myListSkillbutton;
    DistanceBar distanceBar;
    GameSurface gameSurface;
    BitmapData bitmapData;
    Configure configure;
    GameTask gameTask;

    public GameControl(GameSurface gameSurface, BitmapData bitmapData, Configure configure) {
        this.gameSurface = gameSurface;
        this.bitmapData = bitmapData;
        this.configure = configure;

        attack_button = new Attack_Button(gameSurface, bitmapData, configure);
        myListSkillbutton = new List_Skill_button(gameSurface, bitmapData, configure);
        distanceBar = new DistanceBar(gameSurface, bitmapData, configure);
        gameTask = new GameTask(gameSurface, bitmapData, configure);
    }

    public void update(Army army_solider) {
        distanceBar.update();
        attack_button.update();
        myListSkillbutton.update();
        gameTask.update();
        // Cập nhật Sự kiện nhấn nút tấn công
        if (attack_button.isAttack()) {
            // Hiện thực kỹ năng tấn công
            army_solider.attack(myListSkillbutton.getSkill());
            // Hoàn tất tấn công
            attack_button.setAttack(false);
        }
    }

    public void draw(Canvas canvas) {
        attack_button.draw(canvas);
        myListSkillbutton.draw(canvas);
        distanceBar.draw(canvas);
        gameTask.draw(canvas);
    }

    public Attack_Button getAttack_button() {
        return attack_button;
    }

    public List_Skill_button getMyListSkillbutton() {
        return myListSkillbutton;
    }

    public DistanceBar getDistanceBar() {
        return distanceBar;
    }

    public GameTask getGameTask() {
        return gameTask;
    }
}
