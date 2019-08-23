package com.example.hg;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class music2 extends Activity {
    /**
     * Called when the activity is first created.
     */

    MediaPlayer player2;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //음악파일 재생
        if (!player2.isPlaying()) {
            player2 = MediaPlayer.create(this, R.raw.bgm2);
            player2.setLooping(true);
            player2.start();
        }
    }
}