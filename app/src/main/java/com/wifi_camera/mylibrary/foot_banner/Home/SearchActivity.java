package com.wifi_camera.mylibrary.foot_banner.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wifi_camera.mylibrary.R;
import com.wifi_camera.mylibrary.adapter.ListAdapter;
import com.wifi_camera.mylibrary.databinding.ActivitySearchBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding binding;
    private int img[] = {R.raw.search1, R.raw.search2, R.raw.search3, R.raw.search4, R.raw.search5,
            R.raw.search6, R.raw.search7, R.raw.search8, R.raw.search9, R.raw.search10, R.raw.search11,
            R.raw.search12, R.raw.search13};
    private int img2[] = {R.raw.search20, R.raw.search21, R.raw.search22, R.raw.search23, R.raw.search4,
            R.raw.search25, R.raw.search26, R.raw.search27};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        //设置状态栏字体颜色为黑色
        getWindow().getDecorView().setSystemUiVisibility(
                getWindow().getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding.tvSearch.setOnClickListener(v -> {
            Toast.makeText(this, "搜索了捏", Toast.LENGTH_SHORT).show();
        });


        binding.tvProducts.setOnClickListener(v -> {
            binding.tvProducts.setTextColor(getResources().getColor(R.color.橙黄));
            binding.tvVendor.setTextColor(getResources().getColor(R.color.black));
            binding.gridView.setNumColumns(2);
            List<A> list = new ArrayList<>();
            for (int j : img2) {
                list.add(new A("东方红造纸厂", j));
            }
            binding.gridView.setAdapter(new ListAdapter<A>(list, R.layout.list_newest) {
                @Override
                public void bindView(ViewHolder holder, A obj) {
                    holder.setImageResource(R.id.img, obj.imgID);
                    holder.setText(R.id.text, obj.name);
                }
            });
        });
        binding.tvVendor.setOnClickListener(view -> {
            binding.tvVendor.setTextColor(getResources().getColor(R.color.橙黄));
            binding.tvProducts.setTextColor(getResources().getColor(R.color.black));
            binding.gridView.setNumColumns(1);
            List<A> list = new ArrayList<>();
            for (int j : img) {
                list.add(new A("东方红造纸厂", j));
            }
            binding.gridView.setAdapter(new ListAdapter<A>(list, R.layout.list_vendor) {
                @Override
                public void bindView(ViewHolder holder, A obj) {
                    holder.setImageResource(R.id.img, obj.imgID);
                    holder.setText(R.id.text, obj.name);
                }
            });
        });
        //触发tvProducts的点击事件
        binding.tvProducts.performClick();
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