package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wulianwang.lsp.Main22Activity;
import com.wulianwang.lsp.Main2Activity;
import com.wulianwang.lsp.Main3Activity;
import com.wulianwang.lsp.Main4Activity;
import com.wulianwang.lsp.Main5Activity;
import com.wulianwang.lsp.Main6Activity;
import com.wulianwang.lsp.R;

public class MainActivity extends AppCompatActivity {
private Button bt;
    private Button btn = null;

    private CheckBox checkBox = null;

    //public MainActivity(Button button1) {
     //   this.button1 = button1;
   // }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);



        bt = (Button) findViewById(R.id.button1);//id后面为上方button的id

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main3Activity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);


            }
        });



        bt = (Button) findViewById(R.id.button);//id后面为上方button的id
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main2Activity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });

        bt = (Button) findViewById(R.id.button4);//id后面为上方button的id
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main4Activity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });
        bt = (Button) findViewById(R.id.button9);//id后面为上方button的id
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main5Activity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });

        bt = (Button) findViewById(R.id.button2);//id后面为上方button的id
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main6Activity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });

        this.btn = (Button) super.findViewById(R.id.button);
        this.checkBox = (CheckBox) super.findViewById(R.id.checkBox);

        this.btn.setClickable(false);

// 设置CheckBox默认不选中

 this.checkBox.setFocusable(false);

this.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {



         @Override

 public void onCheckedChanged(CompoundButton buttonView,

 boolean isChecked) {

 if (isChecked == true) {

 btn.setClickable(true);

 } else {

 btn.setClickable(false);

 }

 }

 });
               EditText et = (EditText) findViewById(R.id.editText2);
        et.setInputType(InputType.TYPE_CLASS_PHONE);
        EditText etc = (EditText) findViewById(R.id.editText5);
        etc.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        EditText etcc = (EditText) findViewById(R.id.editText5);
        etcc.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);



        bt.setOnClickListener(new View.OnClickListener() {	//给注册按钮设置监听


            EditText editText=(EditText)findViewById(R.id.editText3);
            EditText editTextt=(EditText)findViewById(R.id.editText4);

            public void onClick(View v) {
                String password=editText.getText().toString();//第一次输入的密码赋值给password
                String password2=editTextt.getText().toString();//第二次输入的密码赋值给password2

                if (password.equals("")||password2.equals("")){	//判断两次密码是否为空
                    Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(password.equals(password2)){
                    Toast.makeText(getApplication(),"注册成功",Toast.LENGTH_SHORT).show();

                    //Intent intent=new Intent(getApplicationContext(),Main6Activity.class);
                    //intent.putExtra(    );//可以填入用户信息，如ID等
                    //startActivity(intent);
                    finish();


                }else if (password.equals("") != password2.equals("")){
                    Toast.makeText(getApplication(),"密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                }

            }
        });







    }
        }








