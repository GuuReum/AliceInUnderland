package com.example.aliceinunderland;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class droppedBullet {
    int x = 0;
    int y = 0;
    int size = 100;
    Drawable droppedBulletImage;

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
