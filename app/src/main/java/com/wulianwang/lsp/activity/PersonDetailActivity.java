package com.wulianwang.lsp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wulianwang.lsp.R;

/**
 * 4.2 人员详情 王艺江 皂辉杰
 */
public class PersonDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);

        initView();
        setActionBar(true, "人员详情", R.drawable.phone, (View view)->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:13100000000"));
            startActivity(intent);
        });
    }

    @Override
    public void initView() {
        TextView name = (TextView)this.findViewById(R.id.name);
        TextView resume = (TextView)this.findViewById(R.id.resume);
        TextView work = (TextView)this.findViewById(R.id.work);
        TextView location = (TextView)this.findViewById(R.id.location);
        TextView description = (TextView)this.findViewById(R.id.description);
        TextView score = (TextView)this.findViewById(R.id.score);
        ImageView message = (ImageView)this.findViewById(R.id.message);

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto: 18337600315"));
                startActivity(intent2);
            }
        });
        String str = "彭于晏";
        name.setText(str);
        String str1 = "43岁";
        resume.setText(str1);
        String str2 = "水电工、建筑工";
        work.setText(str2);
        String str3 = "信阳市平桥区";
        location.setText(str3);
        String str4 = "综合得分:" + "      95";
        score.setText(str4);
        String str5 = "电子主要介绍的基本概念、基本定律及分析方法电路的暂态分析;单相正弦交流电路;" +
                "半导体基础;晶体管及;集成运算放大器及应用;数字逻辑电路基础;逻辑代数与逻辑函数\n";
        description.setText(str5);
    }
}

