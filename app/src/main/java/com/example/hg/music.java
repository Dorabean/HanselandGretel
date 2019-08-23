package com.example.hg;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class music extends Activity {
    /**
     * Called when the activity is first created.
     */

    MediaPlayer player;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //음악파일 재생
        if (!player.isPlaying()) {
            player = MediaPlayer.create(this, R.raw.bgm);
            player.setLooping(true);
            player.start();
        }
    }
}