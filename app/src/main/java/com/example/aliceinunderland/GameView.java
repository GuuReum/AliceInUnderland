package com.example.aliceinunderland;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class GameView extends View {
    private Player player;
    private ArrayList<Enemy> enemy = new ArrayList<>();
    private EnemyWave wave = new EnemyWave();
    private EntityDeadHelper entityDeadHelper = new EntityDeadHelper();
    private Bitmap backgroundImage;
    private Context mcontext;

    private int canvasHeight = 1;
    private int canvasWidth = 1;

    public GameView(Context context) {
        super(context);
        player = ((MainPlaying) context).getPlayer();
        mcontext = context;
        initSetting(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        player = ((MainPlaying) context).getPlayer();
        mcontext = context;
        initSetting(context);
    }

    //초기 설정
    private void initSetting(Context context) {
        Resources res = context.getResources();
        //player 이미지 설정
        player.setPlayerImage(res.getDrawable(R.drawable.protaleft));
        //background 이미지 설정
        backgroundImage = BitmapFactory.decodeResource(res, R.drawable.background);
    }

    boolean firstDraw = true;

    @Override
    protected void onDraw(Canvas canvas) {
        //처음에만 실행됨. player의 초기 좌표 설정
        if (firstDraw) {
            firstDraw = false;
            player.setX((int) getWidth() / 2);
            player.setY(getHeight());
        }

        canvasHeight = getHeight();
        canvasWidth = getWidth();

        Log.v("playerPos", player.getX() + ", " + player.getY() + "A" + canvasWidth);

        //backgroundImage의 크기와 canvas의 크기가 다를 경우 Resizing
        if (backgroundImage.getWidth() != canvasWidth || backgroundImage.getHeight() != canvasHeight)
            backgroundImage = backgroundImage.createScaledBitmap(backgroundImage, canvasWidth, canvasHeight, true);

        //Draw background
        canvas.drawBitmap(backgroundImage, 0, 0, null);

        if (player.isAlive()) {
            //set Bounds of player
            player.setBounds();
            //Draw player
            player.draw(canvas);
        }

        //적이 죽었다면 draw하지 않아야 함.
        //이 부분은 Enemy를 ArrayList로 관리해서, 반복문을 수행하게 하면 될 것 같음.
        //그래서 해당 enemy가 죽으면 그 부분은 관리하지 않도록..
        for (Enemy e : enemy) {
            if (e.getIsAlive()) {
                e.setBounds();
                e.draw(canvas);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        float curX = event.getX();  //눌린 곳의 X좌표
        float curY = event.getY();  //눌린 곳의 Y좌표

        if (player.isShootAble()) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (player.getLoadedBullet() > 0) {
                    checkEnemyDead((int) curX,(int) curY, wave);
                    player.shootBullet();
                    ((MainPlaying)mcontext).loadBulletImage();
                }
            }
            return true;
        }
        return false;
    }


    public void StartWave() {
        for (int i = 0; i < wave.getEnemyNum(); i++) {
            spawnEnemy(wave.getEnemyLocation(player.getX()));
        }
        wave.randEnemyNum();
    }

    public void spawnEnemy(int x) {
        Enemy e = new Enemy();
        //Enemy 이미지 설정
        e.setEnemyImage(mcontext.getDrawable(R.drawable.enemyleft));
        //Enemy 위치 설정
        e.setX(x);
        e.setY(getHeight());

        enemy.add(e);
        int i = enemy.size() - 1;
        ((MainPlaying)mcontext).addEnemyInHelper(enemy.get(i));
    }

    public void setEnemyLeftImage(Enemy e) {e.setEnemyImage(mcontext.getDrawable(R.drawable.enemyleft));}
    public void setEnemyRightImage(Enemy e) {e.setEnemyImage(mcontext.getDrawable(R.drawable.enemyright));}

    public void checkPlayerDead() {
        for (Enemy e : enemy) {
            if (e != null) {
                if (!entityDeadHelper.isDeadPlayer(e, player)) {
                    player.setAlive(false);
                    //굳이 player Alive가 필요할까? 어차피 여기서 player가 사망하면 게임도 끝나니까, 필요 없다고 생각함.
                    //여기서 player가 사망하면 game over로 넘어가도록 하자
                    break;
                } else {
                    player.setAlive(true);
                }
            }
        }
    }

    public void checkEnemyDead(int x, int y, EnemyWave wave) {
        for (int i = enemy.size() - 1; i >= 0; i--) {
            if (enemy.get(i) != null) {
                if (entityDeadHelper.isDeadEnemy(enemy.get(i), x, y)) {
                    enemy.remove(i);
                    wave.removelocation(i);

                    break; //한 명만 사격
                }
            }
        }
    }
}