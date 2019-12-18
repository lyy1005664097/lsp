package com.wulianwang.lsp.activity.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

public class Adapter extends BaseAdapter {

    private LinkedList<data> Data;
    private Context mcontext ;
    public Adapter(LinkedList<data> data ,Context mcontext){
        this.Data=data;
        this.mcontext=mcontext;
    }

    @Override
    public int getCount() {
        return Data.size();
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
        convertView = LayoutInflater.from(mcontext).inflate(R.layout.item,parent,false);

        TextView q=(TextView)convertView.findViewById(R.id.textView1);
        TextView a=(TextView)convertView.findViewById(R.id.textView2);
        a.setText(Data.get(position).getAddress());
        q.setText(Data.get(position).getQuestion());


        return convertView;

    }
}
