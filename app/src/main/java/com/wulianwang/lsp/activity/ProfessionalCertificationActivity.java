package com.wulianwang.lsp.activity;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.Spinner;
        import android.widget.ArrayAdapter;
        import android.content.Intent;
        import android.view.View;

        import com.wulianwang.lsp.R;

/**
 * 刘泽钜，杨瑞华 5.10
 */
public class ProfessionalCertificationActivity extends BaseActivity {

    Spinner sp;
    Button btn;
    String[] str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_certification);

        initData();
        initView();
        setActionBar(true, "职业认证");
    }

    @Override
    public void initData() {
        str = new String[]{"请选择职业类型", "水电工", "水工", "外卖员", "建筑工"};
    }

    @Override
    public void initView() {
        sp = (Spinner)this.findViewById(R.id.spinner);
        btn=(Button)this.findViewById(R.id.button2);

        int String;
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str);
        sp.setAdapter(ad);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //    Intent intent = new Intent(ProfessionalCertificationActivity.this, MyInfoActivity.class);
                //    startActivity(intent);
            }
        });
    }
}
