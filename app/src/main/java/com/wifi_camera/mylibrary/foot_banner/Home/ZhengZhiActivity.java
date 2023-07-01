package com.wifi_camera.mylibrary.foot_banner.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wifi_camera.mylibrary.R;
import com.wifi_camera.mylibrary.adapter.ListAdapter;
import com.wifi_camera.mylibrary.databinding.ActivityResultBinding;
import com.wifi_camera.mylibrary.databinding.ActivityZhengZhiBinding;

import java.util.ArrayList;
import java.util.List;

//针织推荐的界面
public class ZhengZhiActivity extends AppCompatActivity {

    private ActivityZhengZhiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityZhengZhiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String title = getIntent().getStringExtra("title");
        binding.title.setText(title);
        //状态栏颜色白色
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        List<A> list = new ArrayList<>();
        int img[] = {R.raw.tuijian1, R.raw.tuijian2, R.raw.tuijian3, R.raw.tuijian4, R.raw.tuijian5,
                R.raw.tuijian6, R.raw.tuijian7, R.raw.tuijian8};
        for (int i = 0; i < img.length; i++) {
            list.add(new A("", img[i]));
        }
        binding.gridView.setAdapter(new ListAdapter<A>(list, R.layout.list_newest) {
            @Override
            public void bindView(ViewHolder holder, A obj) {
                holder.setImageResource(R.id.img, obj.imgID);
            }
        });
    }

    class A {
        String name;
        int imgID;

        public A(String name, int imgID) {
            this.name = name;
            this.imgID = imgID;
        }
    }
}