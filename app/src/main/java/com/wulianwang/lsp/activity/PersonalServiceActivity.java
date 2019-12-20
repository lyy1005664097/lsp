package com.wulianwang.lsp.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wulianwang.lsp.R;
import com.wulianwang.lsp.adapter.ItemAdapter;
import com.wulianwang.lsp.bean.Itemview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 李鑫鑫 李永祥 3.2
 */
public class PersonalServiceActivity extends AppCompatActivity {
    private ListView list_animal;
    private LinkedList<Itemview>itemalls = new  LinkedList<Itemview>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_service);

        getSupportActionBar().hide();

        Itemview itemview = new Itemview("19:30", "更换电灯泡", "信阳市平桥区五号公馆12#1401更换灯泡");
        Itemview itemview1 = new Itemview("20:30", "更换电灯泡", "信阳市平桥区五号公馆12#1401更换灯泡");
        Itemview itemview2 = new Itemview("22:30", "更换电灯泡", "信阳市平桥区五号公馆12#1401更换灯泡");
        Itemview itemview3= new Itemview("9:30", "更换电灯泡", "信阳市平桥区五号公馆12#1401更换灯泡");
        itemalls.add(itemview);
        itemalls.add(itemview1);
        itemalls.add(itemview2);
        itemalls.add(itemview3);

        list_animal = findViewById(R.id.listviewperson);

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemalls);
        ItemAdapter adapter = new ItemAdapter( itemalls, PersonalServiceActivity.this);

        list_animal.setAdapter(adapter);

        list_animal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(PersonalServiceActivity.this, PersonalServiceDetailActivity.class);
                startActivity(intent);
            }

        });
    }





}
