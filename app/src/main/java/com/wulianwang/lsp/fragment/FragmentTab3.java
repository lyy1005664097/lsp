package com.wulianwang.lsp.fragment;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.wulianwang.lsp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 5.12.3 已完成（fragment)
 * 成员：方晨,常文洁
 *
   */

public class FragmentTab3 extends Fragment {


    ListView lv;
    private List<String> stringList;
    private ArrayAdapter lvAdapter;


    private void initData() {
        stringList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            stringList.add(String.valueOf(i));
        }
        lvAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, stringList);
        lv.setAdapter(lvAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), stringList.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"long click:"+stringList.get(i).toString(),Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tab_3,container,false);
        lv = view.findViewById(R.id.lv);
        initData();
        return view;
    }

}
