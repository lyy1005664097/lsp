package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.wulianwang.lsp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 霍东芳 张会纳 5.16
 */
public class MyOrderActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private TextView tv_1;
    ImageView mImageView01;

    private ImageView choRili;
    int mYear;
    int mMonth;
    int mDay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        radioGroup = (RadioGroup) findViewById(R.id.RG);
        mImageView01 = (ImageView) findViewById(R.id.ImageView1);
        //radioGroup.setOnCheckedChangeListener(this);
        choRili=(ImageView)findViewById(R.id.cho_rili);
        tv_1 =(TextView) findViewById(R.id.tv_1) ;
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        mImageView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });//返回上一个界面

        choRili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 调用时间选择器

                new DatePickerDialog(MyOrderActivity.this,onDateSetListener, mYear, mMonth, mDay).show();
            }
        });





    }



    //@Override
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String mYear = String.valueOf(year);
            String mMonth = String.valueOf(monthOfYear);
            String mDay = String.valueOf(dayOfMonth);

            tv_1.setText(mYear+"-"+mMonth+"-"+mDay);
        }
    };
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.RadioButton1:
                ;//转接处理中界面
            case R.id.RadioButton2:
                ;//转接已完成界面

        }
    }
}