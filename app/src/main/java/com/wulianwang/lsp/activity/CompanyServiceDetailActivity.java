package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;




import com.wulianwang.lsp.R;
import com.wulianwang.lsp.bean.User;
import com.wulianwang.lsp.util.SharedPrefsUtil;

/**
 * 张春雨 杨光 3.6
 */
public class CompanyServiceDetailActivity extends BaseActivity {
    private TextView myTextView;
    private Button mybutton,bt2;
    private TextView et1,et2,et11,et12,et13;
    private ImageView im;

    User user;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_service_detail);

        initView();
        setActionBar(true, "企业服务详情");
    }

    @Override
    public void initData() {
        user = SharedPrefsUtil.getValue(this, "user", (User)null);
    }

    @Override
    public void initView() {
        myTextView = findViewById(R.id.textView);
        et1 = findViewById(R.id.editText);
        mybutton = findViewById(R.id.button);
        bt2 = findViewById(R.id.button2);
        et2 = findViewById(R.id.editText2);
        et11 = findViewById(R.id.editText11);
        et12 = findViewById(R.id.editText12);
        et13 = findViewById(R.id.editText13);
        im = findViewById(R.id.im);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myCallIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + "1234567891"));
                startActivity(myCallIntent);
                //点击事件
            }
        });
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(CompanyServiceDetailActivity.this,MapLocationActivity.class
                );
                startActivity(i);
            }
        });
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if(user != null){
            mybutton.setVisibility(View.VISIBLE);
        }else{
            mybutton.setVisibility(View.GONE);
        }
    }
}
