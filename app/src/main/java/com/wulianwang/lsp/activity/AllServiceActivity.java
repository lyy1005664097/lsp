package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wulianwang.lsp.R;

public class AllServiceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_service);

        initView();
        setActionBar(true, "服务");
    }

    @Override
    public void initView() {
        RelativeLayout tv1 = (RelativeLayout) findViewById(R.id.person);
        RelativeLayout tv2 = (RelativeLayout) findViewById(R.id.company);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllServiceActivity.this, PersonalServiceActivity.class);
                startActivity(intent);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllServiceActivity.this, CompanyServiceActivity.class);
                startActivity(intent);
            }
        });
    }
}
