package com.example.aliceinunderland;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainPlayingA extends AppCompatActivity {
    private GameViewA gameView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_playing);

        gameView = (GameViewA) findViewById(R.id.gameView);
    }

    public void onClickLeftBtn(){

    }

    public void onClickRightBtn(){

    }

    public void onClickReloadBtn(){

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }
}
