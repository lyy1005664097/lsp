package com.wulianwang.lsp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wulianwang.lsp.R;
import com.wulianwang.lsp.adapter.RecyclerAdapter;
import com.wulianwang.lsp.bean.MyList;
import com.wulianwang.lsp.bean.User;
import com.wulianwang.lsp.util.HttpUtil;
import com.wulianwang.lsp.util.Url;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 魏行 梅克成 4.1
 */
public class PeopleListActivity extends BaseActivity {
    String[] stringArray;
    Spinner sp;
    SmartRefreshLayout refreshLayout;
    RecyclerView recyclerView;

    private List<User> initDta;
    private List<User> list = new ArrayList<>();
    private RecyclerAdapter<User> adapter;

    private int page = 1;
    private boolean noMore = false;
    private boolean isRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);

        initData();
        initView();
        setActionBar(true, "人员列表");
    }

    @Override
    public void initData(){
        initDta = new ArrayList<>();
        String url = Url.getUserList + "?pageNo=" + page + "&pageSize=10";

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
                        JSONArray jsonArray = jsonObject.getJSONObject("extend").getJSONObject("list").getJSONArray("records");
                        for(int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonUser = jsonArray.getJSONObject(i);
                            User user = new User();
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
                            initDta.add(user);
                        }
                        if(page == 1){
                            list = initDta;
                        }else{
                            list.addAll(initDta);
                        }
                        adapter.setList(list);
                        runOnUiThread(()->{
                            adapter.notifyDataSetChanged();
                            if(isRefresh){
                                if(initDta.size() < 10){
                                    refreshLayout.finishRefreshWithNoMoreData();
                                }else {
                                    refreshLayout.finishRefresh();
                                }
                            }else {
                                if(initDta.size() < 10){
                                    refreshLayout.finishLoadMoreWithNoMoreData();
                                }else {
                                    refreshLayout.finishLoadMore();
                                }
                            }
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
        stringArray = getResources().getStringArray(R.array.type);
        sp = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter<String> ada=new ArrayAdapter<String>(PeopleListActivity.this,android.R.layout.simple_list_item_1,stringArray);
        sp.setAdapter(ada);

        refreshLayout = (SmartRefreshLayout)findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
           //     if(!noMore) {
                isRefresh = false;
                    page++;
                    initData();
                //    refreshLayout.finishLoadMore();
            //    }else {
                //    refreshLayout.finishLoadMore();
            //    }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
                page = 1;
                initData();
           //    refreshLayout.finishRefresh();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter<User>(list, R.layout.person_item, (RecyclerView.ViewHolder holder, User data, int position) ->{
            TextView textView1 = (TextView) holder.itemView.findViewById(R.id.textView);
            TextView textView2 = (TextView) holder.itemView.findViewById(R.id.textView2);
            TextView textView4 = (TextView) holder.itemView.findViewById(R.id.textView4);
            textView1.setText(data.getNickName());
            textView2.setText(data.getPhone());
            textView4.setText("");
            holder.itemView.setOnClickListener((View view)->{
                Intent intent = new Intent(PeopleListActivity.this, PersonDetailActivity.class);
                intent.putExtra("id", String.valueOf(data.getId()));
                startActivity(intent);
            });
        });
        recyclerView.setAdapter(adapter);
    }
}
