package com.example.aliceinunderland;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AMainPlaying extends AppCompatActivity {
    private AGameView gameView;
    private APlayerMoveHelper playerMoveHelper;
    private ATimer timer;
    private TextView timerText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_playing);

        timerText = (TextView) findViewById(R.id.timer);
        timer = new ATimer(600000, 1000, timerText, this);
        timer.start();

        gameView = (AGameView) findViewById(R.id.gameView);
        gameView.invalidate();

        playerMoveHelper = new APlayerMoveHelper(gameView);
        playerMoveHelper.execute(gameView.player.getX(), gameView.player.getY());
    }


    public void onClickLeftBtn(View view) {
        ImageView leftBtn = findViewById(R.id.moveLeftBtn);
        leftBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    playerMoveHelper.setLeftAnimation();
                if (event.getAction() == MotionEvent.ACTION_UP)
                    playerMoveHelper.setLeftAnimation();
                return true;
            }
        });
    }

    public void onClickRightBtn(View view) {
        ImageView rightBtn = findViewById(R.id.moveRightBtn);
        rightBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    playerMoveHelper.setRightAnimation();
                if (event.getAction() == MotionEvent.ACTION_UP)
                    playerMoveHelper.setRightAnimation();
                return true;
            }
        });
    }

    public void onClickReloadBtn(View view) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void gameClear(){
        Intent intent = new Intent(this, MainEnd.class);
        startActivity(intent);
        finish();
    }
}