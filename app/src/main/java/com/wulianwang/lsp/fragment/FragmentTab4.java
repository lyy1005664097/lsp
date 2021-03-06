package com.wulianwang.lsp.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wulianwang.lsp.R;
import com.wulianwang.lsp.activity.HandlingDetailActivity;
import com.wulianwang.lsp.activity.TaskDetailActivity;
import com.wulianwang.lsp.adapter.RecyclerAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  5.16.1 处理中（fragment)
 *  点击任务，进入处理中任务详情 14.1
 *  人员：蒋志帆、董云飞
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTab4 extends Fragment {

    SmartRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    private List<Map<String, String>> initDta;
    private List<Map<String, String>> list = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    RecyclerAdapter<Map<String, String>> adapter;
    int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_tab_4, container, false);

        initData();
        refreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
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
        });

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter<Map<String, String>>(list, R.layout.person_item, (RecyclerView.ViewHolder holder, Map<String, String> data, int position) -> {
            TextView textView1 = (TextView) holder.itemView.findViewById(R.id.textView);
            TextView textView2 = (TextView) holder.itemView.findViewById(R.id.textView2);
            TextView textView4 = (TextView) holder.itemView.findViewById(R.id.textView4);
            textView1.setText(data.get("title"));
            textView2.setText(data.get("data"));
            textView4.setText(data.get("data2"));

            holder.itemView.setOnClickListener(view1 -> {
                Toast.makeText(getActivity(), "你点击了第" + position + "项", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), HandlingDetailActivity.class);
                intent.putExtra("key", position);
                intent.putExtra("key2", "xxx");
                startActivity(intent);
            });
        });
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initData() {
        initDta = new ArrayList<>();

        //模拟网络请求
        new Handler().postDelayed(() -> {
            for (int i = 0; i < 10; i++) {
                Map<String, String> map = new HashMap<>();
                map.put("title", "title" + i);
                map.put("data", "data" + i);
                map.put("data2", "data2" + i);
                initDta.add(map);
            }

            if(page ==1){
                list = initDta;
            }else {
                list.addAll(initDta);
            }
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }, 200);
    }
}
