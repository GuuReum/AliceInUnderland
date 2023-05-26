package com.example.aliceinunderland;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class AGameView extends View {
    public APlayer player;
    private Bitmap backgroundImage;

    private int canvasHeight = 1;
    private int canvasWidth = 1;

    public AGameView(Context context) {
        super(context);
        player = new APlayer();
        initSetting(context);
    }

    public AGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        player = new APlayer();
        initSetting(context);
    }

    //초기 설정
    private void initSetting(Context context) {
        Resources res = context.getResources();
        //player 이미지 설정
        player.setPlayerImage(res.getDrawable(R.drawable.prota));
        //background 이미지 설정
        backgroundImage = BitmapFactory.decodeResource(res, R.drawable.background);
    }

    boolean firstDraw = true;

    @Override
    protected void onDraw(Canvas canvas) {
        //처음에만 실행됨. player의 초기 좌표 설정

        if (firstDraw) {
            firstDraw = false;
            player.setX((int) getWidth() / 2);
            player.setY(getHeight());
        }

        canvasHeight = getHeight();
        canvasWidth = getWidth();

        Log.v("playerPos", player.getX() + ", " + player.getY() +"A"+ canvasWidth);

        //backgroundImage의 크기와 canvas의 크기가 다를 경우 Resizing
        if (backgroundImage.getWidth() != canvasWidth || backgroundImage.getHeight() != canvasHeight)
            backgroundImage = backgroundImage.createScaledBitmap(backgroundImage, canvasWidth, canvasHeight, true);

        //Draw background
        canvas.drawBitmap(backgroundImage, 0, 0, null);

        //set Bounds of player
        player.setBounds();

        //Draw player
        player.draw(canvas);
    }
}
