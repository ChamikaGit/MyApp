package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;

import com.rdmns24.chamiapps.rdmns24live.R;

public class Splashscreen extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splashscreen);

        getSupportActionBar().hide();

        videoView = (VideoView) findViewById(R.id.videoView);

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.startup);

        videoView.setVideoURI(video);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (isFinishing())
                    return;

                startActivity(new Intent(Splashscreen.this, MainActivity.class));
                finish();
            }
        });

        videoView.start();
    }
}
