package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wulianwang.lsp.R;

/**
 * 5.14.2 赵田田 石亚宁
 */

public class AppraiseActivity extends AppCompatActivity {
    private ImageView button1;
    private Button button2;
    private EditText edit6;
    private RatingBar xx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appraise);
        button1 =(ImageView) findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        edit6=(EditText)findViewById(R.id.edit6);
        xx=(RatingBar)findViewById(R.id.xx);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        xx.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean formUser) {
                Toast.makeText(AppraiseActivity.this, "rating"+String.valueOf(rating), Toast.LENGTH_SHORT).show();
            }
        });
        edit6.getText().toString().trim();
    }
}
