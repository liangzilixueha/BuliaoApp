package com.wifi_camera.mylibrary.foot_banner.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.wifi_camera.mylibrary.R;
import com.wifi_camera.mylibrary.adapter.ListAdapter;
import com.wifi_camera.mylibrary.databinding.ActivityActicitymoreBinding;

import java.util.ArrayList;
import java.util.List;

public class acticitymoreActivity extends AppCompatActivity {

    private ActivityActicitymoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityActicitymoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        //状态栏文字颜色改为黑色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setGridview1();
        setGridview2();
        setGridview3();
        setGridview4();
        setGridview5();
        setGridview6();
    }

    private void setGridview6() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add("");
        }
        binding.gridview6.setAdapter(new ListAdapter<String>(list, R.layout.list_newest) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setImageResource(R.id.img, R.raw.nav2);
            }
        });
    }

    private void setGridview1() {
        List<String> list = new ArrayList<>();
        list.add("");
        binding.gridview1.setAdapter(new ListAdapter<String>(list, R.layout.list_img) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setImageResource(R.id.img, R.raw.nav3);
            }
        });
    }

    private void setGridview2() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        binding.gridview2.setAdapter(new ListAdapter<String>(list, R.layout.list_img) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setImageResource(R.id.img, R.raw.nav4);
            }
        });
    }

    private void setGridview3() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        binding.gridview3.setAdapter(new ListAdapter<String>(list, R.layout.list_img) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setImageResource(R.id.img, R.raw.nav5);
            }
        });
    }

    private void setGridview4() {
        List<String> list = new ArrayList<>();
        list.add("");
        binding.gridview4.setAdapter(new ListAdapter<String>(list, R.layout.list_img) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setImageResource(R.id.img, R.raw.nav6);
            }
        });
    }

    private void setGridview5() {
        List<String> list = new ArrayList<>();
        list.add("");
        binding.gridview5.setAdapter(new ListAdapter<String>(list, R.layout.list_img) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setImageResource(R.id.img, R.raw.nav7);
            }
        });
    }
}