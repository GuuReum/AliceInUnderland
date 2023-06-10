package com.example.aliceinunderland;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainPlaying extends AppCompatActivity {
    private GameView gameView;
    private Player player = new Player();

    public Player getPlayer() {
        return player;
    }

    public FrameHelper getFrameHelper() {
        return frameHelper;
    }

    private FrameHelper frameHelper;
    private Timer timer;
    private TextView timerText;

    private TextView remainBulletText;

    public void setRemainBulletText(int i) {
        remainBulletText.setText(Integer.toString(i));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_playing);

        timerText = (TextView) findViewById(R.id.timer);
        timer = new Timer(600000, 1000, timerText, this);
        timer.start();

        remainBulletText = (TextView) findViewById(R.id.remainBullet);

        gameView = (GameView) findViewById(R.id.gameView);
        gameView.invalidate();

        frameHelper = new FrameHelper(gameView, player);
        frameHelper.execute(player.getX(), player.getY());
    }


    public void onClickLeftBtn(View view) {
        ImageView leftBtn = findViewById(R.id.moveLeftBtn);
        leftBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    player.setPlayerImage(getResources().getDrawable(R.drawable.protaleft));
                    frameHelper.setLeftAnimation();
                }
                if (event.getAction() == MotionEvent.ACTION_UP)
                    frameHelper.setLeftAnimation();
                return true;
            }
        });
    }

    public void onClickRightBtn(View view) {
        ImageView rightBtn = findViewById(R.id.moveRightBtn);
        rightBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    player.setPlayerImage(getResources().getDrawable(R.drawable.protaright));
                    frameHelper.setRightAnimation();
                }
                if (event.getAction() == MotionEvent.ACTION_UP)
                    frameHelper.setRightAnimation();
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
                                        remainBulletText.setText(Integer.toString(player.getRemainBullet()));
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

    public void timeForWave() {
        gameView.StartWave();
    }

    public void addEnemyInHelper(Enemy e) {
        frameHelper.addEnemy(e);
    }

    public void removeEnemyInHelper(Enemy e) {
        frameHelper.removeEnemy(e);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void gameClear() {
        Intent intent = new Intent(this, MainEnd.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {   // 뒤로가기 누르면 다이얼로그 생성
        clickPause(timerText);
    }

    private long remaintime = 0;  //일시정지 시 남은 시간 값

    public void clickPause(View v) {
        frameHelper.pauseAnimator();  //적 움직임 멈추기
        remaintime = timer.getRemainTime();  //타이머에서 남은 시간 불러오기
        timer.cancel();  //타이머 캔슬

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Paused"); // 다이얼로그 제목
        builder.setCancelable(false);   // 다이얼로그 화면 밖 터치 방지
        builder.setPositiveButton("계속하기", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                clickResume();
            }
        });

        builder.setNegativeButton("그만하기", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                gameClear();
            }
        });

        builder.show(); // 다이얼로그 보이기
    }

    public void clickResume() {
        frameHelper.pauseAnimator();  //적 움직임 시작
        timer = new Timer(remaintime,1000,timerText,this);  //타이머 재설정
        timer.start();  //타이머 시작
    }

}