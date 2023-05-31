package com.example.aliceinunderland;

import android.os.AsyncTask;

import java.util.ArrayList;

//TODO: mX, mY를 player class에 넣어주자.
//TODO: mX 없이 직접적으로 바꿔도 움직이기는 하는데?
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
            if (left) {
                PmX -= 15;
            }
            if (right) {
                PmX += 15;
            }

            for(Enemy e : enemy) {
                if (e.getX() > player.getX()){
                    mGameView.setEnemyLeftImage(e);
                    e.setX(e.getX()-1);
                }
                if (e.getX() < player.getX()) {
                    mGameView.setEnemyRightImage(e);
                    e.setX(e.getX() + 1);
                }
            }

            publishProgress(PmX, PmY);

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
    }

    public void setLeftAnimation() {
        left = (!left);
    }

    public void setRightAnimation() {
        right = (!right);
    }

    public void addEnemy(Enemy e) {enemy.add(e);}
}
