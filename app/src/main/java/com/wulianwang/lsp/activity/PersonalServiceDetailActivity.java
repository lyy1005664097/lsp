package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.wulianwang.lsp.R;

/**
 * 杜一航，魏来 3.3
 */
public class PersonalServiceDetailActivity extends AppCompatActivity {
    private ImageView a1, a2, a3;
    private Button bb;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_service_detail);

        a1 = (ImageView) this.findViewById(R.id.imageView4);
        a2 = (ImageView) this.findViewById(R.id.imageView7);
        a3 = (ImageView) this.findViewById(R.id.imageView11);
        bb = (Button)this.findViewById(R.id.button3);
        if (flag) {
            bb.setVisibility(View.VISIBLE);
        } else {
            bb.setVisibility(View.GONE);

            a1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击事件
                    Intent i = new Intent(PersonalServiceDetailActivity.this, PersonalServiceActivity.class);
                    startActivity(i);

                }
            });
            a2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent x = new Intent(PersonalServiceDetailActivity.this, MapSearchActivity.class);
                    startActivity(x);
                }

            });
            a3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent y = new Intent(PersonalServiceDetailActivity.this, PeopleListActivity.class);
                    startActivity(y);

                }
            });
        }
    }
};