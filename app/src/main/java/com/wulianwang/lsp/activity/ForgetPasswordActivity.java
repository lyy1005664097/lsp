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
 * 张栋凯 曹小康 1-3
 */
public class ForgetPasswordActivity extends BaseActivity {

    private Button mybtn2;
    private  EditText editText1;
    private  EditText editText2;
    private EditText editText;
    String psaaword0;
    String password1;
    String password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        initView();
        setActionBar(true, "找回密码");
    }

    @Override
    public void initView() {
        editText = (EditText) findViewById(R.id.et_password0);
        editText1 = (EditText) findViewById(R.id.et_password1);
        editText2 = (EditText) findViewById(R.id.et_password2);
        mybtn2 = (Button) findViewById(R.id.bt2);
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
                }else{
                //    Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
                    //把editext里面的密码上传到数据库
                    Map<String, String> map = new HashMap<>();
                    map.put("phone", psaaword0);
                    map.put("password", password1);
                    HttpUtil.post(Url.updatePwd, null, new Callback() {
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
                                        Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    });
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, null, map);
                }
            }
        });
    }
}



