package com.wulianwang.lsp.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wulianwang.lsp.R;
import com.wulianwang.lsp.activity.PeopleListActivity;
import com.wulianwang.lsp.activity.TaskDetailActivity;
import com.wulianwang.lsp.adapter.AnimalAdapter;
import com.wulianwang.lsp.adapter.MyListAdapter;
import com.wulianwang.lsp.bean.Animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 谭敏，孙彦婷
 * 5.12.1 未接（fragment)
 * （不显示时间），点击任务，显示未接任务详情
 */


public class FragmentTab1 extends Fragment  implements AdapterView.OnItemClickListener {

    private List<Animal> mData = null;
    private Context mContext;
    private AnimalAdapter mAdapter = null;
    private ListView list_animal;
    SmartRefreshLayout refreshLayout;
    RecyclerView recyclerView;

    private List<Map<String, String>> initDta;
    private List<Map<String, String>> list;
    private LinearLayoutManager linearLayoutManager;
    //MyListAdapter adapter;
    MyListAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_1, container, false);

        initData();
        refreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                list.addAll(initDta);
                adapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore(true);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                list = initDta;
                adapter.notifyDataSetChanged();
                refreshLayout.finishRefresh(true);
            }
        });

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        list = initDta;
        adapter = new MyListAdapter(list, R.layout.person_item);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mContext, "你点击了第" + position + "项", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), TaskDetailActivity.class);
        intent.putExtra("key", position);
        intent.putExtra("key2", "xxx");
        startActivity(intent);
    }


    private void initData() {
        initDta = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("title", "title" + i);
            map.put("data", "data" + i);
            map.put("data2", "data2" + i);
            initDta.add(map);
        }
    }

    static class MyListAdapter<T> extends RecyclerView.Adapter {

        List<T> list;
        int resId;

        MyListAdapter(List<T> list, int resId) {
            this.list = list;
            this.resId = resId;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false)) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Map<String, String> map = (Map<String, String>) list.get(position);
            TextView textView1 = (TextView) holder.itemView.findViewById(R.id.textView);
            TextView textView2 = (TextView) holder.itemView.findViewById(R.id.textView2);
            TextView textView4 = (TextView) holder.itemView.findViewById(R.id.textView4);
            textView1.setText(map.get("title"));
            textView2.setText(map.get("data"));
            textView4.setText(map.get("data2"));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        void refresh(List<T> list) {
            this.list.clear();
            this.loadMore(list);
        }

        void loadMore(List<T> list) {
            this.list.addAll(list);
            this.notifyDataSetChanged();
        }
    }

}
