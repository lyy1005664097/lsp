package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;




import com.wulianwang.lsp.R;

/**
 * 张春雨 杨光 3.6
 */
public class CompanyServiceDetailActivity extends AppCompatActivity {
    private TextView myTextView;
    private Button mybutton,bt2;
    private EditText et1,et2,et11,et12,et13;
    private ImageView im1;

    boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_service_detail);
        myTextView = (TextView)this.findViewById(R.id.textView);
        et1 = (EditText)this.findViewById(R.id.editText);
        mybutton = (Button)this.findViewById(R.id.button);
        bt2 = (Button)this.findViewById(R.id.button2);
        et2 = (EditText)findViewById(R.id.editText2);
        et11 = (EditText)findViewById(R.id.editText11);
        et12 = (EditText)findViewById(R.id.editText12);
        et13 = (EditText)findViewById(R.id.editText13);
        im1 = (ImageView)findViewById(R.id.af);




        if(flag){
            mybutton.setVisibility(View.VISIBLE);
        }else{
            mybutton.setVisibility(View.GONE);
        }

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myCallIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + "1234567891"));
                startActivity(myCallIntent);
                //点击事件


            }
        });

        et13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(CompanyServiceDetailActivity.this,MapLocationActivity.class
                );
                startActivity(i);

            }
        });

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();



            }
        });
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }
        });

    }
}
