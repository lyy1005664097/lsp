package com.wulianwang.lsp.activity.my;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;


public class BFragment extends Fragment {
    private  Context mcontext ;
    private ListView list;
    private List<data> Data;
    Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        return view;


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mcontext=getActivity();
        list = (ListView)view.findViewById(R.id.listview);
        Data=new LinkedList<data>();

        Data.add(new data("更换灯泡","信阳市平桥区伍号公馆\\n12#140更换灯泡","处理中"));
        Data.add(new data("更换灯泡","信阳市平桥区陆号公馆\\n12#319更换灯泡","处理中"));
        Data.add(new data("更换灯泡","信阳市平桥区伍号公馆\\n12#140更换灯泡","处理中"));
        Data.add(new data("更换灯泡","信阳市平桥区陆号公馆\\n12#319更换灯泡","处理中"));
        Data.add(new data("更换灯泡","信阳市平桥区伍号公馆\\n12#140更换灯泡","处理中"));
        Data.add(new data("更换灯泡","信阳市平桥区陆号公馆\\n12#319更换灯泡","处理中"));
        Data.add(new data("更换灯泡","信阳市平桥区伍号公馆\\n12#140更换灯泡","处理中"));
        Data.add(new data("更换灯泡","信阳市平桥区陆号公馆\\n12#319更换灯泡","处理中"));
        Data.add(new data("更换灯泡","信阳市平桥区伍号公馆\\n12#140更换灯泡","处理中"));
        Data.add(new data("更换灯泡","信阳市平桥区陆号公馆\\n12#319更换灯泡","处理中"));
        Data.add(new data("更换灯泡","信阳市平桥区伍号公馆\\n12#140更换灯泡","处理中"));
        Data.add(new data("更换灯泡","信阳市平桥区陆号公馆\\n12#319更换灯泡","处理中"));
        Data.add(new data("更换灯泡","信阳市平桥区伍号公馆\\n12#140更换灯泡","处理中"));
        Data.add(new data("更换灯泡","信阳市平桥区陆号公馆\\n12#319更换灯泡","处理中"));
        Data.add(new data("更换灯泡","信阳市平桥区伍号公馆\\n12#140更换灯泡","处理中"));
        Data.add(new data("更换灯泡","信阳市平桥区陆号公馆\\n12#319更换灯泡","处理中"));


        adapter=new Adapter((LinkedList<data>) Data,getActivity());



        list.setAdapter(adapter);

    }


}
