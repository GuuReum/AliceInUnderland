package com.example.aliceinunderland;

import java.util.ArrayList;
import java.util.Random;

public class EnemyWave {

    private int EnemyNum = 2;  //이번 웨이브 적 스폰 수
    private int location;  //스폰되는 적의 위치

    private ArrayList<Integer> locationlist = new ArrayList<>(); //적 위치 중복 방지용 기록

    public int getEnemyNum() {
        return EnemyNum;
    }

    public void randEnemyNum() { //다음 웨이브 때 소환되는 적 수 정하기 (랜덤)
        Random random = new Random();
        EnemyNum = (int) random.nextInt(2) + 5;  //최소2, 최대 2+5 = 7
    }

    public int getEnemyLocation(int playerx) {

        randEnemylocation(playerx);

        for (int l : locationlist) {
            if (location < l + 50 || location > l - 50) {  //주위 100 이내에 있으면 다시
                randEnemylocation(playerx);
            }
        }
        locationlist.add(location);
        return location;
    }

    public void randEnemylocation(int playerx) { //다음 웨이브 때 소환되는 적 수 정하기 (랜덤)
        Random random = new Random();
        location = (int) random.nextInt(2300)-300; //Enemy 랜덤하게 생성
        while (location < playerx + 700 && location > playerx - 700) {  //player 주변에는 안나오게
            location = (int) random.nextInt(2000);
        }
    }

    public void removelocation(int i) {
        locationlist.remove(i);
    } //죽은 적 위치 삭제
}