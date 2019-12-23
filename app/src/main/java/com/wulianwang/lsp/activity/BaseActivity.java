package com.wulianwang.lsp.activity;

import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.wulianwang.lsp.R;

public class BaseActivity extends AppCompatActivity {

    ActionBar actionBar;
    boolean isShowBackKey;

    public void initView(){

    }
    public void initData(){

    }

    public void setActionBar(boolean isShowBackKey, String title){
        this.isShowBackKey = isShowBackKey;

        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        View actionView = LayoutInflater.from(this).inflate(R.layout.action_view, null);
        ImageView imageView = actionView.findViewById(R.id.home);
        TextView textView = actionView.findViewById(R.id.title);
        textView.setText(title);
        if(isShowBackKey){
            imageView.setVisibility(View.VISIBLE);
        }else{
            imageView.setVisibility(View.GONE);
        }
        actionBar = getSupportActionBar();
        actionBar.setCustomView(actionView, layoutParams);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home && isShowBackKey){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
