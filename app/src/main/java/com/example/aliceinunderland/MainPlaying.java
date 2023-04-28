package com.example.aliceinunderland;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

//게임 중 화면
public class MainPlaying extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.playing_main);

        countdownText = findViewById(R.id.TimeText);
        timer.start();
    }

    Player player = new Player();

    //왼쪽으로 이동 버튼(구현필요)
    public void ClickLeftButton(View v) {

    }

    //오른쪽으로 이동 버튼(구현필요)
    public void ClickRightButton(View v) {

    }

    //재장전 버튼
    public void ClickReloadButton(View v) {
        //player class의 재장전
        player.reloadBullet();

        //액티비티 화면 총알 설정
        ImageView[] bullet = new ImageView[5];
        //TODO:김재휘_ 총알의 이미지 표시->bullet 이미지 하나만 가지고 관리할 수는 없을까?
        bullet[0] = (ImageView) findViewById(R.id.bullet1);
        bullet[1] = (ImageView) findViewById(R.id.bullet2);
        bullet[2] = (ImageView) findViewById(R.id.bullet3);
        bullet[3] = (ImageView) findViewById(R.id.bullet4);
        bullet[4] = (ImageView) findViewById(R.id.bullet5);

        for (int i = 0; i < bullet.length; i++)
            bullet[i].setVisibility(View.VISIBLE);
    }

    //화면 터치 시 총알 발사 이벤트
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 1) {
            if (player.getBullet() == 0) {
                return false;
            }
            switch (player.getBullet()) {
                case 1:
                    ImageView bullet1 = (ImageView) findViewById(R.id.bullet1);
                    bullet1.setVisibility(View.INVISIBLE);

                    break;
                case 2:
                    ImageView bullet2 = (ImageView) findViewById(R.id.bullet2);
                    bullet2.setVisibility(View.INVISIBLE);

                    break;
                case 3:
                    ImageView bullet3 = (ImageView) findViewById(R.id.bullet3);
                    bullet3.setVisibility(View.INVISIBLE);

                    break;
                case 4:
                    ImageView bullet4 = (ImageView) findViewById(R.id.bullet4);
                    bullet4.setVisibility(View.INVISIBLE);

                    break;
                case 5:
                    ImageView bullet5 = (ImageView) findViewById(R.id.bullet5);
                    bullet5.setVisibility(View.INVISIBLE);

                    break;
                default:
                    break;
            }

            player.shootBullet();

            float x = event.getX();
            float y = event.getY();


        }

        return true;
    }

    //타이머 구현 line:28~57
    private long MaxPlayTime = 900000; //15분
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
            Intent intent = new Intent(MainPlaying.this, MainEnd.class);
            startActivity(intent);

            finish();
        }
    };

    //남은 플레이 시간 표시
    private void updateTimer() {

        int minute = (int) temp_time % 3600000 / 60000;
        int seconds = (int) temp_time % 3600000 % 60000 / 1000;

        String Left_time = "" + minute + ":" + seconds;

        countdownText.setText(Left_time);
    }
}
