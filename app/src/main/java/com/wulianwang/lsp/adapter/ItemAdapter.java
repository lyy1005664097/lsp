package com.wulianwang.lsp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wulianwang.lsp.R;
import com.wulianwang.lsp.bean.Itemview;

import java.util.LinkedList;

/**
 * 李鑫鑫 李永祥 3.2
 */
public class ItemAdapter extends BaseAdapter {
    private LinkedList<Itemview> mData;
    private Context mContext;

    public ItemAdapter(LinkedList<Itemview> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.layout,parent,false);

        TextView text_time = (TextView) convertView.findViewById(R.id.text_time);
        TextView text_requet = (TextView) convertView.findViewById(R.id.textrequest);
        TextView text_content = (TextView)convertView.findViewById(R.id.contenttext);
        text_time.setText(mData.get(position).time);
        text_requet.setText(mData.get(position).itmename);
        text_content.setText(mData.get(position).content);
        return convertView;
    }
}
