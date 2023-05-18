package com.example.aliceinunderland;

import android.content.Context;


public class EnemyWave {
    public EnemyWave(Context mainPlayingContext) {
        this.mainPlayingContext = mainPlayingContext;
    }
    private final Context mainPlayingContext;

    private int EnemyNum = 2;  //적 스폰 수
    private int location;  //적 위치




    public int getEnemyNum() {
        return EnemyNum;
    }

    public int getEnemyLocation() {
        location = 300;
        return location;
    }


}
