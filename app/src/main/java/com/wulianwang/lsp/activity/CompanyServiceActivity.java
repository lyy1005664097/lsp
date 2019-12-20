package com.wulianwang.lsp.activity;


        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.bumptech.glide.Glide;
        import com.wulianwang.lsp.R;
        import com.wulianwang.lsp.bean.MyList;
        import com.wulianwang.lsp.adapter.MyListAdapter;

        import java.util.ArrayList;
        import java.util.List;
/**
 *     成员：刘长恩 曹彬
 *     3.5 企业服务
 */
public class CompanyServiceActivity extends AppCompatActivity {
    private ListView mLv1;
    private List<MyList> mylist;
    private MyListAdapter gridadapter;
    private ImageView IB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_service);
        IB =(ImageView)this.findViewById(R.id.imageB1);

        mLv1 =(ListView)findViewById(R.id.list1);
        try {
            mylist =new ArrayList<MyList>();
            gridadapter=new MyListAdapter(this);
            mylist.add(0,new MyList("修路"));
            mylist.add(1,new MyList("架桥"));
            mylist.add(2,new MyList("家变电站"));
            mylist.add(3,new MyList("装变压器"));
            mylist.add(4,new MyList("修水电站"));
            mylist.add(5,new MyList("装发动机"));
            mylist.add(6,new MyList("维修发动机"));
            gridadapter.setList(mylist);
            mLv1.setAdapter(gridadapter);
        } catch (Exception e) {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
        finally {
        }

        mLv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CompanyServiceActivity.this, CompanyServiceDetailActivity.class);
                startActivity(intent);
            }
        });
        IB.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View vAll) {
                Intent intent = new Intent(CompanyServiceActivity.this,AllServiceActivity.class);
                startActivity(intent);
            }
        });
    }
}
