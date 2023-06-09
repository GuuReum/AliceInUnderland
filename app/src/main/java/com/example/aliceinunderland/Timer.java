package com.example.aliceinunderland;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

public class Timer extends CountDownTimer {
    public Timer(long maxPlayMillis, long coolDownMillis, TextView textView, Context c) {
        super(maxPlayMillis, coolDownMillis);
        timerTextView = textView;
        mContext = c;
    }

    private int remainMin = 10;
    private int remainSec = 0;
    private TextView timerTextView;

    private long remainTime = 0;

    public void onTick(long time) {
        //남은 시간을 분 단위 표시
        remainMin = (int) (time % 3600000) / 60000;
        //남은 시간의 초 단위 표시
        remainSec = (int) ((time % 3600000) % 60000) / 1000;
        //Wave 불러오기용 초
        int wavesec = (int) (time / 1000) % 10;

        remainTime = time;

        timerTextView.setText(remainMin + " : " + remainSec);

        if (wavesec == 7) {  //10초마다 (1의 자리수가 7가 될 때마다)
            ((MainPlaying) mContext).timeForWave();  //웨이브 시작
        }

    }

    private Context mContext;

    public void onFinish() {((MainPlaying) mContext).gameClear();}

    public long getRemainTime() {return remainTime;}

}
