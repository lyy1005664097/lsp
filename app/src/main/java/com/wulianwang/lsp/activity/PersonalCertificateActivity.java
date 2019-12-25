package com.wulianwang.lsp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;

import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wulianwang.lsp.R;
import com.wulianwang.lsp.util.Tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * 许浒 李鹏 5.3
 */
public class PersonalCertificateActivity extends BaseActivity {
    static final int TAKE_PHOTO=1;
    ImageView[] imbt = new ImageView[3];
    String[] names = new String[]{"output_image1.jpg", "output_image2.jpg", "output_image3.jpg"};
    Uri[] uris = new Uri[3];
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_certificate);

        initView();
        setActionBar(true, "个人认证");
    }

    @Override
    public void initView() {
        imbt[0] = findViewById(R.id.imageButton);
        imbt[1] = findViewById(R.id.imageButton2);
        imbt[2] = findViewById(R.id.imageButton3);

        imbt[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=0;
                requestPermission();
            }
        });

        imbt[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=1;
                requestPermission();
            }
        });

        imbt[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=2;
                requestPermission();
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(dm.widthPixels - Tools.dp2px(this, 20),
                1080*(dm.widthPixels - Tools.dp2px(this, 20))/1920);
        llp.setMarginStart(Tools.dp2px(this, 10));
        llp.setMarginEnd(Tools.dp2px(this, 10));
        llp.bottomMargin = Tools.dp2px(this, 10);
        imbt[0].setLayoutParams(llp);
        imbt[1].setLayoutParams(llp);
        imbt[2].setLayoutParams(llp);
    }

    private void requestPermission(){
        if(Build.VERSION.SDK_INT >= 23){
            if(ContextCompat.checkSelfPermission(PersonalCertificateActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(PersonalCertificateActivity.this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(PersonalCertificateActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA}, 300);
            }else{
                takePhoto();
            }
        }else{
            takePhoto();
        }
    }

    private void takePhoto(){
        File outputImage = new File(getExternalCacheDir(), names[i]);
        try//判断图片是否存在，存在则删除在创建，不存在则直接创建
        {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            uris[i] = FileProvider.getUriForFile(PersonalCertificateActivity.this,
                "com.example.cameraalbumtest.fileprovider", outputImage);
        }
        else{
            uris[i] = Uri.fromFile(outputImage);
        }
        Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uris[i]);
        startActivityForResult(intent,TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK) {
                    Bitmap bitmap = Tools.compress(uris[i].getPath(), 1920, 1080);
                    imbt[i].setImageBitmap(bitmap);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 300){
            for(int grant: grantResults){
                if(grant != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(PersonalCertificateActivity.this, "请在“设置”中开启权限", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            takePhoto();
        }
    }
}
