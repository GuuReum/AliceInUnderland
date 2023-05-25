package com.example.aliceinunderland;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.view.View;

//플레이어 클래스..
public class Player {
    public Player(Context c, View v) {
        mainPlayingContext = c;
        v.getLocationOnScreen(location);
    }

    private int loadedBullet = 5; //장전된 총알
    private int remainBullet = 100;  //여분 총알
    private boolean shootAble = true; // 사격 가능 여부
    private boolean reloadAble = true; // 재장전 가능 여부
    private Context mainPlayingContext;
    public int[] location = new int[2]; // 플레이어의 위치

    //총알 한 발 사격
    public void shootBullet(float shootVectorX, float shootVectorY) {
        //총알 개수가 0개라면 쓰레기 shootVector return
        if(loadedBullet == 0){
            ((MainPlaying) mainPlayingContext).setTempText("Not enough ammo");
        }else { //총알이 있다면
            loadedBullet--; //총알 개수 감소
            setShootDisable();

            new Handler().postDelayed(new Runnable() {  // 0.7초뒤에 사격 가능
            @Override
            public void run() {
                setShootAble();
                ((MainPlaying) mainPlayingContext).setTempText("Ready For Shoot!!");
            }
        }, 700);

            ((MainPlaying) mainPlayingContext).setTempText("(" + shootVectorX + "," + shootVectorY + ")");
        }
    }

    //총알 삽입
    private boolean insertBullet() {
        if ((remainBullet > 0) && (loadedBullet < 5)) {
            loadedBullet++;
            remainBullet--;
            return true;
        }
        return false;
    }

    //재장전
    public void doReload() {

        setReloadDisable();

        //재장전 전 준비
        remainBullet += loadedBullet;
        loadedBullet = 0;

        ((MainPlaying) mainPlayingContext).loadBulletImage();

        //사격 금지
        setShootDisable();

        reloadloop:
        for (int i = 0; i < 6; i++) {

            new Handler().postDelayed(new Runnable() {  // 0.3초마다에 1탄 장전
                @Override
                public void run() {

                    if (insertBullet()) {
                        ((MainPlaying) mainPlayingContext).loadBulletImage(); //이미지
                    } else {
                        ((MainPlaying) mainPlayingContext).setTempText("Reload complete");
                        setReloadAble();
                        setShootAble();
                        return;
                    }
                }
            }, 300 * i);

        }
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
    private void setShootAble(){
        shootAble = true;
    }

    //사격 불가능한 상태로 전환
    private void setShootDisable(){
        shootAble = false;
    }

    //return 재장전 가능 여부
    public boolean isReloadAble() {return reloadAble;}

    //재장전 가능한 상태로 전환
    private void setReloadAble(){
        reloadAble = true;
    }

    //재장전 불가능한 상태로 전환
    private void setReloadDisable(){
        reloadAble = false;
    }
}