package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wulianwang.lsp.R;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.net.URL;

import okhttp3.OkHttpClient;

public class AuthActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        private Handler mHandler;
                
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);

        public class Authentication extends  implements View.OnClickListener {
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
                setContentView(R.layout.activity_main);
                btn1=findViewById(R.id.btn1);
                btn2 = findViewById(R.id.btn2);
                textView = findViewById(R.id.tv);
                headIv = findViewById(R.id.iv);
                btn1.setOnClickListener(this);
                btn2.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn1:
                        intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, PICK);
                        break;
                    case R.id.btn2:
                        intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, IMAGE_RESULT_CODE);
                        break;
                }
            }

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
               // super.onActivityResult(requestCode, resultCode, data);
                switch (requestCode) {
                    // 表示 调用照相机拍照
                    case PICK:
                        if (resultCode == RESULT_OK) {
                            Bundle bundle = data.getExtras();
                            Bitmap bitmap = (Bitmap) bundle.get("data");
                            imgString = bitmapToBase64(bitmap);
                            uploadImg();
                        }
                        break;
                    // 选择图片库的图片
                    case IMAGE_RESULT_CODE:
                        if (resultCode == RESULT_OK) {
                            Uri uri = data.getData();
                            Bitmap bitmap2 = PhotoUtils.getBitmapFromUri(uri,);
                            imgString = bitmapToBase64(bitmap2);
                            uploadImg();
                        }
                        break;
                }
            }
            //上传图片文件的操作
            public void uploadImg() {
                okHttpClient okHttpClient = new OkHttpClient.Builder().build();
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
                        Gson gson = new Gson();
                        final Beans bean = gson.fromJson(data, Beans.class);
                        Log.d(TAG, "onResponse: " + data);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                //加载图片用的Gilde框架，也可以自己自由选择，
                                //""里面取决于服务端的返回值是否需要自行添加地址
                                Glide.with(MainActivity.this).load("" + bean.getData().getUrl()).into(headIv);
                            }
                        });
                    }
                });
            }}}}