package com.example.aliceinunderland;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

public class ATimer extends CountDownTimer {
    public ATimer(long maxPlayMillis, long coolDownMillis, TextView textView, Context c) {
        super(maxPlayMillis, coolDownMillis);
        timerTextView = textView;
        mContext = c;
    }

    private int remainMin = 10;
    private int remainSec = 0;
    private TextView timerTextView;

    public void onTick(long time) {
        //남은 시간을 분 단위 표시
        remainMin = (int) (time % 3600000) / 60000;
        //남은 시간의 초 단위 표시
        remainSec = (int) ((time % 3600000) % 60000) / 1000;

        timerTextView.setText(remainMin + " : " + remainSec);
    }

    private Context mContext;

    public void onFinish() {
        ((AMainPlaying) mContext).gameClear();
    }
}
