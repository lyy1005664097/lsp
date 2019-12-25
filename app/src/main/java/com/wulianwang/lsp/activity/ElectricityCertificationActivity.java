package com.wulianwang.lsp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.wulianwang.lsp.R;
import com.wulianwang.lsp.util.PhotoUtils;
import com.wulianwang.lsp.util.Tools;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 刘奇 冯鑫 5.5
 */
public class ElectricityCertificationActivity extends BaseActivity implements View.OnClickListener {

    private final int PICK = 1;
    private String url = "";//此处为上传图片地址，我就不写了
    private String imgString = "";
    private Intent intent;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    ImageView[] ET_img = new ImageView[2];
    Uri[] uris = new Uri[2];
    String[] names = new String[]{"im0.jpg", "im1.jpg",};
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_certification);

        initView();
        setActionBar(true, "电力认证");
    }

    @Override
    public void initView() {
        EditText ET_name=findViewById(R.id.name);
        EditText ET_number1=findViewById(R.id.number1);
        EditText ET_number2=findViewById(R.id.number2);
        EditText ET_number3=findViewById(R.id.number3);
        ET_img[0]=findViewById(R.id.image1);
        ET_img[1]=findViewById(R.id.image2);
        Button ET_button1=findViewById(R.id.button1);

        ET_img[0].setOnClickListener(this);
        ET_img[1].setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image1:
                i = 0;
                requestPermission();
                break;
            case R.id.image2:
                i = 1;
                requestPermission();
                break;
        }
    }

    private void requestPermission(){
        if(Build.VERSION.SDK_INT >= 23){
            if(ContextCompat.checkSelfPermission(ElectricityCertificationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(ElectricityCertificationActivity.this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(ElectricityCertificationActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
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
            uris[i] = FileProvider.getUriForFile(ElectricityCertificationActivity.this,
                    "com.example.cameraalbumtest.fileprovider", outputImage);
        }
        else{
            uris[i] = Uri.fromFile(outputImage);
        }
        Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uris[i]);
        startActivityForResult(intent, PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case PICK:
                if(resultCode==RESULT_OK) {
                    Bitmap bitmap = Tools.compress(uris[i].getPath(), 1920, 1080);
                    ET_img[i].setImageBitmap(bitmap);
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
                    Toast.makeText(ElectricityCertificationActivity.this, "请在“设置”中开启权限", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            takePhoto();
        }
    }

    //上传图片文件的操作
    public void uploadImg() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //上传图片参数需要与服务端沟通，我就不多做解释了，我添加的都是我们服务端需要的
        //你们根据情况自行更改
        //另外网络请求我就不多做解释了
        FormBody body = new FormBody.Builder().add("dir", "c/image")
                .add("data", imgString)
                .add("file", "headicon")
                .add("ext", "jpg").build();
        Request request = new Request.Builder().url(url).post(body).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
           //     Gson gson = new Gson();
          //      final Beans bean = gson.fromJson(data, Beans.class);
          //      Log.d(TAG, "onResponse: " + data);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //加载图片用的Gilde框架，也可以自己自由选择，
                        //""里面取决于服务端的返回值是否需要自行添加地址
                     //   Glide.with(getApplicationContext()).load("" + bean.getData().getUrl()).into(headIv);
                    }
                });
            }
        });
    }}