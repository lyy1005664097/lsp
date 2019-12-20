package com.wulianwang.lsp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wulianwang.lsp.R;
import com.wulianwang.lsp.bean.MyList;
import com.wulianwang.lsp.util.HttpUtil;

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
public class PeopleListActivity extends AppCompatActivity {
    String[] StringArray=new String[]{"岗位","昵称"};
    Spinner sp;
    SmartRefreshLayout refreshLayout;
    RecyclerView recyclerView;

    private List<Map<String, String>> initDta;
    private List<Map<String, String>> list = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    MyListAdapter adapter;
    ImageButton im;

    private boolean isRefresh = true;

    private List<MyList> myListAdapter = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);
        sp = (Spinner)findViewById(R.id.spinner);


        ArrayAdapter<String> ada=new ArrayAdapter<String>(PeopleListActivity.this,android.R.layout.simple_list_item_1,StringArray);
        sp.setAdapter(ada);

        im=(ImageButton)findViewById(R.id.imageButton3);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });


    //    initData();
        refreshLayout = (SmartRefreshLayout)findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
             //   list.addAll(initDta);
            //    adapter.notifyDataSetChanged();
            //    refreshLayout.finishLoadMore(true);
                isRefresh = false;
                initData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            //    list = initDta;
            //    adapter.notifyDataSetChanged();
            //    refreshLayout.finishRefresh(true);
                isRefresh = true;
                initData();
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

     //   list = initDta;


        refreshLayout.autoRefresh();

    }


    private void initData(){
        String url = "http://123.7.17.91:7777/getUserList?pageNo=0&pageSize=2";

        /*initDta = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Map<String, String> map = new HashMap<>();
            map.put("title", "title" + i);
            map.put("data", "data" + i);
            map.put("data2", "data2" + i);
            initDta.add(map);
        }*/

        initDta = new ArrayList<>();
         HttpUtil.get(url, new Callback() {
             @Override
             public void onFailure(Call call, IOException e) {
             //请求失败后执行的代码
                 Log.d("------","=====");
             e.printStackTrace();
             }
             @Override
             public void onResponse(Call call, Response response) throws IOException {
                 //请求成功后执行的代码
                 String responseData = response.body().string();
                 Log.d("Login", responseData);
                 try {
                     JSONObject jsonObject = new JSONObject(responseData);
                     JSONArray jsonArray = jsonObject.getJSONObject("extend").getJSONObject("list").getJSONArray("records");
                     for (int i = 0 ; i<jsonArray.length();i++){

                         Map<String, String> map = new HashMap<>();
                         map.put("title", jsonArray.getJSONObject(i).getString("nickname"));
                         map.put("data", jsonArray.getJSONObject(i).getString("profession"));
                         map.put("data2",  jsonArray.getJSONObject(i).getString("email"));
                         initDta.add(map);

                     }

                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             if(isRefresh){
                                 list = initDta;
                            //     adapter.notifyDataSetChanged();
                                 refreshLayout.finishRefresh(true);
                             }else{
                                 list.addAll(initDta);
                             //    adapter.notifyDataSetChanged();
                                 refreshLayout.finishLoadMore(true);
                             }
                             adapter = new MyListAdapter(list, R.layout.person_item);
                             recyclerView.setAdapter(adapter);
                         }
                     });


                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }
         });
    }

    static class MyListAdapter<T> extends RecyclerView.Adapter {

        List<T> list;
        int resId;

        MyListAdapter(List<T> list, int resId){
            this.list = list;
            this.resId = resId;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false)){
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Map<String, String> map = (Map<String, String>)list.get(position);
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
