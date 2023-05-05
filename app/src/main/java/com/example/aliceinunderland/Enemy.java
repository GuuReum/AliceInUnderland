package com.example.aliceinunderland;

import android.content.Context;
import android.view.View;

public class Enemy {
    private boolean isAlive = true; // 적의 생존 여부
    private Context mainPlayingContext;

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

    //터치했는지 확인
    public boolean isTouchInside(View v, float x, float y) {
        int[] location = new int[2];
        v.getLocationOnScreen(location);

        final int realRight = location[0] + v.getWidth();
        final int realBottom = location[1] + v.getHeight();
        boolean result = ((x >= location[0]) && x <= realRight) && ((y >= location[1]) && (y <= realBottom));

        return result;
    }
}
