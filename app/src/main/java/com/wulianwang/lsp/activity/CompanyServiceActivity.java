package com.wulianwang.lsp.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wulianwang.lsp.R;
import com.wulianwang.lsp.adapter.RecyclerAdapter;
import com.wulianwang.lsp.bean.MyList;

import java.util.ArrayList;
import java.util.List;
/**
 *     成员：刘长恩 曹彬
 *     3.5 企业服务
 */
public class CompanyServiceActivity extends BaseActivity {
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private RecyclerAdapter<MyList> adapter;
    private List<MyList> mylist = new ArrayList<>();
    private List<MyList> initData = new ArrayList<>();
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_service);

        initData();
        initView();
        setActionBar(true, "企业服务");
    }

    @Override
    public void initData() {
        initData.clear();
        new Handler().postDelayed(() -> {
            initData.add(new MyList("修路",
                    "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=750445817,2012264218&fm=26&gp=0.jpg",
                    "信阳市平桥区五开路开辟小路", "9:20"));
            initData.add(new MyList("架桥", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=919506936,3549572557&fm=26&gp=0.jpg",
                    "信阳市浉河区", "9:30"));
            initData.add(new MyList("家变电站", "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4241911205,1022235024&fm=26&gp=0.jpg",
                    "信阳市浉河区变电站", "10:00"));
            initData.add(new MyList("装变压器", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576815855011&di=6ffa76e3aa12ded377c2a86e9c84b5c2&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20130816%2FImg384289518.jpg",
                    "信阳市伍号公馆新建变压器", "11:00"));
            initData.add(new MyList("修水电站", "http://i0.qhimg.com/t0173035adcbbd320d6.jpg",
                    "信阳市平桥区文博路", "15:00"));
            initData.add(new MyList("装发动机", "http://club1.autoimg.cn/album/userphotos/2014/11/07/06/400_22186700-6419-gnc6-g73l-n202a666bh22.jpg",
                    "信阳市羊山区解放路", "15:30"));
            initData.add(new MyList("维修发动机", "https://icweiliimg1.pstatp.com/weili/bl/254652321908391962.jpg",
                    "信阳市平桥区明港机场", "16:00"));
            if(page == 1){
                mylist = initData;
            }else {
                mylist.addAll(initData);
            }
            adapter.setList(mylist);
            adapter.notifyDataSetChanged();
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

        adapter = new RecyclerAdapter<MyList>(mylist, R.layout.layout_list_item, (RecyclerView.ViewHolder holder, MyList data, int position) ->{
            ImageView imageView = holder.itemView.findViewById(R.id.iv);
            TextView tvTile=holder.itemView.findViewById(R.id.tv_title);
            TextView tvContent=holder.itemView.findViewById(R.id.tv_Content);
            TextView tvTime=holder.itemView.findViewById(R.id.tv_Time);

            Glide.with(CompanyServiceActivity.this).load(data.getImageUrl()).into(imageView);
            tvTile.setText(data.getObjname());
            tvContent.setText(data.getAddress());
            tvTime.setText(data.getTime());

            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(CompanyServiceActivity.this, CompanyServiceDetailActivity.class);
                startActivity(intent);
            });
        });
        recyclerView.setAdapter(adapter);
    }
}
