package com.example.aliceinunderland;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class GameViewA extends View {
    private PlayerA player;
    private ArrayList<Enemy> enemyArray;
    private Bitmap backgroundImage;

    private int canvasHeight = 1;
    private int canvasWidth = 1;

    public GameViewA(Context context) {
        super(context);
        initSetting(context);
    }

    public GameViewA(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSetting(context);
    }

    //초기 설정
    private void initSetting(Context context) {
        player = new PlayerA();
        enemyArray = new ArrayList<>();
        Resources res = context.getResources();
        //player 이미지 설정
        player.playerImage = res.getDrawable(R.drawable.prota);
        //player 좌표 설정
        player.x = (int) (getWidth() / 2);
        player.y = getHeight();
        //player.setPlayerImage(res.getDrawable(R.drawable.prota));
        //background 이미지 설정
        backgroundImage = BitmapFactory.decodeResource(res, R.drawable.background);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvasHeight = getHeight();
        canvasWidth = getWidth();

        //backgroundImage의 크기와 canvas의 크기가 다를 경우 Resizing
        if (backgroundImage.getWidth() != canvasWidth || backgroundImage.getHeight() != canvasHeight)
            backgroundImage = backgroundImage.createScaledBitmap(backgroundImage, canvasWidth, canvasHeight, true);

        //Draw background
        canvas.drawBitmap(backgroundImage, 0, 0, null);

        //Draw player
        player.playerImage.setBounds(player.x, player.y, player.x + 100, player.y + 100);

        //player가 canvas 밖으로 나가지 못하게..
        if (player.x >= canvasWidth) player.x = canvasWidth;

        player.playerImage.draw(canvas);
    }
}
