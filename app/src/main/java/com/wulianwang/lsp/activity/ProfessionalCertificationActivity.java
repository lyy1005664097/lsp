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
public class ProfessionalCertificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_certification);
        Spinner spinner;
        Button btn;
        ImageView imv;
        Spinner sp = (Spinner)this.findViewById(R.id.spinner);
        imv=(ImageView)this.findViewById(R.id.imageView4);
        btn=(Button)this.findViewById(R.id.button2);
        String[] str = {"请选择职业类型", "水电工", "水工", "外卖员", "建筑工"};
        int String;
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str);
        sp.setAdapter(ad);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfessionalCertificationActivity.this, MyInfoActivity.class);
                startActivity(intent);}
        });
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ProfessionalCertificationActivity.this, MyInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
