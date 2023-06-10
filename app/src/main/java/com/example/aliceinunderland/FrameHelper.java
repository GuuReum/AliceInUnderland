package com.example.aliceinunderland;

import android.os.AsyncTask;

import java.util.ArrayList;

public class FrameHelper extends AsyncTask<Integer, Integer, Integer> {

    private GameView mGameView;
    private Player player;
    private ArrayList<Enemy> enemy = new ArrayList<>();
    private Integer PmX, PmY; // player의 현재 위치

    //이동 방향 결정
    private boolean left = false;
    private boolean right = false;

    public FrameHelper(GameView gameView, Player mPlayer) {
        mGameView = gameView;
        player = mPlayer;
    }

    //좌우이동 기능
    protected Integer doInBackground(Integer... integers) {
        PmX = 900; //integers[0];
        PmY = integers[1];

        while (true) {
            if (!mPaused && player.isAlive()) {
                if (left) {
                    if (PmX >= 0)
                        PmX -= 15;
                }
                if (right) {
                    if (PmX <= mGameView.getWidth() - player.getSize())
                        PmX += 15;
                }

                for (Enemy e : enemy) {
                    if (e.getX() > player.getX()) {
                        mGameView.setEnemyLeftImage(e);
                        e.setX(e.getX() - 2);
                    }
                    if (e.getX() < player.getX()) {
                        mGameView.setEnemyRightImage(e);
                        e.setX(e.getX() + 2);
                    }
                }

                publishProgress(PmX, PmY);
            }
            try {
                Thread.sleep(16); // 1프레임 = 16밀리초
            } catch (Exception ex) {
            }
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        player.setX(values[0]);
        mGameView.checkPlayerDead();
        mGameView.invalidate();
        mGameView.checkPlayerGetBullet();
    }

    public void setLeftAnimation() {
        left = (!left);
    }

    public void setRightAnimation() {
        right = (!right);
    }

    public void addEnemy(Enemy e) {
        enemy.add(e);
    }

    public void removeEnemy(Enemy e) {
        enemy.remove(e);
    }

    private boolean mPaused = false;
    public void pauseAnimator() {
        mPaused = (!mPaused);
    }
}