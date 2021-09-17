package com.example.boundservicemusicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;
    public MusicService() {
    }

    @Override
    public void onCreate() {
        mediaPlayer = MediaPlayer.create(this,R.raw.mp3);
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder();
    }
    public void onPlay(){
if (!mediaPlayer.isPlaying()){
    mediaPlayer.start();
}
    }

    public void onPause(){
if (mediaPlayer.isPlaying()){
    mediaPlayer.pause();
}
    }

    public void onStop(){
if (mediaPlayer.isPlaying()){
    mediaPlayer.stop();
}
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }

    public class ServiceBinder extends Binder{
        public MusicService getMusicService(){
            return MusicService.this;
        }
    }
}