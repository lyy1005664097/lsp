package com.wulianwang.lsp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.wulianwang.lsp.R;
import com.wulianwang.lsp.util.Tools;

import java.io.File;
import java.io.IOException;

/**
 * 杨纪元 戎柯君 5.4
 */
public class CompanyCertificateActivity extends BaseActivity{

    ImageView[] ims = new ImageView[3];
    Uri[] uris = new Uri[3];
    String[] names = new String[]{"image0.jpg", "image1.jpg", "image2.jpg",};
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_certificate);

        initView();
        setActionBar(true, "企业认证");
    }

    @Override
    public void initView() {
        ims[0] = findViewById(R.id.imageButton3);
        ims[1] = findViewById(R.id.imageButton1);
        ims[2] = findViewById(R.id.imageButton);
        ims[0].setOnClickListener( view -> {
            i = 0;
            requestPermission();
        });
        ims[1].setOnClickListener( view -> {
            i = 1;
            requestPermission();
        });
        ims[2].setOnClickListener( view -> {
            i = 2;
            requestPermission();
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(dm.widthPixels - Tools.dp2px(this, 20),
                1080*(dm.widthPixels - Tools.dp2px(this, 20))/1920);
        llp.setMarginStart(Tools.dp2px(this, 10));
        llp.setMarginEnd(Tools.dp2px(this, 10));
        ims[0].setLayoutParams(llp);
        ims[1].setLayoutParams(llp);
        ims[2].setLayoutParams(llp);
    }

    private void requestPermission(){
        if(Build.VERSION.SDK_INT >= 23){
            if(ContextCompat.checkSelfPermission(CompanyCertificateActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(CompanyCertificateActivity.this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(CompanyCertificateActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
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
            uris[i] = FileProvider.getUriForFile(CompanyCertificateActivity.this,
                    "com.example.cameraalbumtest.fileprovider", outputImage);
        }
        else{
            uris[i] = Uri.fromFile(outputImage);
        }
        Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uris[i]);
        startActivityForResult(intent, 400);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case 400:
                if(resultCode==RESULT_OK) {
                    Bitmap bitmap = Tools.compress(uris[i].getPath(), 1920, 1080);
                    ims[i].setImageBitmap(bitmap);
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
                    Toast.makeText(CompanyCertificateActivity.this, "请在“设置”中开启权限", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            takePhoto();
        }
    }
}
