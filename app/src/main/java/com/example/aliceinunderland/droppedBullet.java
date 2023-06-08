package com.example.aliceinunderland;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class droppedBullet {
    private int x = 0;
    public int getX(){
        return x;
    }
    private int y = 0;
    private int size = 100;
    public int getSize() {
        return size;
    }
    private Drawable droppedBulletImage;

    public droppedBullet(int mX, int mY, Drawable d) {
        x = mX;
        y = mY;
        droppedBulletImage = d;
    }

    public void setBounds() {
        //image의 좌표 및 크기 설정
        droppedBulletImage.setBounds(x, y, x + size, y + size);
    }

    public void draw(Canvas canvas) {
        droppedBulletImage.draw(canvas);
    }

}
