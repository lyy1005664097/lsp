package com.wulianwang.lsp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wulianwang.lsp.R;

/**
 * 张栋凯 曹小康 1-3
 */
public class ForgetPasswordActivity extends AppCompatActivity {

    private ImageView mybtn1;private Button mybtn2;
    private  EditText editText1;private  EditText editText2; private EditText editText;
    String psaaword0; String password1; String password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        editText = (EditText) findViewById(R.id.et_password0);
        editText1 = (EditText) findViewById(R.id.et_password1);
        editText2 = (EditText) findViewById(R.id.et_password2);
        mybtn1 = (ImageView) findViewById(R.id.bt1);
        mybtn2 = (Button) findViewById(R.id.bt2);
        mybtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {finish();

            }
        });
        mybtn2.setOnClickListener(new View.OnClickListener() { //给确认更改按钮设置监听
            @Override
            public void onClick(View v) {
                psaaword0=editText.getText().toString().trim();  //输入的手机号码
                password1=editText1.getText().toString().trim();  //第一次输入的密码
                password2=editText2.getText().toString().trim();  //第二次输入的密码

               if(password1.equals("")||password2.equals("")||psaaword0.equals("")){//判断两次密码是否为空
                   Toast.makeText(getApplicationContext(),"输入手机号码和密码不能为空",Toast.LENGTH_SHORT).show();
               }else if (!password1.equals(password2)){
                   Toast.makeText(getApplicationContext(),"密码输入不一致，请重新输入",Toast.LENGTH_SHORT).show();
               }else if (password1.equals(password2)){
                   Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
                    //把editext里面的密码上传到数据库
                   Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                   startActivity(intent);
                   finish();
               }
            }
        });



    }
}



