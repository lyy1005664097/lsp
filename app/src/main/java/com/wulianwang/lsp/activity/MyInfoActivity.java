package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wulianwang.lsp.R;

/**
 * 孙帅达，张世浩 5.1
 */
public class MyInfoActivity extends BaseActivity {

    TextView l1, l2, l3, l4, l5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        initView();
        setActionBar(true, "我的");
    }

    @Override
    public void initView() {
        l1 = findViewById(R.id.nameAuth);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyInfoActivity.this, NameAuthActivity.class);
                startActivity(intent);
            }
        });
        l2 = findViewById(R.id.elecAuth);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyInfoActivity.this, ElectricityCertificationActivity.class);
                startActivity(intent);
            }
        });
        l3 = findViewById(R.id.publish);
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyInfoActivity.this, PublishActivity.class);
                startActivity(intent);
            }
        });
        l4 = findViewById(R.id.zhiyerenzheng);
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyInfoActivity.this, ProfessionalCertificationActivity.class);
                startActivity(intent);
            }
        });
        l5 = findViewById(R.id.myorder);
        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyInfoActivity.this, MyWorkListActivity.class);
                startActivity(intent);
            }
        });
    }
}
