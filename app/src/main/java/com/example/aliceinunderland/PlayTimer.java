package com.example.aliceinunderland;

//타이머 클래스..
public class PlayTimer{
    private long MaxPlayTime = 900000; //15분
    private long remainTime;
    private String txt;

    public String setRemainTime() {
        txt = ((int) remainTime % 3600000 / 60000) + ":" + ((int) remainTime % 3600000 % 60000 / 1000);
        return txt;
    }

    public long getMaxPlayTime() {
        return MaxPlayTime;
    }

    public void setRemainTime(long time) {
        remainTime = time;
    }
}