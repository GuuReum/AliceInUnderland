package com.example.aliceinunderland;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;


public class EnemyWave {
    public EnemyWave(Context mainPlayingContext) {
        this.mainPlayingContext = mainPlayingContext;
    }
    private final Context mainPlayingContext;

    private int EnemyNum = 2;  //적 스폰 수
    private int location;  //적 위치

    private ArrayList<Integer> locationlist = new ArrayList<>(); //적 위치 중복 방지용

    public int getEnemyNum() {
        return EnemyNum;
    }

    public void randEnemyNum() {
        Random random = new Random();
        EnemyNum = (int) random.nextInt(3) + 2;
    }

    public int getEnemyLocation() {
        Random random = new Random();
        location = (int) random.nextInt(500) + 50;
        for (int l : locationlist) {
            if (location < l + 10 || location > l - 10) {
                location = (int) random.nextInt(500) + 50;
            }
        }
        locationlist.add(location);
        return location;
    }

    public void removelocation(int i) {
        locationlist.remove(i);
    }

}
