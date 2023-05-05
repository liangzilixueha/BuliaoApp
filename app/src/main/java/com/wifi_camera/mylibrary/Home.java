package com.wifi_camera.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.wifi_camera.mylibrary.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity {

    private FrameLayout frameLayout;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        获得id();
        //设置fragment为默认界面
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
    }

    private void 获得id() {
        frameLayout = findViewById(R.id.frameLayout);
    }
}