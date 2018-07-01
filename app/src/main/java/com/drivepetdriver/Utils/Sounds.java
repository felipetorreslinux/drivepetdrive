package com.drivepetdriver.Utils;

import android.app.Activity;
import android.media.MediaPlayer;

import com.drivepetdriver.R;

public class Sounds {

    static MediaPlayer mediaPlayer;

    public static void playSoundAlertCar(Activity activity){
        mediaPlayer = MediaPlayer.create(activity, R.raw.alert_car);
        mediaPlayer.start();
    };

    public static void stopSoundAlertNewCar(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
    };
}
