package com.wifi_camera.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wifi_camera.mylibrary.databinding.ActivityHomeBinding;
import com.wifi_camera.mylibrary.foot_banner.BillFragment;
import com.wifi_camera.mylibrary.foot_banner.ClassifyFragment;
import com.wifi_camera.mylibrary.foot_banner.HomeFragment;
import com.wifi_camera.mylibrary.foot_banner.MyFragment;

public class Home extends AppCompatActivity {


    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //设置状态栏颜色
        getWindow().setStatusBarColor(getResources().getColor(R.color.background_blue));
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
        binding.clBill
                .setOnClickListener(v -> getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, new BillFragment()).commit());
        binding.clHome
                .setOnClickListener(v -> getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, new HomeFragment()).commit());
        binding.clClassify
                .setOnClickListener(v -> getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, new ClassifyFragment()).commit());
        binding.clMy
                .setOnClickListener(v -> getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, new MyFragment()).commit());
    }

}