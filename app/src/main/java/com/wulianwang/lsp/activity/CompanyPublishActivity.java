

package com.wulianwang.lsp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.wulianwang.lsp.R;

/**
 * 5.9 赵哲 胡博文
 */
public class CompanyPublishActivity extends BaseActivity {

    private EditText myEditText;
    private Spinner mySpinner;
    private ImageView arrowImageView;
    private TextView chooseText;
    private Button mBtnTextView;
    private TextView mTv;
    private Button mBt2;
    String[] ctype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_publish);

        initData();
        initView();
        setActionBar(true, "企业发布");
    }

    @Override
    public void initData() {
        ctype = new String[]{"项目1", "项目2", "项目3", "项目4"};
    }

    @Override
    public void initView() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ctype);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = super.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        mBt2=findViewById(R.id.button2);
        mBt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CompanyPublishActivity.this,MapSearchActivity.class);
                startActivityForResult(intent, 200);
            }
        });


        mBtnTextView =findViewById(R.id.button);
        mTv=(TextView) findViewById(R.id.textView);
        mBtnTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    Intent intent= new Intent(CompanyPublishActivity.this,Test2.class);
                //   startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200){
            if(resultCode == 100){
                mBt2.setText(data.getStringExtra("address"));
            }
        }
    }
}

