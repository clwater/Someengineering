package com.clwater.zcf;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by yszsyf on 16/3/9.
 */
public class Error extends AppCompatActivity {
    MyGifView gif;
    private MediaPlayer mPlayer = null;
    private Vibrator vibrator;
    private Button return_main;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error);

        gif = (MyGifView) findViewById(R.id.gif);
        // 设置背景gif图片资源
        gif.setMovieResource(R.drawable.baojing);


        mPlayer = MediaPlayer.create(this, R.raw.baojing);
        mPlayer.setLooping(true);
        mPlayer.start();

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {100, 600, 100, 600};
        vibrator.vibrate(pattern, 2);

        return_main = (Button) findViewById(R.id.return_main);
        return_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.stop();
                vibrator.cancel();
                finish();
            }
        });
    }
}
