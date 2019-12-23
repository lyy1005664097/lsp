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

    public void setActionBar(String title){
        setActionBar(false, title, 0, null);
    }

    public void setActionBar(boolean isShowBackKey, String title){
        setActionBar(isShowBackKey, title, 0, null);
    }

    public void setActionBar(boolean isShowBackKey, String title, int resId, View.OnClickListener listener){
        this.isShowBackKey = isShowBackKey;

        View actionView = LayoutInflater.from(this).inflate(R.layout.action_view, null);
        ImageView imageView = actionView.findViewById(R.id.home);
        TextView textView = actionView.findViewById(R.id.title);
        ImageView action = actionView.findViewById(R.id.action);
        if(resId != 0){
            action.setImageResource(resId);
            action.setOnClickListener(listener);
        }
        textView.setText(title);
        if(isShowBackKey){
            imageView.setVisibility(View.VISIBLE);
        }else{
            imageView.setVisibility(View.GONE);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        setActionBar(actionView);
    }

    public void setActionBar(View view){
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        actionBar = getSupportActionBar();
        actionBar.setCustomView(view, layoutParams);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home && isShowBackKey){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
