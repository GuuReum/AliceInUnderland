package com.example.aliceinunderland;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
<<<<<<< HEAD
import android.os.Handler;
=======
>>>>>>> jeahwi
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


//게임 중 화면
public class MainPlaying extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.playing_main);

        countdownText = findViewById(R.id.TimeText);
        timer.start();

        //temp_text view
        TextView moving = (TextView) findViewById(R.id.test);

        //화면 터치 시 발사 이벤트
        View shootview =findViewById(R.id.ShootTouchView);
        shootview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float curX = event.getX();  //눌린 곳의 X좌표
                float curY = event.getY();  //눌린 곳의 Y좌표

                String posOftouch = new String();
                if(shootenable) {
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            loadBulletImage();
                            posOftouch = player.shootBullet(curX, curY);
                            moving.setText(posOftouch);
                            shootCooldown();
                            break;
                    }
                }

                return true;
            }
        });
    }

<<<<<<< HEAD
    //타이머 구현
=======
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
>>>>>>> jeahwi
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
<<<<<<< HEAD
    //타임 구현 끝

    //사격 쿨타임 구현
    boolean shootenable = true;
    private void shootCooldown() {

        shootenable = false;

        new Handler().postDelayed(new Runnable() {  // 0.7초뒤에 사격 가능
            @Override
            public void run() {
                shootenable = true;
                TextView moving = (TextView) findViewById(R.id.test);
                moving.setText("Ready for shoot");
            }
        },700);
    }


    Player player = new Player();



    //왼쪽으로 이동 버튼(구현필요)
    public void ClickLeftButton(View v) {
        //temp_text view
        TextView moving = (TextView) findViewById(R.id.test);
        moving.setText("Move to Left");
    }
    
    //오른쪽으로 이동 버튼(구현필요)
    public void ClickRightButton(View v) {
        //temp_text view
        TextView moving = (TextView) findViewById(R.id.test);
        moving.setText("Move to Right");
    }
    
    //재장전 버튼 추가
    public void ClickReloadButton(View v) {
        //temp_text view
        TextView moving = (TextView) findViewById(R.id.test);
        TextView remaining = (TextView) findViewById(R.id.remainbullet);

        ImageView[] bullet = new ImageView[5];
        //TODO:김재휘_ 총알의 이미지 표시->bullet 이미지 하나만 가지고 관리할 수는 없을까?
        bullet[0] = (ImageView)findViewById(R.id.bullet1);
        bullet[1] = (ImageView)findViewById(R.id.bullet2);
        bullet[2] = (ImageView)findViewById(R.id.bullet3);
        bullet[3] = (ImageView)findViewById(R.id.bullet4);
        bullet[4] = (ImageView)findViewById(R.id.bullet5);

        moving.setText("Reloading Magazine...");

        //재장전 전 준비
        player.makeMagEmpty();

        for (int i = 0; i < bullet.length; i++) {
            bullet[i].setImageResource(R.drawable.temp_emptymag);
        }

        //사격 금지
        shootenable = false;

        reloadloop:
        for (int i = 0; i < bullet.length; i++) {

            int j = i;


            new Handler().postDelayed(new Runnable() {  // 0.3초마다에 1탄 장전
                @Override
                public void run() {

                    if (player.getRemainBullet()>0) {
                        bullet[j].setImageResource(R.drawable.temp_mag); //이미지
                        player.reloadBullet();
                        remaining.setText(""+player.getRemainBullet());
                    } else {
                        moving.setText("Reload complete");
                        shootenable = true;
                        return;
                    }

                    if (j==4) {
                        moving.setText("Reload complete");
                        shootenable = true;
                    }
                }
            },300*i);

        }

    }


    //사격 후 이미지 불러오기
    public void loadBulletImage() {

        ImageView[] bullet = new ImageView[5];
        //TODO:김재휘_ 총알의 이미지 표시->bullet 이미지 하나만 가지고 관리할 수는 없을까?
        bullet[0] = (ImageView)findViewById(R.id.bullet1);
        bullet[1] = (ImageView)findViewById(R.id.bullet2);
        bullet[2] = (ImageView)findViewById(R.id.bullet3);
        bullet[3] = (ImageView)findViewById(R.id.bullet4);
        bullet[4] = (ImageView)findViewById(R.id.bullet5);

        int i = player.getLoadedBullet();

        switch (i){
            case 0:
                //player have 0 bullet -> print need to reload
                break;
                //player have bullet -> invisible one bullet & remove one bullet
            default:

                bullet[i-1].setImageResource(R.drawable.temp_emptymag);

                break;
        }

    }
=======
>>>>>>> jeahwi
}
