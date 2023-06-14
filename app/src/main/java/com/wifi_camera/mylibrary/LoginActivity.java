package com.wifi_camera.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wifi_camera.mylibrary.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(R.color.background_blue));
        //设置状态栏文字颜色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (getSharedPreferences("user", MODE_PRIVATE).getBoolean("isLogin", false)) {
            startActivity(new Intent(LoginActivity.this, Home.class));
            Toast.makeText(LoginActivity.this, "欢迎回来", Toast.LENGTH_SHORT).show();
            finish();
        }
        binding.login.setOnClickListener(v -> {
            String username = binding.name.getText().toString();
            String password = binding.pw.getText().toString();
            if (username.equals("123456") && password.equals("123456")) {
                startActivity(new Intent(LoginActivity.this, Home.class));
                getSharedPreferences("user", MODE_PRIVATE).edit().putString("username", username).apply();
                getSharedPreferences("user", MODE_PRIVATE).edit().putString("password", password).apply();
                getSharedPreferences("user", MODE_PRIVATE).edit().putBoolean("isLogin", true).apply();
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
            }
        });
    }
}