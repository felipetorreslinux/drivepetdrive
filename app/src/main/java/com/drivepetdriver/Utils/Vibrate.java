package com.drivepetdriver.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class Vibrate {
    static Vibrator vibrator;
    public static void playVibrate (Activity activity){
        vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        }else{
            vibrator.vibrate(1000);
        }
    };

    public static void stopVibrate (){
        if(vibrator != null){
            vibrator.cancel();
        }
    }
}
