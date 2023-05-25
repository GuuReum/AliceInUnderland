package com.example.aliceinunderland;

import android.os.AsyncTask;
import android.util.Log;

public class APlayerMoveHelper extends AsyncTask<Integer, Integer, Integer> {

    private AGameView mGameView;
    private Integer mX, mY; // player의 현재 위치

    //이동 방향 결정
    private boolean left = false;
    private boolean right = false;

    public APlayerMoveHelper(AGameView gameView) {
        mGameView = gameView;
    }

    //좌우이동 기능
    protected Integer doInBackground(Integer... integers) {
        mX = integers[0];
        mY = integers[1];

        Log.v("playerPos", mGameView.player.getX() + ", " + mGameView.player.getY() + " / " + mX + ", " + mY);

        while (true) {
            if (left)
                mX -= 15;
            if (right)
                mX += 15;
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

        mGameView.player.setX(values[0]);
        mGameView.invalidate();
    }

    public void setLeftAnimation() {
        left = (!left);
    }

    public void setRightAnimation() {
        right = (!right);
    }
}
