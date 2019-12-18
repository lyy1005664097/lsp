package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.wulianwang.lsp.R;

public class renwuxiangqing extends AppCompatActivity {

    private ImageView phone;
    private ImageView ivAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_renwuxiangqing);
        ImageView back = findViewById(R.id.back);

        ivAdress = findViewById(R.id.iv_address);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(renwuxiangqing.this,MainActivity.class);
                startActivity(intent);
            }
        });

//跳转到打电话
        ImageView phone = findViewById(R.id.imageView2);
        phone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent (Intent.ACTION_CALL_BUTTON);
                startActivity(intent );
            }

        });




        //接单
        /*public void startActivityAfterLogin(Intent intent){
            if (!UserManager.getInstance().isLogin()) {
                ComponentName componentName = new ComponentName(getContext(),LoginActivity.class);
                Intent.putExtra("className",Intent.getComponent().getClassName());
                Intent.setComponent(componentName);
                super.startActivity(intent);
            } else {
                super.startActivity(intent);
            }
        }*/


    }

    private class LoginActivity {
    }
}
