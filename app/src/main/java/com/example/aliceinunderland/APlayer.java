package com.example.aliceinunderland;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

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
}