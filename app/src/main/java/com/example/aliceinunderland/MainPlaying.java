package com.example.aliceinunderland;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


//게임 중 화면
public class MainPlaying extends AppCompatActivity {
    private Player player = new Player(this);
    Enemy enemyBot;

    public TextView countdownText;
    public TextView tempTextPrint;

    private PlayTimer playTimer = new PlayTimer(900000, 1000, this);
    private ImageView enemyBotImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing_main);

        enemyBotImageView = findViewById(R.id.enemyBot);
        enemyBot = new Enemy(this, enemyBotImageView);

        countdownText = findViewById(R.id.TimeText);
        playTimer.start();

        tempTextPrint = (TextView) findViewById(R.id.test);

        //화면 터치 시 발사 이벤트
        View shootview = findViewById(R.id.ShootTouchView);
        shootview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float curX = event.getX();  //눌린 곳의 X좌표
                float curY = event.getY();  //눌린 곳의 Y좌표

                if (player.isShootAble()) {
                    if(event.getAction() == MotionEvent.ACTION_UP){
                        loadBulletImage();

                        if(enemyBot.isDead(player.shootBullet(curX, curY))){
                            enemyBotImageView.setImageResource(0);
                        };

                    }
                }
                return true;
            }
        });
    }


























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
        bullet[0] = (ImageView) findViewById(R.id.bullet1);
        bullet[1] = (ImageView) findViewById(R.id.bullet2);
        bullet[2] = (ImageView) findViewById(R.id.bullet3);
        bullet[3] = (ImageView) findViewById(R.id.bullet4);
        bullet[4] = (ImageView) findViewById(R.id.bullet5);

        moving.setText("Reloading Magazine...");

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
                        bullet[j].setImageResource(R.drawable.temp_mag); //이미지
                        player.reloadBullet();
                        remaining.setText("" + player.getRemainBullet());
                    } else {
                        moving.setText("Reload complete");
                        player.setShootAble();
                        return;
                    }

                    if (j == 4) {
                        moving.setText("Reload complete");
                        player.setShootAble();
                    }
                }
            }, 300 * i);

        }

    }

    //사격 후 이미지 불러오기
    public void loadBulletImage() {

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
                break;
            //player have bullet -> invisible one bullet & remove one bullet
            default:

                bullet[i - 1].setImageResource(R.drawable.temp_emptymag);

                break;
        }

    }
}