package com.example.makeg.componenet;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.example.makeg.R;

public class MyBoundService extends Service {

    private MediaPlayer mediaPlayer;
    public MyBoundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.quran);
        Log.d("BoundService", "onCreate: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        Log.d("BoundService", "onDestroy: ");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //UI Thread or Main Thread
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}