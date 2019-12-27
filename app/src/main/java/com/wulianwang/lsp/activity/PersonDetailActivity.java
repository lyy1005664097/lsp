package com.wulianwang.lsp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wulianwang.lsp.R;
import com.wulianwang.lsp.bean.User;
import com.wulianwang.lsp.util.HttpUtil;
import com.wulianwang.lsp.util.Url;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 4.2 人员详情 王艺江 皂辉杰
 */
public class PersonDetailActivity extends BaseActivity {

    private TextView name;
    private TextView resume;
    private TextView work;
    private TextView location;
    private TextView description;
    private TextView score;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);

        initData();
        initView();
        setActionBar(true, "人员详情", R.drawable.ic_phone, (View view)->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + user.getPhone()));
            startActivity(intent);
        });
    }

    @Override
    public void initData() {
        String id = getIntent().getStringExtra("id");
        String url = Url.getUserByID + "?id=" + id;
        HttpUtil.get(url, new Callback() {
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
                        JSONObject jsonUser = jsonObject.getJSONObject("extend").getJSONObject("list");
                        user = new User();
                        user.setId(jsonUser.getInt("id"));
                        user.setUserName(jsonUser.getString("username"));
                        user.setPassword(jsonUser.getString("password"));
                        user.setPhone(jsonUser.getString("phone"));
                        user.setEmail(jsonUser.getString("email"));
                        user.setNickName(jsonUser.getString("nickname"));
                        user.setSex(jsonUser.getString("sex"));
                        user.setAge(jsonUser.getInt("age"));
                        user.setHeadImg(jsonUser.getString("headImg"));
                        user.setProfession(jsonUser.getString("profession"));
                        user.setRealName(jsonUser.getString("realName"));
                        user.setRealEnterprise(jsonUser.getString("realEnterprise"));
                        user.setRealElectro(jsonUser.getString("realElectro"));
                        user.setRealOccupation(jsonUser.getString("realOccupation"));
                        user.setBeginTime(jsonUser.getString("beginTime"));
                        user.setUpdateTime(jsonUser.getString("updateTime"));

                        runOnUiThread(()->{
                            name.setText(user.getNickName());
                            resume.setText(user.getAge() + "岁");
                            work.setText(user.getProfession());
                            location.setText("");
                            description.setText("");
                            score.setText("");
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void initView() {
        name = (TextView)this.findViewById(R.id.name);
        resume = (TextView)this.findViewById(R.id.resume);
        work = (TextView)this.findViewById(R.id.work);
        location = (TextView)this.findViewById(R.id.location);
        description = (TextView)this.findViewById(R.id.description);
        score = (TextView)this.findViewById(R.id.score);
        ImageView message = (ImageView)this.findViewById(R.id.message);

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:" + user.getPhone()));
                startActivity(intent2);
            }
        });
        /*String str = "彭于晏";
        name.setText(str);
        String str1 = "43岁";
        resume.setText(str1);
        String str2 = "水电工、建筑工";
        work.setText(str2);
        String str3 = "信阳市平桥区";
        location.setText(str3);
        String str4 = "综合得分:" + "      95";
        score.setText(str4);
        String str5 = "电子主要介绍的基本概念、基本定律及分析方法电路的暂态分析;单相正弦交流电路;" +
                "半导体基础;晶体管及;集成运算放大器及应用;数字逻辑电路基础;逻辑代数与逻辑函数\n";
        description.setText(str5);*/
    }
}

