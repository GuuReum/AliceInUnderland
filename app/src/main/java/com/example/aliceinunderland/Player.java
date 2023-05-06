package com.example.aliceinunderland;


import android.content.Context;
import android.os.Handler;

//플레이어 클래스..
public class Player {
    Player(Context c) {
        mainPlayingContext = c;
    }

    private int loadedBullet = 5; //장전된 총알
    private int remainBullet = 10;  //여분 총알
    private boolean shootAble = true; // 사격 가능 여부
    private Context mainPlayingContext;

        //총알 한 발 사격
    public void shootBullet(float shootVectorX, float shootVectorY) {
        //총알 개수가 0개라면 쓰레기 shootVector return
        if(loadedBullet == 0){
            ((MainPlaying) mainPlayingContext).setTempText("Not enough ammo");
        }else { //총알이 있다면
            loadedBullet--; //총알 개수 감소
            shootCoolDown();

            ((MainPlaying) mainPlayingContext).setTempText("(" + shootVectorX + "," + shootVectorY + ")");
        }
    }

    private void shootCoolDown() {
        shootAble = false;
        new Handler().postDelayed(new Runnable() {  // 0.7초뒤에 사격 가능
            @Override
            public void run() {
                shootAble = true;
                ((MainPlaying) mainPlayingContext).setTempText("Ready For Shoot!!");
            }
        }, 700);
    }

    public void makeMagEmpty() {
        remainBullet += loadedBullet;
        loadedBullet = 0;
    }

    //총알 재장전
    public boolean reloadBullet() {
        if ((remainBullet > 0) && (loadedBullet < 5)) {
            loadedBullet++;
            remainBullet--;
            return true;
        }
        return false;
    }

    //return 장전된 총알의 개수
    public int getLoadedBullet() {
        return loadedBullet;
    }

    //return 남은 총알의 개수
    public int getRemainBullet() {
        return remainBullet;
    }

    //return 사격 가능 여부
    public boolean isShootAble() {
        return shootAble;
    }

    //사격 가능한 상태로 전환
    public void setShootAble(){
        shootAble = true;
    }

    //사격 불가능한 상태로 전환
    public void setShootDisable(){
        shootAble = false;
    }
}