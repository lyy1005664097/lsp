package com.wulianwang.lsp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.wulianwang.lsp.R;
import com.wulianwang.lsp.util.PhotoUtils;

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
public class ElectricityCertificationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1, btn2;
    private ImageView headIv;
    private TextView textView;
    private static final String TAG = "MainActivity";
    private final int IMAGE_RESULT_CODE = 2;
    private final int PICK = 1;
    private String url = "";//此处为上传图片地址，我就不写了
    private String imgString = "";
    private Intent intent;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_certification);
        EditText ET_name=findViewById(R.id.name);
        EditText ET_number1=findViewById(R.id.number1);
        EditText ET_number2=findViewById(R.id.number2);
        EditText ET_number3=findViewById(R.id.number3);
        ImageView ET_img1=findViewById(R.id.image1);
        ImageView ET_img2=findViewById(R.id.image2);
        Button ET_button1=findViewById(R.id.button1);

        ET_img1.setOnClickListener(this);
        ET_img2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image1:
                intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, PICK);
                break;
            case R.id.image2:
                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_RESULT_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 表示 调用照相机拍照
            case PICK:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
              //      imgString = bitmapToBase64(bitmap);
                    uploadImg();
                }
                break;
            // 选择图片库的图片
            case IMAGE_RESULT_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    Bitmap bitmap2 = PhotoUtils.getBitmapFromUri(uri, this);
               //     imgString = bitmapToBase64(bitmap2);
                    uploadImg();
                }
                break;
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