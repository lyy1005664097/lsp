package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class FirstActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private TextView tv_1;
    ImageView mImageView01;

    private ImageView choRili;
    TimePickerView pvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup) findViewById(R.id.RG);
        mImageView01 = (ImageView) findViewById(R.id.ImageView1);
        radioGroup.setOnCheckedChangeListener(this);

        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_first);
            Calendar ca = Calendar.getInstance();
            int mYear = ca.get(Calendar.YEAR);
            int mMonth = ca.get(Calendar.MONTH);
            int mDay = ca.get(Calendar.DAY_OF_MONTH);


            choRili = findViewById(R.id.cho_rili);
            choRili.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pvTime.show();


                }
            });
            mImageView01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FirstActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            });

            //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
            //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
            Calendar selectedDate = Calendar.getInstance();
            Calendar startDate = Calendar.getInstance();
            startDate.set(2013, 0, 23);
            Calendar endDate = Calendar.getInstance();
            endDate.set(2029, 11, 28);
            //时间选择器
            pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {

                @Override
                public void onTimeSelect(Date date, View v) {//选中事件回调
                    // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                    TextView btn = (TextView) v;
                    btn.setText(getTimes(date));


                }
            })
                    //年月日时分秒 的显示与否，不设置则默认全部显示
                    .setType(new boolean[]{true, true, true, false, false, false})
                    .setLabel("年", "月", "日", "时", "", "")
                    .isCenterLabel(true)
                    .setDividerColor(Color.DKGRAY)
                    .setContentSize(21)
                    .setDate(selectedDate)
                    .setRangDate(startDate, endDate)
                    .setDecorView(null)
                    .build();


        }

    }
    private String getTimes (Date date){//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.RadioButton1:
                ;
            case R.id.RadioButton2:
                ;
        }
    }
}
