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

/**
 * 5.13 未接任务详情 张妍妍 张玉
 */
public class TaskDetailActivity extends AppCompatActivity {

    private ImageView phone;
    private ImageView ivAdress;
    boolean flag = false;
    private Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task_detail);
        ImageView back = findViewById(R.id.back);
        bt1=(Button) this.findViewById(R.id.button3);

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
         //       Intent intent = new Intent(renwuxiangqing.this,MainActivity.class);
        //        startActivity(intent);
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
        if(flag){
            bt1.setVisibility(View.VISIBLE);
            bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

          }else{
            bt1.setVisibility(View.GONE);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        };
        bt1=(Button) this.findViewById(R.id.button3);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });}
}
