package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
//import android.util.Log;
import android.view.View;
import com.wulianwang.lsp.R;



public class MainActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ET_name=findViewById(R.id.name);
        EditText ET_number1=findViewById(R.id.number1);
        EditText ET_number2=findViewById(R.id.number2);
        EditText ET_number3=findViewById(R.id.number3);
        ImageView ET_img1=findViewById(R.id.image1);
        ImageView ET_img2=findViewById(R.id.image2);
        Button ET_button1=findViewById(R.id.button1);
       //Final TextView text =  findViewById(R.id.myTextView);

        ET_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                text.setText("Editing ET_name");
                return false;
            }
        });

        ET_number1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                text.setText("Editing ET_number1");
                return false;
            }
        });

        ET_number2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                text.setText("Editing ET_number2");
                return false;
            }
        });

        ET_number3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                text.setText("Editing ET_number3");
                return false;
            }
        });

        ET_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent1=new Intent();
                intent1.setClass(MainActivity.this,Main2Activity.class);
                MainActivity.this.startActivity(intent1);
            }
        });

        ET_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent2=new Intent();
                intent2.setClass(MainActivity.this,Main2Activity.class);
                MainActivity.this.startActivity(intent2);
            }
        });

        ET_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent3=new Intent();
                intent3.setClass(MainActivity.this,Main2Activity.class);


                MainActivity.this.startActivity(intent3);
            }
        });




    }

    public static class Main2Activity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
        }
    }
}
