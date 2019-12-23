package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wulianwang.lsp.R;
import com.wulianwang.lsp.bean.User;
import com.wulianwang.lsp.util.SharedPrefsUtil;

/**
 * 杜一航，魏来 3.3
 */
public class PersonalServiceDetailActivity extends BaseActivity {
    private TextView textView1, textView2, textView3, textView4, textView5, textView6;
    private Button bb;
    boolean flag = false;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_service_detail);

        initData();
        initView();
        setActionBar(true, "个人服务详情");
    }

    @Override
    public void initData() {
        user = SharedPrefsUtil.getValue(this, "user", (User) null);

    }

    @Override
    public void initView() {
        textView1 = findViewById(R.id.text15);
        textView2 = findViewById(R.id.text10);
        textView3 = findViewById(R.id.text7);
        textView4 = findViewById(R.id.text14);
        textView5 = findViewById(R.id.text11);
        textView6 = findViewById(R.id.text8);
        bb = findViewById(R.id.button3);

        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(PersonalServiceDetailActivity.this, MapSearchActivity.class);
                startActivity(x);
            }

        });
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:13100000000"));
                startActivity(intent);
            }
        });
        bb.setOnClickListener(view -> {

        });

        if (user != null) {
            bb.setVisibility(View.VISIBLE);
        } else {
            bb.setVisibility(View.GONE);

        }

    }
};