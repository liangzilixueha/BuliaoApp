package com.wifi_camera.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wifi_camera.mylibrary.databinding.ActivityHomeBinding;
import com.wifi_camera.mylibrary.foot_banner.BillFragment;
import com.wifi_camera.mylibrary.foot_banner.ClassifyFragment;
import com.wifi_camera.mylibrary.foot_banner.HomeFragment;

public class Home extends AppCompatActivity {


    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
    }

}