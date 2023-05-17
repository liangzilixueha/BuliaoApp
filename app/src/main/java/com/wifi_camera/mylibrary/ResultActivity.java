package com.wifi_camera.mylibrary;

import static android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET;
import static android.net.NetworkCapabilities.TRANSPORT_CELLULAR;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.wifi_camera.mylibrary.adapter.ListAdapter;
import com.wifi_camera.mylibrary.databinding.ActivityResultBinding;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding binding;
    private ConnectivityManager connectivityManager;
    private NetworkRequest.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        builder = null;
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

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    builder = new NetworkRequest.Builder();
                    builder.addCapability(NET_CAPABILITY_INTERNET);
                    builder.addTransportType(TRANSPORT_CELLULAR);
                    NetworkRequest build = builder.build();
                    connectivityManager.requestNetwork(build, new ConnectivityManager.NetworkCallback() {
                        @Override
                        public void onAvailable(Network network) {
                            super.onAvailable(network);
                            try {
                                if (Build.VERSION.SDK_INT >= 23) {
                                    connectivityManager.bindProcessToNetwork(network); //这句话要加上哈，不然设置不生效
                                } else {// 23后这个方法舍弃了
                                    ConnectivityManager.setProcessDefaultNetwork(network);
                                }
                                下载图片(holder, obj);
                                if (Build.VERSION.SDK_INT >= 23) {
                                    connectivityManager.bindProcessToNetwork(null); //这句话要加上哈，不然设置不生效
                                } else {// 23后这个方法舍弃了
                                    ConnectivityManager.setProcessDefaultNetwork(null);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("TAG", "同步图片出错: " + e);
                            }

                        }
                    });
                }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //变回去
        Log.e("TAG", "onDestroy: ");
    }
}