package com.example.aliceinunderland;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundHelper {
    private MediaPlayer player;
    private MediaPlayer player1;

    public void shootSoundPlayer(Context c) {
        player = MediaPlayer.create(c, R.raw.gun);
        player.start();
    }

    public void emptyMag(Context c) {
        player1 = MediaPlayer.create(c, R.raw.empty_mag);
        player1.start();
    }

    public void reloadPlayer(Context c) {
        player1 = MediaPlayer.create(c, R.raw.reload);
        player1.start();
    }
}
