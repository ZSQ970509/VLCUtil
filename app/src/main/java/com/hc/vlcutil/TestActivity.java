package com.hc.vlcutil;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.pedrovlc.VlcListener;
import org.videolan.libvlc.pedrovlc.VlcVideoLibrary;

import java.util.TooManyListenersException;

public class TestActivity extends AppCompatActivity {
    private VlcVideoLibrary mVlcVideoLibrary;
    SurfaceView mVLCVideoView;
    ProgressBar mProgressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVLCVideoView = findViewById(R.id.videoVlc);
        mProgressBar = findViewById(R.id.videoPb);
        initVlc();
        mVLCVideoView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mVlcVideoLibrary.play("http://223.110.243.173/ott.js.chinamobile.com/PLTV/3/224/3221227215/index.m3u8");

            }
        }, 1000);
    }
    /**
     * 初始化vlc
     */
    private void initVlc() {
        mVlcVideoLibrary = new VlcVideoLibrary(this, mVLCVideoView);
        mVlcVideoLibrary.setVlcListener(new VlcListener() {
            @Override
            public void onComplete() {
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                Toast.makeText(TestActivity.this,"播放视频失败",Toast.LENGTH_LONG);
                mVlcVideoLibrary.stop();
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onBuffering(MediaPlayer.Event event) {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        });
    }
}
