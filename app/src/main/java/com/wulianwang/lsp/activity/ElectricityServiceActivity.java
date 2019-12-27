package com.wulianwang.lsp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wulianwang.lsp.R;
import com.wulianwang.lsp.adapter.RecyclerAdapter;
import com.wulianwang.lsp.bean.User;
import com.wulianwang.lsp.util.HttpUtil;
import com.wulianwang.lsp.util.SharedPrefsUtil;
import com.wulianwang.lsp.util.Url;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 沈志宏、任满意    2.1
 */
public class ElectricityServiceActivity extends BaseActivity implements OnRefreshLoadMoreListener {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;

    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    RecyclerAdapter<Map<String, String>> adapter;
    LinearLayoutManager linearLayoutManager;

    private int page = 1;
    private List<Map<String, String>> initDta;
    private List<Map<String, String>> list = new ArrayList<>();

    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_service);

        initData();
        initView();
        setActionBar(true, "电力服务");
    }

    @Override
    public void initData() {
        User user = SharedPrefsUtil.getValue(this, "user", (User)null);
        isLogin = (user != null);
        if(isLogin){
            getelectric(user.getPhone());
        }
        getData();
    }

    @Override
    public void initView() {
        textView1 = findViewById(R.id.textView400);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView15);
        textView4 = findViewById(R.id.textView14);
        textView5 = findViewById(R.id.textView13);
        if(!isLogin){
            textView1.setText("登录/注册");
            textView2.setText("");
            textView3.setText("0");
            textView4.setText("0");
            textView5.setText("0");
            textView1.setOnClickListener(view -> {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            });
        }
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshLoadMoreListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter<Map<String, String>>(list, R.layout.elect_service_item, new RecyclerAdapter.OnCreateItemListener<Map<String, String>>() {
            @Override
            public void onCreateItem(RecyclerView.ViewHolder holder, Map<String, String> data, int position) {
                Log.d("onCreateItem", "position:" + position);
                TextView textView1 = (TextView) holder.itemView.findViewById(R.id.site);
                TextView textView2 = (TextView) holder.itemView.findViewById(R.id.time);
                TextView textView3 = (TextView) holder.itemView.findViewById(R.id.area);
                textView1.setText(data.get("site"));
                textView2.setText(data.get("time"));
                textView3.setText(data.get("area"));
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        getData();
        refreshLayout.finishLoadMore(true);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 1;
        getData();
        refreshLayout.finishRefresh(true);
    }

    private void getelectric(String phone){
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        HttpUtil.post(Url.getelectric, null, new Callback() {
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
                        JSONObject json = jsonObject.getJSONObject("extend");
                        String yesterday = json.getString("yesterday");
                        String surplus = json.getString("surplus");
                        String lastMonth = json.getString("lastMonth");
                        String name = json.getString("name");
                        runOnUiThread(()->{
                            textView1.setText(name);
                            textView3.setText(lastMonth);
                            textView4.setText(surplus);
                            textView5.setText(yesterday);
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, null, map);
    }

    private void getData(){
        initDta = new ArrayList<>();

        //模拟网络请求数据
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("Handle", "" + page);
                for(int i = 10*(page-1); i < 10*page; i++){
                    Map<String, String> map = new HashMap<>();
                    map.put("site", "信阳" + i);
                    map.put("time", "2019-12-15 08:30:00 - 2019-12-15 18:00:00" + i);
                    map.put("area", "城东街道" + i);
                    initDta.add(map);
                }

                if(page == 1){
                    list = initDta;
                }else{
                    list.addAll(initDta);
                }
                adapter.setList(list);
                adapter.notifyDataSetChanged();
            }
        }, 200);
    }
}

