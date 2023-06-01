package com.wifi_camera.mylibrary.foot_banner.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wifi_camera.mylibrary.R;
import com.wifi_camera.mylibrary.adapter.ListAdapter;
import com.wifi_camera.mylibrary.databinding.ActivityStarShopBinding;

import java.util.ArrayList;

public class StarShopActivity extends AppCompatActivity {

    private ActivityStarShopBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStarShopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(R.color.background_blue));
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        binding.gridView.setAdapter(new ListAdapter<String>(list,R.layout.list_goods) {
            @Override
            public void bindView(ViewHolder holder, String obj) {

            }
        });
    }
}