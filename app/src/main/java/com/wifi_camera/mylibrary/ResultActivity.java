package com.wifi_camera.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.wifi_camera.mylibrary.adapter.ListAdapter;
import com.wifi_camera.mylibrary.databinding.ActivityResultBinding;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //获得传递过来的数据
        getWindow().setStatusBarColor(getResources().getColor(R.color.background_blue));
        String data = getIntent().getStringExtra("data");
        Log.e("TAG", "将要显示的数据: " + data);
        Home.识别结果 a = new Gson().fromJson(data, Home.识别结果.class);
        binding.gridView.setAdapter(new ListAdapter<Home.识别结果.DataDTO>(a.data, R.layout.list_result) {
            @Override
            public void bindView(ViewHolder holder, Home.识别结果.DataDTO obj) {
                holder.setText(R.id.name, obj.proName);
                holder.setText(R.id.colorName, obj.colorName);
                holder.setText(R.id.price, "价格：" + obj.showPrice.toString());
                new Thread(() -> {
                    try {
                        下载图片(holder, obj);
                    } catch (Exception e) {
                        Log.e("TAG", "同步图片出错: " + e);
                    }
                }).start();
            }

            private void 下载图片(ViewHolder holder, Home.识别结果.DataDTO obj) throws Exception {
                //对obj.url进行okhttp请求
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://" + obj.img)
                        .build();
                okhttp3.Response response = client.newCall(request).execute();
                byte[] bytes = response.body().bytes();
                //bytes转换成bitmap
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                //将字节数组转换为图片
                runOnUiThread(() -> holder.setImageBitmap(R.id.img, bitmap));
            }
        });
    }
}