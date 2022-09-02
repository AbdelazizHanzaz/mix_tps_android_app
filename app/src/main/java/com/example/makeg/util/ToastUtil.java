package com.example.makeg.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {


    public static void getMessage(Context c, String msg){
        Toast.makeText(c, msg, Toast.LENGTH_SHORT).show();
    }

}
