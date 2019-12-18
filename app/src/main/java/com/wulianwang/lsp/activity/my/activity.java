package com.wulianwang.lsp.activity.my;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wulianwang.lsp.R;

import java.util.List;

public class activity extends AppCompatActivity {
    private AFragment mAFragment;
    private BFragment mBFragment;
    private CFragment mCFragment;
    private TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();



        mAFragment =new AFragment();
        getFragmentManager().beginTransaction().add(R.id.frameLayout,mAFragment).commitAllowingStateLoss();
        t1=(TextView)findViewById(R.id.textView14);
        t1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mBFragment==null){
                    mBFragment=new BFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frameLayout,mBFragment).commitAllowingStateLoss();
                }
            }
        });





    }
}
