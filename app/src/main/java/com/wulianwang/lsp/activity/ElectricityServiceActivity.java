package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.wulianwang.lsp.R;

/**
 * 沈志宏、任满意    2.1
 */
public class ElectricityServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_service);
        ImageButton btn = (ImageButton)this.findViewById(R.id.imageButton1);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

             //   finish();
                onBackPressed();
            }
        });
    }
}

