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
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(" ");
            }
            binding.gridView.setAdapter(new ListAdapter<String>(list, R.layout.list_newest) {
                @Override
                public void bindView(ViewHolder holder, String obj) {

                }
            });
        });
        binding.tvVendor.setOnClickListener(view -> {
            binding.tvVendor.setTextColor(getResources().getColor(R.color.橙黄));
            binding.tvProducts.setTextColor(getResources().getColor(R.color.black));
            binding.gridView.setNumColumns(1);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(" ");
            }
            binding.gridView.setAdapter(new ListAdapter<String>(list, R.layout.list_vendor) {
                @Override
                public void bindView(ViewHolder holder, String obj) {

                }
            });
        });
        //触发tvProducts的点击事件
        binding.tvProducts.performClick();
    }
}