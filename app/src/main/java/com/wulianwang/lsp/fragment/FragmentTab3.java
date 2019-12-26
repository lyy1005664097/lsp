package com.wulianwang.lsp.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wulianwang.lsp.R;
import com.wulianwang.lsp.activity.HistoryWorkActivity;
import com.wulianwang.lsp.activity.TaskDetailActivity;
import com.wulianwang.lsp.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 5.12.3 已完成（fragment)
 * 成员：方晨,常文洁
 *
   */

public class FragmentTab3 extends Fragment {

    private List<String> stringList;

    SmartRefreshLayout refreshLayout;
    RecyclerView recyclerView;

//    private List<Map<String, String>> initDta;
    private List<String> list = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    RecyclerAdapter<String> adapter;
    int page = 1;

    private void initData() {
        stringList = new ArrayList<String>();

        new Handler().postDelayed(() -> {
            for (int i = 0; i < 10; i++) {
                stringList.add(String.valueOf(i));
            }
            if(page ==1){
                list = stringList;
            }else {
                list.addAll(stringList);
            }
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }, 200);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tab_3,container,false);
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

        adapter = new RecyclerAdapter<String>(list, android.R.layout.simple_list_item_1, (RecyclerView.ViewHolder holder, String data, int position) -> {
            TextView textView1 = (TextView) holder.itemView.findViewById(android.R.id.text1);
            textView1.setText(data);

            holder.itemView.setOnClickListener(view1 -> {
                Toast.makeText(getActivity(), "你点击了第" + position + "项", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), HistoryWorkActivity.class);
                intent.putExtra("key", position);
                intent.putExtra("key2", "xxx");
                startActivity(intent);
            });
        });
        recyclerView.setAdapter(adapter);
        return view;
    }

}
