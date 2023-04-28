package com.example.aliceinunderland;

//플레이어 클래스..
public class Player {
    private int bullet = 5;
    private Vector playerVector = new Vector();

    public void setVector(int x1, int y1, int x2, int y2) {
        playerVector.beginX = x1;
        playerVector.beginY = y1;
        playerVector.endX = x2;
        playerVector.endY = y2;
    }

    //총알 한 발 사격
    public void shootBullet() {
        bullet--;
    }

    //총알 재장전
    public void reloadBullet() {
        bullet = 5;
    }

    //return 총알의 개수
    public int getBullet() {
        return bullet;
    }

    public boolean isDamaged(float x, float y) {
        if ((playerVector.beginX <= x) && (x <= playerVector.beginY)) {

        }
        return true;
    }
}
