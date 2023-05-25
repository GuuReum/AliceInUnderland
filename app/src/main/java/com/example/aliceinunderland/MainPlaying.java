package com.example.aliceinunderland;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;


//게임 중 화면
public class MainPlaying extends AppCompatActivity {
    public TextView countdownText;

    private PlayTimer playTimer = new PlayTimer(900000, 1000, this);

    private ArrayList<Enemy> enemyBot = new ArrayList<>();
    private ArrayList<ImageView> enemyBotImageView = new ArrayList<>();
    private EnemyWave Wave = new EnemyWave(this);

    private Player player;
    private ImageView playerImageView;

    private ImageView leftBtn;
    private ImageView RightBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing_main);

        //주인공 이미지뷰, class 설정
        playerImageView = (ImageView)findViewById(R.id.GameProta);
        player = new Player(this, playerImageView);

        //타이머 클래스 생성 및 타이머뷰 설정
        countdownText = findViewById(R.id.TimeText);
        playTimer.start();

        leftBtn = (ImageView)findViewById(R.id.MoveLeftBtn);
        leftBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                playerImageView.setX(playerImageView.getX() - 5);
                return true;
            }
        });

        RightBtn = (ImageView)findViewById(R.id.MoveRightBtn);
        RightBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                playerImageView.setX(playerImageView.getX() + 5);
                return true;
            }
        });

        //화면 터치 시 발사 이벤트
        View shootview = findViewById(R.id.ShootTouchView);
        shootview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float curX = event.getX();  //눌린 곳의 X좌표
                float curY = event.getY();  //눌린 곳의 Y좌표

                if (player.isShootAble()) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (player.getLoadedBullet() > 0) {
                            for (int i = 0; i < enemyBot.size(); i++) {
                                //총을 쏴서 적이 죽는다면 적 이미지뷰의 이미지 제거, 배열에서 제거
                                if (enemyBot.get(i).isDead(enemyBotImageView.get(i), curX, curY)) {
                                    removeEnemy(enemyBotImageView.get(i).getId());
                                    enemyBotImageView.remove(i);
                                    enemyBot.remove(i);
                                    Wave.removelocation(i);

                                    break; //한마리만 사격
                                }
                            }
                        }
                        player.shootBullet(curX, curY);
                        loadBulletImage();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    //타이머 종료시 액티비티 종료 후 End로
    public void EndPlaying() {
        Intent intent = new Intent(this,MainEnd.class);
        startActivity(intent);
        finish();
    }

    //재장전 버튼 추가
    public void ClickReloadButton(View v) {
        if (player.isReloadAble()) {
            setTempText("Reloading Magazine...");
            player.doReload();
        }
    }

    //bullet 이미지 불러오기, remainbullet 수 새로고침
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
                bullet[0].setImageResource(R.drawable.emptymag);
                bullet[1].setImageResource(R.drawable.emptymag);
                bullet[2].setImageResource(R.drawable.emptymag);
                bullet[3].setImageResource(R.drawable.emptymag);
                bullet[4].setImageResource(R.drawable.emptymag);
                break;
            case 5:
                bullet[4].setImageResource(R.drawable.mag);
                remaining.setText("" + player.getRemainBullet());
                break;
            //player have bullet -> invisible one bullet & remove one bullet
            default:
                bullet[i - 1].setImageResource(R.drawable.mag);
                bullet[i].setImageResource(R.drawable.emptymag);
                remaining.setText("" + player.getRemainBullet());
                break;
        }

    }

    public void setTempText(String s) {
        TextView tempTextPrint = (TextView) findViewById(R.id.test);
        tempTextPrint.setText(s);
    }

    private void spawnEnemy() {  //적 생성 및 이미지뷰 동적생성 함수
        //적 이미지뷰와 class 설정
        //TODO:그냥 Enemy sprite는 bitmap으로 바꾸는것도 괜찮을지도? (강의노트 6) - ok, 김재휘
        //enemyBotImageView = (ImageView) findViewById(R.id.enemyBot);

        //이미지뷰 동적 생성
        ConstraintLayout layout = findViewById(R.id.te);

        int num = enemyBot.size();

        ImageView imageview = new ImageView(this); //빈 이미지뷰 생성


        imageview.setImageResource(R.drawable.enemy300);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageview.setLayoutParams(param);


        enemyBotImageView.add(imageview);
        enemyBot.add(new Enemy(enemyBotImageView.get(num), Wave.getEnemyLocation()));
        layout.addView(enemyBotImageView.get(num),2);
        enemyBotImageView.get(num).setId(80500 + num); //id 부여 (이미지뷰 삭제할 때 필요)
    }

    private void removeEnemy(int id) { //적 이미지뷰 삭제 함수
        ConstraintLayout layout = findViewById(R.id.te);
        ImageView imageview = findViewById(id);
        layout.removeView(imageview);
    }

    public void StartWave() {
        for (int i = 0; i < Wave.getEnemyNum(); i++) {spawnEnemy();}
        Wave.randEnemyNum();
    }

    public void tempClickStartWaveButton(View v) {
        for (int i = 0; i < Wave.getEnemyNum(); i++) {spawnEnemy();}
        Wave.randEnemyNum();
    }


    //TODO: 뒤로가기 누를시 선택창 뜨게. custom dialog로 만들면 될 듯? (강의노트 7)
    @Override
    public void onBackPressed() {   // 뒤로가기 누르면 다이얼로그 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("메인으로 나가시겠습니까?"); // 다이얼로그 제목
        builder.setNegativeButton("게임 계속하기", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("메인으로", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MainPlaying.super.onBackPressed();
            }
        });

        builder.show(); // 다이얼로그 보이기
    }

}