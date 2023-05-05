package com.example.aliceinunderland;

import android.content.Context;
import android.widget.ImageView;

public class Enemy {
    Enemy(Context c, ImageView iv){

    }
    private Vector enemyVector = new Vector(); // 적의 위치
    private boolean isAlive = true; // 적의 생존 여부

    public boolean isDead(Vector shootVector){
        if(isAlive) {
            //if botbot을 터치했다면
            //botbot imageview 삭제
            //2
            isAlive = false;
            return true;
        }
        //아니라면
        return false;
    }
}
