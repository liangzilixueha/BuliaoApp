package com.wifi_camera.mylibrary.foot_banner.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wifi_camera.mylibrary.R;
import com.wifi_camera.mylibrary.adapter.ListAdapter;
import com.wifi_camera.mylibrary.databinding.ActivityResultBinding;
import com.wifi_camera.mylibrary.databinding.ActivityZhengZhiBinding;

import java.util.ArrayList;
import java.util.List;

//针织推荐的界面
public class ZhengZhiActivity extends AppCompatActivity {

    private ActivityZhengZhiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityZhengZhiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String title = getIntent().getStringExtra("title");
        binding.title.setText(title);
        //状态栏颜色白色
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("666");
        }
        binding.gridView.setAdapter(new ListAdapter<String>(list, R.layout.list_newest) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.text, "666");
            }
        });
    }
}