package com.wulianwang.lsp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter<T> extends RecyclerView.Adapter {

    private List<T> list;
    private int resId;
    private OnCreateItemListener<T> listener;

    public RecyclerAdapter(List<T> list, int resId, OnCreateItemListener<T> listener){
        this.list = list;
        this.resId = resId;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false)){
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        T data = list.get(position);
        listener.onCreateItem(holder, data, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<T> getList(){
        return list;
    }

    public void setList(List<T> list){
        this.list = list;
    }

    public interface OnCreateItemListener<T>{
        void onCreateItem(RecyclerView.ViewHolder holder, T data, int position);
    }
}
