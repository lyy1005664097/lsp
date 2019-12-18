package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import com.wulianwang.lsp.Main2Activity;
import com.wulianwang.lsp.R;

public class MainActivity extends AppCompatActivity {
    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bt = (Button) findViewById(R.id.button);//id后面为上方button的id

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main2Activity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);


            }
        });








    }
}
