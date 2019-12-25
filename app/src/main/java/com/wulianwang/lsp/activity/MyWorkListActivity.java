package com.wulianwang.lsp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wulianwang.lsp.R;

public class MyWorkListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_work_list);

        initView();
        setActionBar(true, "我的工单");
    }

    @Override
    public void initView() {
        TextView tv1 = findViewById(R.id.person);
        TextView tv2 = findViewById(R.id.company);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyWorkListActivity.this, MyReleaseActivity.class);
                startActivity(intent);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyWorkListActivity.this, MyOrderActivity.class);
                startActivity(intent);
            }
        });
    }
}
