package com.wifi_camera.mylibrary.foot_banner.My;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.wifi_camera.mylibrary.R;
import com.wifi_camera.mylibrary.adapter.ListAdapter;
import com.wifi_camera.mylibrary.databinding.ActivityYjxdactivityBinding;

import java.util.ArrayList;
import java.util.List;

public class YJXDActivity extends AppCompatActivity {

    private ActivityYjxdactivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityYjxdactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add("") ;
        }
        binding.gridView.setAdapter(new ListAdapter<String>(list, R.layout.list_yjxd) {
            @Override
            public void bindView(ViewHolder holder, String obj) {

            }
        });
    }
}