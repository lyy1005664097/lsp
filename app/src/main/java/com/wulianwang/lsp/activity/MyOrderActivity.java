package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.wulianwang.lsp.fragment.FragmentTab4;
import com.wulianwang.lsp.fragment.OrderCompleteFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 霍东芳 张会纳 5.16
 */
public class MyOrderActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private TextView tv_1;

    private ImageView choRili;
    int mYear;
    int mMonth;
    int mDay;

    FragmentTab4 fragmentTab4;
    OrderCompleteFragment fragmentTab5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        initView();
        setActionBar(true, "我的接单");
    }

    @Override
    public void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.RG);
        radioGroup.setOnCheckedChangeListener(this);
        choRili=(ImageView)findViewById(R.id.cho_rili);
        tv_1 =(TextView) findViewById(R.id.tv_1) ;
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        choRili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 调用时间选择器

                new DatePickerDialog(MyOrderActivity.this,onDateSetListener, mYear, mMonth, mDay).show();
            }
        });

        tv_1.setText(mYear+"-"+(mMonth + 1)+"-"+mDay);
        radioGroup.check(R.id.RadioButton1);
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String mYear = String.valueOf(year);
            String mMonth = String.valueOf(monthOfYear + 1);
            String mDay = String.valueOf(dayOfMonth);

            tv_1.setText(mYear+"-"+mMonth+"-"+mDay);
        }
    };

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hide(ft);
        switch (checkedId) {
            case R.id.RadioButton1:
                if(fragmentTab4 == null){
                    fragmentTab4 = new FragmentTab4();
                    ft.add(R.id.frame, fragmentTab4, "fragmentTab4");
                }else {
                    ft.show(fragmentTab4);
                }
                break;
            case R.id.RadioButton2:
                if(fragmentTab5 == null){
                    fragmentTab5 = new OrderCompleteFragment();
                    ft.add(R.id.frame, fragmentTab5, "fragmentTab5");
                }else {
                    ft.show(fragmentTab5);
                }
                break;
        }
        ft.commit();
    }

    private void hide(FragmentTransaction ft){
        if(fragmentTab4 != null){
            ft.hide(fragmentTab4);
        }
        if(fragmentTab5 != null){
            ft.hide(fragmentTab5);
        }
    }
}