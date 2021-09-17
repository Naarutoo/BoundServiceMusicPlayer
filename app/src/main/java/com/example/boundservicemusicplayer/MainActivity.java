package com.example.boundservicemusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button mBtnPlay, mBtnPause, mBtnStop;
private MusicService musicService;
private ServiceConnection serviceConnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MusicService.ServiceBinder serviceBinder = (MusicService.ServiceBinder) service;
        musicService = serviceBinder.getMusicService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    initviews();
    }

    private void initviews() {
    mBtnPlay = findViewById(R.id.btnPlay);
    mBtnPause  = findViewById(R.id.btnPause);
    mBtnStop = findViewById(R.id.btnStop);
    startService();

    mBtnPlay.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (musicService!=null){
                musicService.onPlay();
            }
        }
    });

    mBtnPause.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (musicService!=null){
                musicService.onPause();
            }
        }
    });

    mBtnStop.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (musicService!=null){
                musicService.onStop();
            }
        }
    });
    }

    private void startService() {
        Intent intent = new Intent(MainActivity.this,MusicService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }
}