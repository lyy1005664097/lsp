package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wulianwang.lsp.R;
/**
 * 作者：陈宏伟
 *       余小宁

 * 描述：5.15返回键的代码以及xml文件连接修改代码
 *
 * */
public class HistoryWorkActivity extends AppCompatActivity {

    private TextView elect;
    private ImageView back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_work);
        elect = findViewById(R.id.elect);
        back = findViewById(R.id.back);

        elect.setText("工作详情");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
