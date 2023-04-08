package com.example.aliceinunderland;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainPlaying extends AppCompatActivity {

    private long MaxPlayTime = 5000;//900000;

    private long temp_time;

    private TextView countdownText;

    CountDownTimer timer = new CountDownTimer(MaxPlayTime, 1000) {

        @Override
        public void onTick(long l) {
            temp_time = l;
            updateTimer();
        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(MainPlaying.this,MainEnd.class);
            startActivity(intent);

            finish();
        }
    };

    private void updateTimer() {

        int minute = (int) temp_time % 3600000 / 60000;
        int seconds = (int) temp_time % 3600000 % 60000 / 1000;

        String Left_time = "" + minute + ":" + seconds;

        countdownText.setText(Left_time);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.playing_main);

        countdownText = findViewById(R.id.TimeText);

        timer.start();
    }
}
