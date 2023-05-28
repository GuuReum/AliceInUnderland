package com.example.aliceinunderland;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;

public class APlayer {
    private int x = 0;
    private int y = 0;
    final private int size = 200;
    private boolean isAlive = true;
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    private boolean isShootAble = true; // 사격 가능 여부
    public boolean isShootAble() {return isShootAble;}
    public void setShootAble(boolean shootable) {isShootAble = shootable;}

    private boolean isReloadAble = true; // 재장전 가능 여부
    public boolean isReloadAble() {return isReloadAble;}
    public void setReloadAble(boolean reloadable) {isReloadAble = reloadable;}

    private int loadedBullet = 5; //장전된 총알
    public int getLoadedBullet() {
        return loadedBullet;
    }
    private int remainBullet = 100;  //여분 총알
    public int getRemainBullet() {
        return remainBullet;
    }

    private Drawable playerImage;

    public void setPlayerImage(Drawable drawable) {
        playerImage = drawable;
    }

    public void setBounds() {
        //image의 좌표 및 크기 설정
        playerImage.setBounds(x, y, x + size, y + size);
    }

    public void draw(Canvas canvas) {
        playerImage.draw(canvas);
    }

    public void setX(int posX) {
        x = posX;
    }

    public int getX() {
        return x;
    }

    public void setY(int posY) {
        y = posY - size;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public void shootBullet() {
        --loadedBullet;
        setShootAble(false);

        new Handler().postDelayed(new Runnable() {  // 0.7초뒤에 사격 가능
            @Override
            public void run() {
                setShootAble(true);
            }
        }, 700);}

    public void prepareReload() {

        setShootAble(false); //사격 불가
        setReloadAble(false); //리로드 불가

        //재장전 전 준비
        remainBullet += loadedBullet;
        loadedBullet = 0;
    }

    public boolean insertBullet() {
        if ((remainBullet > 0) && (loadedBullet < 5)) {
            loadedBullet++;
            remainBullet--;
            return true;
        }
        return false;
    }
}