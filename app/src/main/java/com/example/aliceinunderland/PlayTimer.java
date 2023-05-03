package com.example.aliceinunderland;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;

//타이머 클래스..
public class PlayTimer extends CountDownTimer {
    public PlayTimer(long maxPlayTime, long coolDownTime, Context mContext) {
        super(maxPlayTime, coolDownTime);
        mainActivityContext = mContext;
    }

    private long remainTime;
    private String txt;
    private final Context mainActivityContext;

    @Override
    public void onTick(long time) {
        remainTime = time;
        txt = ((int) remainTime % 3600000 / 60000) + ":" + ((int) remainTime % 3600000 % 60000 / 1000);
        ((MainPlaying) mainActivityContext).countdownText.setText(txt);
    }

    @Override
    public void onFinish() {
        Intent intent = new Intent(mainActivityContext, MainEnd.class);
        mainActivityContext.startActivity(intent);

        /*
        TODO:finish 함수가 없어서 MainPlaying Activity가 남아있음
        TODO:타이머에서 사용하는 변수들만 따로 이 class에서 관리해줄지, 아니면 타이머 자체를 분리시킬지 고민해봐야 할 필요가 있음.
        */
    }
}