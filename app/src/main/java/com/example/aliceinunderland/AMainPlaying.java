package com.example.aliceinunderland;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AMainPlaying extends AppCompatActivity {
    private AGameView gameView;
    private APlayer player = new APlayer();
    public APlayer getAPlayer(){
        return player;
    }
    private APlayerMoveHelper playerMoveHelper;
    private ATimer timer;
    private TextView timerText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_playing);

        timerText = (TextView) findViewById(R.id.timer);
        timer = new ATimer(600000, 1000, timerText, this);
        timer.start();

        gameView = (AGameView) findViewById(R.id.gameView);
        gameView.invalidate();

        playerMoveHelper = new APlayerMoveHelper(gameView, player);
        playerMoveHelper.execute(player.getX(), player.getY());
    }


    public void onClickLeftBtn(View view) {
        ImageView leftBtn = findViewById(R.id.moveLeftBtn);
        leftBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    playerMoveHelper.setLeftAnimation();
                if (event.getAction() == MotionEvent.ACTION_UP)
                    playerMoveHelper.setLeftAnimation();
                return true;
            }
        });
    }

    public void onClickRightBtn(View view) {
        ImageView rightBtn = findViewById(R.id.moveRightBtn);
        rightBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    playerMoveHelper.setRightAnimation();
                if (event.getAction() == MotionEvent.ACTION_UP)
                    playerMoveHelper.setRightAnimation();
                return true;
            }
        });
    }

    public void onClickReloadBtn(View view) {
        ImageView reloadBtn = findViewById(R.id.reloadBtn);
        reloadBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (player.isReloadAble()) {
                        player.prepareReload();
                        loadBulletImage();

                        reloadloop:
                        for (int i = 0; i < 6; i++) {

                            new Handler().postDelayed(new Runnable() {  // 0.3초마다에 1탄 장전
                                @Override
                                public void run() {

                                    if (player.insertBullet()) {
                                        loadBulletImage(); //이미지
                                    } else {
                                        player.setReloadAble(true);
                                        player.setShootAble(true);
                                    }
                                }
                            }, 300 * i);

                        }
                        loadBulletImage();
                    }
                }
                return true;
            }
        });
    }

    public void onTouchToShoot(View v) {
        View shootview = findViewById(R.id.gameView);
        shootview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float curX = event.getX();  //눌린 곳의 X좌표
                float curY = event.getY();  //눌린 곳의 Y좌표

                if (player.isShootAble()) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (player.getLoadedBullet() > 0) {
                            player.shootBullet();
                            loadBulletImage();
                        }
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void loadBulletImage() {

        ImageView[] bullet = new ImageView[5];
        bullet[0] = (ImageView) findViewById(R.id.bulletA1);
        bullet[1] = (ImageView) findViewById(R.id.bulletA2);
        bullet[2] = (ImageView) findViewById(R.id.bulletA3);
        bullet[3] = (ImageView) findViewById(R.id.bulletA4);
        bullet[4] = (ImageView) findViewById(R.id.bulletA5);

        int i = player.getLoadedBullet();

        switch (i) {
            case 0:
                //player have 0 bullet -> print need to reload
                bullet[0].setImageResource(R.drawable.emptymag);
                bullet[1].setImageResource(R.drawable.emptymag);
                bullet[2].setImageResource(R.drawable.emptymag);
                bullet[3].setImageResource(R.drawable.emptymag);
                bullet[4].setImageResource(R.drawable.emptymag);
                break;
            case 5:
                bullet[4].setImageResource(R.drawable.mag);
                break;
            //player have bullet -> invisible one bullet & remove one bullet
            default:
                bullet[i - 1].setImageResource(R.drawable.mag);
                bullet[i].setImageResource(R.drawable.emptymag);
                break;
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void gameClear(){
        Intent intent = new Intent(this, MainEnd.class);
        startActivity(intent);
        finish();
    }
}