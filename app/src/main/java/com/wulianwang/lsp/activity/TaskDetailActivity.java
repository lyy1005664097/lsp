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
import android.widget.TextView;

import com.wulianwang.lsp.R;

/**
 * 5.13 未接任务详情 张妍妍 张玉
 */
public class TaskDetailActivity extends BaseActivity {

    private ImageView phone;
    private TextView ivAdress;
    boolean flag = false;
    private Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        initView();
        setActionBar(true, "任务详情");
    }

    @Override
    public void initView() {

        bt1=(Button) this.findViewById(R.id.button3);

        //跳转到打电话
        ImageView phone = findViewById(R.id.imageView2);
        phone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent (Intent.ACTION_CALL_BUTTON);
                startActivity(intent );
            }

        });

        ivAdress = findViewById(R.id.textView15);
        ivAdress.setOnClickListener(view -> {
            Intent intent = new Intent(TaskDetailActivity.this, MapLocationActivity.class);
            startActivity(intent);
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
        };
    }
}
