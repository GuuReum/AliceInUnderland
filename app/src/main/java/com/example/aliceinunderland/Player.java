package com.example.aliceinunderland;


//플레이어 클래스..
public class Player {
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
    }

    //총알 재장전
    public void reloadBullet() {
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
}