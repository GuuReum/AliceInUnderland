package com.example.aliceinunderland;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
    }

    //Game Start -> 게임 activity로 전환
    public void ClickStartButton(View v) {
        //Intent intent = new Intent(this,MainPlaying.class); //변경 전
        Intent intent = new Intent(this, MainPlaying.class); // 변경 후
        startActivity(intent);
    }
}