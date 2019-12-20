package com.wulianwang.lsp.adapter;


/**
 *     成员：刘长恩 曹彬
 *     3.5 企业服务
 *     下拉框，不显示时间，所有企业未接工单，分页显示
 */


import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wulianwang.lsp.R;
import com.wulianwang.lsp.bean.MyList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyListAdapter extends BaseAdapter {
    private Context mContext;
    private List<MyList> gridlist;
    private LayoutInflater mLayoutInflater;





    String[] urlImage=new String[]{"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=750445817,2012264218&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=919506936,3549572557&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4241911205,1022235024&fm=26&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576815855011&di=6ffa76e3aa12ded377c2a86e9c84b5c2&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20130816%2FImg384289518.jpg",
            "http://i0.qhimg.com/t0173035adcbbd320d6.jpg",
            "http://club1.autoimg.cn/album/userphotos/2014/11/07/06/400_22186700-6419-gnc6-g73l-n202a666bh22.jpg",
            "https://icweiliimg1.pstatp.com/weili/bl/254652321908391962.jpg"};
    String[] addressList=new String[]{"信阳市平桥区五开路开辟小路","信阳市浉河区","信阳市浉河区变电站","信阳市伍号公馆新建变压器",
            "信阳市平桥区文博路","信阳市羊山区解放路","信阳市平桥区明港机场"};
    String[] TimePos=new String[]{"9:20","9:30","10:00","11:00","15:00","15:30","16:00"};
    static class  ViewHolder {
        public ImageView imageView;
        TextView tvTile, tvTime, tvContent;
    }
    public MyListAdapter(Context context){
        super();
        this.mContext =context;
    }
    public void  setList(List<MyList> gridlist){
        this.gridlist=gridlist;
        mLayoutInflater =(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return gridlist.size();
    }


    @Override
    public Object getItem(int position) {
        return gridlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder =null;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.layout_list_item,null);
            holder =new ViewHolder();
            holder.imageView =(ImageView)convertView.findViewById(R.id.iv);

            holder.tvTile=(TextView)convertView.findViewById(R.id.tv_title);
            holder.tvTime=(TextView)convertView.findViewById(R.id.tv_Time);
            holder.tvContent=(TextView)convertView.findViewById(R.id.tv_Content);
            convertView.setTag(holder);
        }
        else{
            holder =(ViewHolder)convertView.getTag();
        }
        MyList grid =gridlist.get(position);
        if(grid!=null){
                String StrImage=urlImage[position];
                String StrAddress=addressList[position];
                String StrTime=TimePos[position];
                holder.tvTile.setText(grid.getObjname());
                Glide.with(mContext).load(StrImage).into(holder.imageView);
                holder.tvContent.setText(StrAddress);
                holder.tvTime.setText(StrTime);
        }
        else{
        }
        return convertView;
    }
}
