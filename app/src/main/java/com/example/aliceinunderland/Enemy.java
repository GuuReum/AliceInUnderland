package com.example.aliceinunderland;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class Enemy {
    //Enemy의 좌표
    private int x = 0;
    private int y = 0;
    //Enemy의 크기
    final private int size = 200;

    private boolean isAlive = true;

    public void setIsAlive(){
        isAlive = (!isAlive);
    }

    public boolean getIsAlive(){
        return isAlive;
    }
    //Enemy Image
    private Drawable enemyImage;

    public void setEnemyImage(Drawable drawable){
        this.enemyImage = drawable;
    }

    public void setBounds() {
        //image의 좌표 및 크기 설정
        enemyImage.setBounds(x, y, x + size, y + size);
    }

    public void draw(Canvas canvas) {enemyImage.draw(canvas);
    }

    public int getSize(){
        return size;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y - size;
    }
}