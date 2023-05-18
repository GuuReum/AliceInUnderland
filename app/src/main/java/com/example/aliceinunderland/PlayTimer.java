package com.example.aliceinunderland;

import android.content.Context;
import android.os.CountDownTimer;

//타이머 클래스..
public class PlayTimer extends CountDownTimer {
    public PlayTimer(long maxPlayTime, long coolDownTime, Context mContext) {
        super(maxPlayTime, coolDownTime);
        mainPlayingContext = mContext;
    }

    private long remainTime;
    private String txt;
    private final Context mainPlayingContext;

    @Override
    public void onTick(long time) { // coolDownTime마다 시간을 갱신해서 Timer 텍스트뷰에 표시해줌
        remainTime = time;
        int remainmin = (int) remainTime % 3600000 / 60000;
        int remainsec = (int) remainTime % 3600000 % 60000 / 1000;
        txt = remainmin + ":" + remainsec;
        ((MainPlaying) mainPlayingContext).countdownText.setText(txt);
    }

    @Override
    public void onFinish() { // 타이머의 시간이 0이 된다면 게임 클리어!
        ((MainPlaying) mainPlayingContext).EndPlaying();

        //Intent intent = new Intent(mainPlayingContext, MainEnd.class);
        //mainPlayingContext.startActivity(intent);
        //finish();
        //
        //finish 함수가 없어서 MainPlaying Activity가 남아있음
        //타이머에서 사용하는 변수들만 따로 이 class에서 관리해줄지, 아니면 타이머 자체를 분리시킬지 고민해봐야 할 필요가 있음.
        //
    }
}