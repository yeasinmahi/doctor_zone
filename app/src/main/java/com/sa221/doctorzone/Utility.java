package com.sa221.doctorzone;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Arafat on 20/04/2017.
 */

public class Utility {
    public static void getPermission(Activity activity, String[] permissions) {
        //Manifest.permission.CALL_PHONE
        ActivityCompat.requestPermissions(activity, permissions, 1);
    }

    public static boolean checkPermission(Context context, String permission) {
        if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    public static boolean checkLocationPermission(Context context) {
        if (Utility.checkPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) && Utility.checkPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION))
            return true;
        return false;
    }
}
