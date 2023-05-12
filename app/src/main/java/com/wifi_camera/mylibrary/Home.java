package com.wifi_camera.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;

import com.wifi_camera.mylibrary.databinding.ActivityHomeBinding;
import com.wifi_camera.mylibrary.foot_banner.BillFragment;
import com.wifi_camera.mylibrary.foot_banner.ClassifyFragment;
import com.wifi_camera.mylibrary.foot_banner.HomeFragment;
import com.wifi_camera.mylibrary.foot_banner.MyFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
        binding.clCamera
                .setOnClickListener(v -> {
                    //跳转到摄像头
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                        Log.d("进入：", "requestPermissions");
                    }
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                        Log.d("进入：", "requestPermissions");
                    }
                    //无视错误（这个好赖皮啊
                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builder.build());
                    builder.detectFileUriExposure();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/" + "test.png"));
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, 1);
                });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Log.e("TAG", "拍摄成功");
            //提示图库更新
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/" + "test.png"));
            intent.setData(uri);
            sendBroadcast(intent);
        }
    }


}