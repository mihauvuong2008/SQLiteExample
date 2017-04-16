package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.freeapp.ngocdong.sqliteexample.R;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;

public class GAME_START_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_option_);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Loại bỏ tiêu đề.
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(new GameSurface(this));

    }
}
