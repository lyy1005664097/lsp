package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wulianwang.lsp.R;
/**
 * 作者：陈宏伟
 *       余小宁

 * 描述：5.15返回键的代码以及xml文件连接修改代码
 *
 * */
public class HistoryWorkActivity extends BaseActivity {

    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_work);

        initView();
        setActionBar(true, "任务详情");
    }

    @Override
    public void initView() {
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating((float)3.5);
    }
}
