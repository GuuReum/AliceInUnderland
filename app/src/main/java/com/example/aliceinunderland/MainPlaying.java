package com.example.aliceinunderland;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainPlaying extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.playing_main);

        countdownText = findViewById(R.id.TimeText);

        timer.start();
    }









    private long MaxPlayTime = 900000;
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









    public void ClickLeftButton(View v) {
        TextView moving = (TextView) findViewById(R.id.test);
        moving.setText("Move to Left");
    }
    public void ClickRightButton(View v) {
        TextView moving = (TextView) findViewById(R.id.test);
        moving.setText("Move to Right");
    }
    int remainBullets = 5;
    public void ClickReloadButton(View v) {
        TextView moving = (TextView) findViewById(R.id.test);
        moving.setText("Reload Magazine");

        remainBullets = 5;

        ImageView bullet1 = (ImageView)findViewById(R.id.bullet1);
        ImageView bullet2 = (ImageView)findViewById(R.id.bullet2);
        ImageView bullet3 = (ImageView)findViewById(R.id.bullet3);
        ImageView bullet4 = (ImageView)findViewById(R.id.bullet4);
        ImageView bullet5 = (ImageView)findViewById(R.id.bullet5);

        bullet1.setVisibility(View.VISIBLE);
        bullet2.setVisibility(View.VISIBLE);
        bullet3.setVisibility(View.VISIBLE);
        bullet4.setVisibility(View.VISIBLE);
        bullet5.setVisibility(View.VISIBLE);
    }
    public void ClickShootButton(View v) {

        TextView shooting = (TextView) findViewById(R.id.test);

        switch (remainBullets){
            case 0:
                shooting.setText("Not Enough Bullets");
                break;
            case 1:
                shooting.setText("Shot0");

                ImageView bullet1 = (ImageView)findViewById(R.id.bullet1);
                bullet1.setVisibility(View.INVISIBLE);
                remainBullets--;

                break;
            case 2:
                shooting.setText("Shot1");

                ImageView bullet2 = (ImageView)findViewById(R.id.bullet2);
                bullet2.setVisibility(View.INVISIBLE);
                remainBullets--;

                break;
            case 3:
                shooting.setText("Shot2");

                ImageView bullet3 = (ImageView)findViewById(R.id.bullet3);
                bullet3.setVisibility(View.INVISIBLE);
                remainBullets--;

                break;
            case 4:
                shooting.setText("Shot3");

                ImageView bullet4 = (ImageView)findViewById(R.id.bullet4);
                bullet4.setVisibility(View.INVISIBLE);
                remainBullets--;

                break;
            case 5:
                shooting.setText("Shot4");

                ImageView bullet5 = (ImageView)findViewById(R.id.bullet5);
                bullet5.setVisibility(View.INVISIBLE);
                remainBullets--;

                break;
        }

    }
}
