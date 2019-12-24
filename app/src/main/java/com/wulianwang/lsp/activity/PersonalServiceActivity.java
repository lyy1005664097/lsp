package com.wulianwang.lsp.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wulianwang.lsp.R;
import com.wulianwang.lsp.adapter.RecyclerAdapter;
import com.wulianwang.lsp.bean.Itemview;

import java.util.ArrayList;
import java.util.List;

/**
 * 李鑫鑫 李永祥 3.2
 */
public class PersonalServiceActivity extends BaseActivity {
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<Itemview> items = new ArrayList<>();
    private List<Itemview> initItems = new ArrayList<>();
    RecyclerAdapter<Itemview> adapter;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_service);

        initData();
        initView();
        setActionBar(true, "个人服务");
    }

    @Override
    public void initData() {
        initItems.clear();
        //模拟网络请求获取数据
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i = 10*(page-1); i < 10*page; i++){
                    Itemview itemview = new Itemview("19:30", "更换电灯泡", "信阳市平桥区五号公馆12#1401更换灯泡");
                    initItems.add(itemview);
                }

                if(page == 1){
                    items = initItems;
                }else {
                    items.addAll(initItems);
                }
                adapter.setList(items);
                adapter.notifyDataSetChanged();
            }
        }, 200);
    }

    @Override
    public void initView() {
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initData();
                refreshLayout.finishRefresh();
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);

        adapter = new RecyclerAdapter<Itemview>(items, R.layout.layout, (RecyclerView.ViewHolder holder, Itemview data, int position) -> {
            TextView text_time = (TextView) holder.itemView.findViewById(R.id.text_time);
            TextView text_requet = (TextView) holder.itemView.findViewById(R.id.textrequest);
            TextView text_content = (TextView)holder.itemView.findViewById(R.id.contenttext);
            text_time.setText(data.time);
            text_requet.setText(data.itmename);
            text_content.setText(data.content);
            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(PersonalServiceActivity.this, PersonalServiceDetailActivity.class);
                startActivity(intent);
            });
        });

        recyclerView.setAdapter(adapter);
    }
}
