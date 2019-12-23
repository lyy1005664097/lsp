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
public class PeopleListActivity extends BaseActivity {
    String[] stringArray;
    Spinner sp;
    SmartRefreshLayout refreshLayout;
    RecyclerView recyclerView;

    private List<Map<String, String>> initDta;
    private List<Map<String, String>> list = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter adapter;

    private boolean isRefresh = true;

    private int page = 1;

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
        String url = "http://123.7.17.91:7777/getUserList?pageNo=" + page + "&pageSize=10";

        //模拟网络请求数据
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("Handle", "" + page);
                for(int i = 10*(page-1); i < 10*page; i++){
                    Map<String, String> map = new HashMap<>();
                    map.put("title", "信阳" + i);
                    map.put("data", "2019-12-15" + i);
                    map.put("data2", "城东街道" + i);
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


         /*HttpUtil.get(url, new Callback() {
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
                     if(jsonObject.getInt("code") == 100){
                         JSONArray jsonArray = jsonObject.getJSONObject("extend").getJSONObject("list").getJSONArray("records");
                         for (int i = 0 ; i<jsonArray.length();i++){

                             Map<String, String> map = new HashMap<>();
                             map.put("title", jsonArray.getJSONObject(i).getString("nickname"));
                             map.put("data", jsonArray.getJSONObject(i).getString("profession"));
                             map.put("data2",  jsonArray.getJSONObject(i).getString("email"));
                             initDta.add(map);
                         }

                         if(page == 1){
                             list = initDta;
                         }else {
                             list.addAll(initDta);
                         }
                         adapter.setList(list);
                         adapter.notifyDataSetChanged();
                     }


                    *//* runOnUiThread(new Runnable() {
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
                      //       adapter = new MyListAdapter(list, R.layout.person_item);
                      //       recyclerView.setAdapter(adapter);
                         }
                     });
*//*

                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }
         });*/
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

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter<Map<String, String>>(list, R.layout.person_item, (RecyclerView.ViewHolder holder, Map<String, String> data, int position) ->{
            LinearLayout item = holder.itemView.findViewById(R.id.item);
            TextView textView1 = (TextView) holder.itemView.findViewById(R.id.textView);
            TextView textView2 = (TextView) holder.itemView.findViewById(R.id.textView2);
            TextView textView4 = (TextView) holder.itemView.findViewById(R.id.textView4);
            textView1.setText(data.get("title"));
            textView2.setText(data.get("data"));
            textView4.setText(data.get("data2"));
            item.setOnClickListener((View view)->{
                Intent intent = new Intent(PeopleListActivity.this, PersonDetailActivity.class);
                startActivity(intent);
            });
        });
        recyclerView.setAdapter(adapter);
    }
}
