package com.example.aliceinunderland;

import android.os.AsyncTask;

public class PlayerMoveHelper extends AsyncTask<Integer, Integer, Integer> {

    private GameView mGameView;
    private Player player;
    private Integer mX, mY; // player의 현재 위치

    //이동 방향 결정
    private boolean left = false;
    private boolean right = false;

    public PlayerMoveHelper(GameView gameView, Player mPlayer) {
        mGameView = gameView;
        player = mPlayer;
    }

    //좌우이동 기능
    protected Integer doInBackground(Integer... integers) {
        mX = 900; //integers[0];
        mY = integers[1];

        while (true) {
            if (left) {
                player.setDirection(true);
                mX -= 15;
            }
            if (right) {
                player.setDirection(false);
                mX += 15;
            }
            publishProgress(mX, mY);

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
}
