package com.wulianwang.lsp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wulianwang.lsp.R;
import com.wulianwang.lsp.bean.User;
import com.wulianwang.lsp.util.SharedPrefsUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private Button im1, im2, im3, im4;
    boolean isLogin = false;
    List<String> paths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        setActionBar("中智惠生活");
    }

    @Override
    public void initData() {
        paths = new ArrayList<>();
        paths.add("http://img0.imgtn.bdimg.com/it/u=4133970237,1551773401&fm=11&gp=0.jpg");
        paths.add("http://img2.imgtn.bdimg.com/it/u=1747277526,4027229022&fm=26&gp=0.jpg");
        paths.add("http://img4.imgtn.bdimg.com/it/u=1548659975,2111531&fm=26&gp=0.jpg");
        paths.add("http://img5.imgtn.bdimg.com/it/u=2863504786,3776649173&fm=26&gp=0.jpg");
        paths.add("http://img0.imgtn.bdimg.com/it/u=96180300,3008843172&fm=26&gp=0.jpg");
    }

    @Override
    public void initView() {
        Banner banner = findViewById(R.id.banner);
        banner.setDelayTime(3000)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setIndicatorGravity(BannerConfig.CENTER)
                .isAutoPlay(true)
                .setImages(paths)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        Glide.with(context.getApplicationContext())
                                .load(path)
                                .into(imageView);
                    }
                })
                .start();
        //电力服务
        im1 = findViewById(R.id.imageView);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ElectricityServiceActivity.class);
                startActivity(intent);
            }
        });
        //任务广场
        im2 = findViewById(R.id.imageView3);
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllServiceActivity.class);
                startActivity(intent);
            }
        });
        //人员列表
        im3 = findViewById(R.id.imageView5);
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PeopleListActivity.class);
                startActivity(intent);
            }
        });
        //我的、登录
        im4 = findViewById(R.id.imageView2);
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLogin){
                    Intent intent = new Intent(MainActivity.this, MyInfoActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        User user = SharedPrefsUtil.getValue(this, "user", (User)null);

        if(user == null){
            isLogin = false;
            im4.setText("登录/注册");
        }else{
            isLogin = true;
            im4.setText("我的");
        }

        isLogin = true;
    }
}
