package com.example.aliceinunderland;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;


public class EnemyWave {
    public EnemyWave(Context mainPlayingContext) {
        this.mainPlayingContext = mainPlayingContext;
    }
    private final Context mainPlayingContext;

    private int EnemyNum = 2;  //이번 웨이브 적 스폰 수
    private int location;  //스폰되는 적의 위치

    private ArrayList<Integer> locationlist = new ArrayList<>(); //적 위치 중복 방지용 기록

    public int getEnemyNum() {
        return EnemyNum;
    }

    public void randEnemyNum() { //다음 웨이브 때 소환되는 적 수 정하기 (랜덤)
        Random random = new Random();
        EnemyNum = (int) random.nextInt(2) + 1;
    }

    public int getEnemyLocation() {
        Random random = new Random();
        location = (int) random.nextInt(500) + 50;
        for (int l : locationlist) {
            if (location < l + 50 || location > l - 50) {
                location = (int) random.nextInt(500) + 50;
            }
        }
        locationlist.add(location);
        return location;
    }

    public void removelocation(int i) {
        locationlist.remove(i);
    } //죽은 적 위치 삭제

}
