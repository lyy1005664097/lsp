package com.wulianwang.lsp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.content.ContextCompat;

public class Tools {

    //图片压缩
    public static Bitmap compress(String path, int width, int height){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        int wSize = options.outWidth/width;
        int hSize = options.outHeight/height;
        if(wSize > hSize){
            options.inSampleSize = wSize;
        }else{
            options.inSampleSize = hSize;
        }
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

    //dp 转 px
    public static int dp2px(Context context, double dp){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    //px 转 dp
    public static int px2dp(Context context, double px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
