package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.wulianwang.lsp.R;
import com.wulianwang.lsp.fragment.FragmentTab1;
import com.wulianwang.lsp.fragment.FragmentTab3;
import com.wulianwang.lsp.fragment.HandlingPublishFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.bigkoo.pickerview.TimePickerView;

/**
 * 成员：刘海帆 范王娟
 * 5.12 我的发布
 */

public class MyReleaseActivity extends BaseActivity {

    private TextView tv2;
    private ImageView ib2;
    private RadioGroup rg;
    int m_year,m_month,m_day;
    private Calendar ca;

    private FragmentTab1 fragment1;
    private HandlingPublishFragment fragment2;
    private FragmentTab3 fragment3;

    private TimePickerView dpv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_release);

        initView();
        setActionBar(true, "我的发布");
    }

    @Override
    public void initView() {
        tv2 = (TextView) this.findViewById(R.id.textview2);
        rg = (RadioGroup) this.findViewById(R.id.RG);
        ib2 =  this.findViewById(R.id.imageButton2);
        ca = Calendar.getInstance();
        m_year = ca.get(Calendar.YEAR);
        m_month = ca.get(Calendar.MONTH);
        m_day = ca.get(Calendar.DAY_OF_MONTH);
        tv2.setText(m_year + "-" + (m_month + 1) + "-" + m_day);
        //设置日期监听
        ib2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dpv.show(tv2);
            }
        });
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(m_year - 10, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(m_year + 10, 11, 31);
        dpv = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                TextView btn = (TextView) v;
                btn.setText(getTimes(date));
            }
        })//年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();

        //设置RadioButton的监听
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentManager fm =getSupportFragmentManager();
                FragmentTransaction ft1 =fm.beginTransaction();
                switch (i){
                    case R.id.radiobutton1:
                        hide(ft1);
                        if(fragment1 == null){
                            fragment1 = new FragmentTab1();
                            ft1.add(R.id.fragment, fragment1, "fragment1");
                        }else{
                            ft1.show(fragment1);
                        }
                        break;
                    case R.id.radiobutton2:
                        hide(ft1);
                        if(fragment2 == null){
                            fragment2 = new HandlingPublishFragment();
                            ft1.add(R.id.fragment, fragment2, "fragment2");
                        }else{
                            ft1.show(fragment1);
                        }
                        break;
                    case R.id.radiobutton3:
                        hide(ft1);
                        if(fragment3 == null){
                            fragment3 = new FragmentTab3();
                            ft1.add(R.id.fragment, fragment3, "fragment2");
                        }else{
                            ft1.show(fragment3);
                        }
                        break;
                }
                ft1.commit();
            }
        });//三个RadioButton的监听

       rg.check(R.id.radiobutton1);
    }

    private void hide(FragmentTransaction ft1){
        if(fragment1 != null ){
            ft1.hide(fragment1);
        }
        if(fragment2 != null){
            ft1.hide(fragment2);
        }
        if(fragment3 != null){
            ft1.hide(fragment3);
        }
    }

    private String getTimes(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
     //   Date date = null;
        return format.format(date);
    }


}
