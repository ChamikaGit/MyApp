package com.rdmns24.chamiapps.rdmns24live.Actvities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.rdmns24.chamiapps.rdmns24live.R;

import java.security.MessageDigest;

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

//    private static boolean validateAppSignature(Context context) {
//        try {
//            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
//            for (Signature signature : packageInfo.signatures) {
//                try {
//                    String sha256 = getSHA256(signature.toByteArray());
//                    Log.e("getSHA256 ","getSHA256 :"+sha256);
//                    return sha256.equals("6184025E7A6524E3CBC8F25E83EBE056AB9ECE886ECEAB7D1B3503458B9DB4CD");
//                } catch (Exception e) {
//                }
//            }
//        } catch (Exception e) {
//        }
//        return false;
//    }

}
