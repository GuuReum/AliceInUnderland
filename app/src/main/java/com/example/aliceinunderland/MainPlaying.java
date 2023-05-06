package com.example.aliceinunderland;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


//게임 중 화면
public class MainPlaying extends AppCompatActivity {
    private Player player = new Player(this);
    Enemy enemyBot;

    public TextView countdownText;

    private PlayTimer playTimer = new PlayTimer(900000, 1000, this);
    ImageView enemyBotImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing_main);

        //적 이미지뷰와 class를 만들어주고, 적의 좌표값과 사이즈 설정
        enemyBotImageView = (ImageView) findViewById(R.id.enemyBot);
        enemyBot = new Enemy();

        //타이머 클래스 생성 및 타이머뷰 설정
        countdownText = findViewById(R.id.TimeText);
        playTimer.start();

        //화면 터치 시 발사 이벤트
        View shootview = findViewById(R.id.ShootTouchView);
        shootview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float curX = event.getX();  //눌린 곳의 X좌표
                float curY = event.getY();  //눌린 곳의 Y좌표

                if (player.isShootAble()) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        player.shootBullet(curX, curY);
                        loadBulletImage();
                        //총을 쏴서 적이 죽는다면 적 이미지뷰의 이미지 제거
                        if (enemyBot.isDead(enemyBotImageView, curX, curY)) {
                            enemyBotImageView.setImageResource(0);
                        }
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void EndPlaying() {
        Intent intent = new Intent(this,MainEnd.class);
        startActivity(intent);
        finish();
    }























    //왼쪽으로 이동 버튼(구현필요)
    public void ClickLeftButton(View v) {
        //temp_text view
        setTempText("Move to Left");
    }

    //오른쪽으로 이동 버튼(구현필요)
    public void ClickRightButton(View v) {
        //temp_text view
        setTempText("Move to Right");
    }

    //재장전 버튼 추가
    public void ClickReloadButton(View v) {
        //temp_text view


        ImageView[] bullet = new ImageView[5];
        //TODO:김재휘_ 총알의 이미지 표시->bullet 이미지 하나만 가지고 관리할 수는 없을까?
        bullet[0] = (ImageView) findViewById(R.id.bullet1);
        bullet[1] = (ImageView) findViewById(R.id.bullet2);
        bullet[2] = (ImageView) findViewById(R.id.bullet3);
        bullet[3] = (ImageView) findViewById(R.id.bullet4);
        bullet[4] = (ImageView) findViewById(R.id.bullet5);

        setTempText("Reloading Magazine...");

        //재장전 전 준비
        player.makeMagEmpty();

        for (int i = 0; i < bullet.length; i++) {
            bullet[i].setImageResource(R.drawable.temp_emptymag);
        }

        //사격 금지
        player.setShootDisable();

        reloadloop:
        for (int i = 0; i < bullet.length; i++) {

            int j = i;


            new Handler().postDelayed(new Runnable() {  // 0.3초마다에 1탄 장전
                @Override
                public void run() {

                    if (player.getRemainBullet() > 0) {
                        player.reloadBullet();
                        loadBulletImage(); //이미지
                    } else {
                        setTempText("Reload complete");
                        player.setShootAble();
                        return;
                    }

                    if (j == 4) {
                        setTempText("Reload complete");
                        player.setShootAble();
                    }
                }
            }, 300 * i);

        }

    }

    //bullet 이미지 불러오기, remainbullet 새로고침
    public void loadBulletImage() {

        TextView remaining = (TextView) findViewById(R.id.remainbullet);

        ImageView[] bullet = new ImageView[5];
        bullet[0] = (ImageView) findViewById(R.id.bullet1);
        bullet[1] = (ImageView) findViewById(R.id.bullet2);
        bullet[2] = (ImageView) findViewById(R.id.bullet3);
        bullet[3] = (ImageView) findViewById(R.id.bullet4);
        bullet[4] = (ImageView) findViewById(R.id.bullet5);

        int i = player.getLoadedBullet();

        switch (i) {
            case 0:
                //player have 0 bullet -> print need to reload
                bullet[0].setImageResource(R.drawable.temp_emptymag);
                break;
            case 5:
                bullet[4].setImageResource(R.drawable.temp_mag);
                remaining.setText("" + player.getRemainBullet());
                break;
            //player have bullet -> invisible one bullet & remove one bullet
            default:
                bullet[i - 1].setImageResource(R.drawable.temp_mag);
                bullet[i].setImageResource(R.drawable.temp_emptymag);
                remaining.setText("" + player.getRemainBullet());
                break;
        }

    }

    public void setTempText(String s) {
        TextView tempTextPrint = (TextView) findViewById(R.id.test);
        tempTextPrint.setText(s);
    }
}