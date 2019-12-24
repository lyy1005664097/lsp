package com.wulianwang.lsp.activity;

import android.os.Bundle;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.wulianwang.lsp.R;

/**
 * 卫星卓 李消飞 1.6
 */
public class PravcyActivity extends BaseActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pravcy);

        setActionBar(true, "隐私协议");

    //    TextView tv = findViewById(R.id.textView2);
    //    tv.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}