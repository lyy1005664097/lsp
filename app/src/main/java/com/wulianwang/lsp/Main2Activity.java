package com.wulianwang.lsp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Spinner sp;

        sp = (Spinner) this.findViewById(R.id.spinner);
        String[] str = {"请选择职业类型", "水电工", "水工", "外卖员", "建筑工"};
        int String;
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str);
        sp.setAdapter(ad);
    }
}
