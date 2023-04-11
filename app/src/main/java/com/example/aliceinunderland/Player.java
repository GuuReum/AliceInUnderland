package com.example.aliceinunderland;

//플레이어 클래스..
public class Player {
    private int bullet = 5;

    //총알 한 발 사격
    public void shootBullet(){
        bullet--;
    }

    //총알 재장전
    public void reloadBullet(){
        bullet = 5;
    }

    //return 총알의 개수
    public int getBullet(){
        return bullet;
    }
}
