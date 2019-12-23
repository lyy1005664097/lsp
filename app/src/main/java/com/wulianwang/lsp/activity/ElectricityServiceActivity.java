package com.wulianwang.lsp.activity;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 沈志宏、任满意    2.1
 */
public class ElectricityServiceActivity extends BaseActivity implements OnRefreshLoadMoreListener {

    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    RecyclerAdapter<Map<String, String>> adapter;
    LinearLayoutManager linearLayoutManager;

    private int page = 1;
    private List<Map<String, String>> initDta;
    private List<Map<String, String>> list = new ArrayList<>();

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

    @Override
    public void initView() {
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
        initData();
        refreshLayout.finishLoadMore(true);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 1;
        initData();
        refreshLayout.finishRefresh(true);
    }
}

