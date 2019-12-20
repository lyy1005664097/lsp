package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

public class MyReleaseActivity extends AppCompatActivity {
private RadioButton rb1,rb2,rb3;
private TextView tv1,tv2;
private ImageButton ib1,ib2;
private RadioGroup rg;
int m_year,m_month,m_day;
private Calendar ca;
//private Fragment frgament1;
//private android.app.Fragment fragment1;

    private FragmentTab1 fragment1;
    private HandlingPublishFragment fragment2;
    private FragmentTab3 fragment3;

    private TimePickerView dpv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_release);
        tv1 = (TextView) this.findViewById(R.id.textview1);
        tv2 = (TextView) this.findViewById(R.id.textview2);
        rg = (RadioGroup) this.findViewById(R.id.RG);
        rb1 = (RadioButton) this.findViewById(R.id.radiobutton1);
        rb2 = (RadioButton) this.findViewById(R.id.radiobutton2);
        rb3 = (RadioButton) this.findViewById(R.id.radiobutton3);
        ib1 = (ImageButton) this.findViewById(R.id.imageButton1);
        ib2 = (ImageButton) this.findViewById(R.id.imageButton2);
        ca = Calendar.getInstance();
        m_year = ca.get(Calendar.YEAR);
        m_month = ca.get(Calendar.MONTH);
        m_day = ca.get(Calendar.DAY_OF_MONTH);
        tv2.setText(m_year + ":" + (m_month + 1) + ":" + m_day);
        //设置日期监听
         ib2.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View view) {
                 dpv.show(tv2);
             }
         });
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2013, 0, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2029, 11, 28);
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
                FragmentManager fm =getFragmentManager();
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

        //设置(页面跳转)返回键链接赵江浩5.11任务页
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(MyReleaseActivity.this,MyWorkListActivity.class);
                MyReleaseActivity.this.startActivity(intent);
            }
        });//(页面跳转)返回键链接赵江浩5.11任务页


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

/*
    private int getTimes(Date date) {
        return 0;
    }*/

   /* private class TestFragment extends Fragment {
    }*/

    /*static class TimePickerView {
        public void show() {
        }

        public static class Builder extends TimePickerView {
            public Builder(MyReleaseActivity myReleaseActivity, OnTimeSelectListener onTimeSelectListener) {
                super();
            }

            *//*public javax.swing.AbstractButton setType(boolean[] booleans) {
            }*//*
        }

        public abstract static class OnTimeSelectListener {
            public abstract void onTimeSelect(Date date, View v);
        }
    }*/
}
