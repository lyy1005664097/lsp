package com.wulianwang.lsp.activity;

import android.app.DatePickerDialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.TextView;

import android.widget.Spinner;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import com.wulianwang.lsp.R;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * 王城 王光彪 5.7
 */
public class PersonPublishActivity extends BaseActivity {
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    TextView txt1;
    Button btn2,btn3,btn4 ,BtnTextView;

    String[] ctype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_publish);

        initData();
        initView();
        setActionBar(true, "个人发布");
    }

    @Override
    public void initData() {
        ctype = new String[]{"全部", "游戏", "电影", "娱乐", "图书"};
    }

    @Override
    public void initView() {
        btn2=(Button)findViewById(R.id.buttonoo2);
        btn2.setOnClickListener(new mClick());
        btn4=(Button)findViewById(R.id.button04);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // activity页面跳转
                Intent intent= new Intent(PersonPublishActivity.this,MapSearchActivity.class);
                startActivityForResult(intent, 100);
            }
        });
        BtnTextView =findViewById(R.id.button01);
        BtnTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // activity页面跳转
                //    Intent intent= new Intent(PersonPublishActivity.this,Main3Activity.class);

                //     startActivity(intent);
            }
        });
        txt1=(TextView)findViewById(R.id.text001);
        editText1 = findViewById(R.id.edit1);
        editText2 = findViewById(R.id.edit2);
        editText3 = findViewById(R.id.edit3);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ctype);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = super.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            if(resultCode == 100){
                editText3.setText(data.getStringExtra("address"));
            }
        }
    }

    class mClick implements View.OnClickListener{
        int m_year;
        int m_month;
        int m_day;

        public void onClick(View v){
            Calendar calendar = new GregorianCalendar();
            m_year = calendar.get(Calendar.YEAR);
            m_month = calendar.get(Calendar.MONTH);
            m_day = calendar.get(Calendar.DAY_OF_MONTH);
            if (v==btn2) {
                DatePickerDialog.OnDateSetListener dateListener=new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        m_year=year;
                        m_month=month;
                        m_day=dayOfMonth;
                        txt1.setText(m_year+"-"+m_month+"-"+m_day);
                    }
                };
                DatePickerDialog date=new DatePickerDialog(PersonPublishActivity.this,dateListener,m_year,m_month,m_day);
            //    date.setTitle("日期对话框");
                date.show();

            }
        }
    }

}








