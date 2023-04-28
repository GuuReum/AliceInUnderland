package com.example.aliceinunderland;


//플레이어 클래스..
public class Player {
<<<<<<< HEAD
    private int loadedBullet = 5; //장전된 총알

    private int remainBullet = 10;  //여분 총알


    //총알 한 발 사격
    public String shootBullet(float x, float y){

        switch (loadedBullet){
            case 0:
                return "Not enough ammo";
            default :
                loadedBullet--;
                return "("+x+","+y+")";
        }
    }

    public void makeMagEmpty() {
        remainBullet += loadedBullet;
        loadedBullet = 0;
=======
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
>>>>>>> jeahwi
    }

    //총알 재장전
    public void reloadBullet() {
<<<<<<< HEAD
        if (remainBullet>0) {
            loadedBullet++;
            remainBullet--;
        }
    }

    //return 총알의 개수
    public int getLoadedBullet(){
        return loadedBullet;
    }
    public int getRemainBullet() {return remainBullet;}
=======
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
>>>>>>> jeahwi
}
