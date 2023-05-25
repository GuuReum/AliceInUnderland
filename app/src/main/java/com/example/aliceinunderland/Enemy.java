package com.example.aliceinunderland;

import android.content.Context;
import android.view.View;

public class Enemy {
    private boolean isAlive = true; // 적의 생존 여부
    private int[] location = new int[2]; // 적의 위치

    public Enemy(View view, int l){
        setLocation(l);
        view.setX(location[0]);
        view.setY(location[1]);
        view.getLocationOnScreen(location);
    }

    //적이 파괴되었는지 확인
    public boolean isDead(View v, float curX, float curY) {
        if (isAlive) {
            //if botbot을 터치했다면
            if (isTouchInside(v, curX, curY)) {
                isAlive = false;
                return true;
            }
        }
        //아니라면
        return false;
    }

    //플레이어와 겹치면 게임 오버
    public boolean killPlayer(View playerView, View enemyView){
        enemyView.getLocationOnScreen(location);  
        final int realEnemyRight = location[0] + enemyView.getWidth();
        
        int[] playerLocation = new int[2];
        playerView.getLocationOnScreen(playerLocation);
        final int realPlayerRight = playerLocation[0] + playerView.getWidth();

        if((playerLocation[0] <= realEnemyRight && realEnemyRight <= realPlayerRight)
        || (playerLocation[0] <= location[0] && location[0] <= realPlayerRight))
            return true;

        return false;
    }

    //터치했는지 확인
    public boolean isTouchInside(View v, float x, float y) {
        v.getLocationOnScreen(location);

        final int realRight = location[0] + v.getWidth();
        final int realTop = location[1] + v.getHeight();
        boolean result = ((x >= location[0] && x <= realRight) && (y >= location[1] && y <= realTop));

        return result;
    }

    public void setLocation(int l){
        location[0] = l;
        location[1] = 475;
    }
}
