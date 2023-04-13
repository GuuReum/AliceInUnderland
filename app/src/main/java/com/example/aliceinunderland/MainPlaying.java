package com.example.aliceinunderland;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
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
            Intent intent = new Intent(MainPlaying.this,MainEnd.class);
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

    Player player = new Player();

    //왼쪽으로 이동 버튼(구현필요)
    public void ClickLeftButton(View v) {
        TextView moving = (TextView) findViewById(R.id.test);
        moving.setText("Move to Left");
    }
    
    //오른쪽으로 이동 버튼(구현필요)
    public void ClickRightButton(View v) {
        TextView moving = (TextView) findViewById(R.id.test);
        moving.setText("Move to Right");
    }
    
    //재장전 버튼 추가
    public void ClickReloadButton(View v) {
        TextView moving = (TextView) findViewById(R.id.test);
        moving.setText("Reload Magazine");

        //재장전
        player.reloadBullet();

        ImageView[] bullet = new ImageView[4];
        //TODO:김재휘_ 총알의 이미지 표시->bullet 이미지 하나만 가지고 관리할 수는 없을까?
        bullet[0] = (ImageView)findViewById(R.id.bullet1);
        bullet[1] = (ImageView)findViewById(R.id.bullet2);
        bullet[2] = (ImageView)findViewById(R.id.bullet3);
        bullet[3] = (ImageView)findViewById(R.id.bullet4);
        bullet[4] = (ImageView)findViewById(R.id.bullet5);

        for(int i = 0; i < bullet.length; i++)
            bullet[i].setVisibility(View.VISIBLE);
    }
    //shoot Button 구현
    public void ClickShootButton(View v) {

        TextView shooting = (TextView) findViewById(R.id.test);

        switch (player.getBullet()){
            case 0:
                //player have 0 bullet -> print need to reload
                shooting.setText("Not Enough Bullets");
                break;
                //player have bullet -> invisible one bullet & remove one bullet
            case 1:
                shooting.setText("Shot0");

                ImageView bullet1 = (ImageView)findViewById(R.id.bullet1);
                bullet1.setVisibility(View.INVISIBLE);
                player.shootBullet();

                break;
            case 2:
                shooting.setText("Shot1");

                ImageView bullet2 = (ImageView)findViewById(R.id.bullet2);
                bullet2.setVisibility(View.INVISIBLE);
                player.shootBullet();

                break;
            case 3:
                shooting.setText("Shot2");

                ImageView bullet3 = (ImageView)findViewById(R.id.bullet3);
                bullet3.setVisibility(View.INVISIBLE);
                player.shootBullet();

                break;
            case 4:
                shooting.setText("Shot3");

                ImageView bullet4 = (ImageView)findViewById(R.id.bullet4);
                bullet4.setVisibility(View.INVISIBLE);
                player.shootBullet();

                break;
            case 5:
                shooting.setText("Shot4");

                ImageView bullet5 = (ImageView)findViewById(R.id.bullet5);
                bullet5.setVisibility(View.INVISIBLE);
                player.shootBullet();

                break;
        }

    }
}
