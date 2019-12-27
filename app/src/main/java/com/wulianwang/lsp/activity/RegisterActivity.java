package com.wulianwang.lsp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wulianwang.lsp.R;
import com.wulianwang.lsp.util.HttpUtil;
import com.wulianwang.lsp.util.Url;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 陈关奇、侯璐鑫 1.4
 */
public class RegisterActivity extends BaseActivity {
    private TextView bt2, bt3, bt4;
    private Button btn;

    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        setActionBar(true, "注册");
    }

    @Override
    public void initView() {
        EditText et1 = (EditText) findViewById(R.id.editText);
        EditText et2 = (EditText) findViewById(R.id.editText2);
        EditText et3 = (EditText) findViewById(R.id.editText3);
        EditText et4 = (EditText) findViewById(R.id.editText4);
        EditText et5 = (EditText) findViewById(R.id.editText5);

        btn = (Button) findViewById(R.id.button);//id后面为上方button的id
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册
                String nickname = et1.getText().toString();
                String phone = et2.getText().toString();
                String password = et3.getText().toString();//第一次输入的密码赋值给password
                String password2 = et4.getText().toString();//第二次输入的密码赋值给password2
                String email = et5.getText().toString();
                if(phone.equals("")){
                    Toast.makeText(getApplicationContext(),"手机号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.equals("")||password2.equals("")){	//判断两次密码是否为空
                    Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(password.equals(password2)){
                    Map<String, String> map = new HashMap<>();
                    map.put("phone", phone);
                    map.put("password", password);
                    map.put("nickname", nickname);
                    map.put("email", email);
                    HttpUtil.post(Url.adduser, null, new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            String responseData = response.body().string();
                            try {
                                JSONObject jsonObject = new JSONObject(responseData);
                                if(jsonObject.getInt("code") == 100){
                                    runOnUiThread(() -> {
                                        Toast.makeText(getApplication(),"注册成功",Toast.LENGTH_SHORT).show();
                                        finish();
                                    });
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, null, map);
                }else if (password.equals("") != password2.equals("")){
                    Toast.makeText(getApplication(),"密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                }

            }
        });

        bt2 = findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录
                finish();
            }
        });

        bt3 = findViewById(R.id.button4);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, PravcyActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });
        bt4 = findViewById(R.id.button9);//id后面为上方button的id
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, ServiceActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });

        checkBox = (CheckBox) super.findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btn.setEnabled(true);
                } else {
                    btn.setEnabled(false);
                }
            }
        });
    }
}








