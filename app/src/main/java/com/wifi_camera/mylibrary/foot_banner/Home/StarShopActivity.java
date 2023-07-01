package com.wifi_camera.mylibrary.foot_banner.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.wifi_camera.mylibrary.GoodsViewActivity;
import com.wifi_camera.mylibrary.R;
import com.wifi_camera.mylibrary.adapter.ListAdapter;
import com.wifi_camera.mylibrary.databinding.ActivityStarShopBinding;

import java.util.ArrayList;

public class StarShopActivity extends AppCompatActivity {

    private ActivityStarShopBinding binding;
    private int[] img={R.raw.shop1,R.raw.shop2,
            R.raw.shop3,R.raw.shop4,R.raw.shop5,R.raw.shop6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStarShopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(R.color.background_blue));
        binding.shopicon.setImageResource(R.raw.shopicon);
        ArrayList<A> list = new ArrayList<>();

        for (int i = 0; i < img.length; i++) {
            list.add(new A("", img[i]));
        }
        binding.gridView.setAdapter(new ListAdapter<A>(list, R.layout.list_goods) {
            @Override
            public void bindView(ViewHolder holder, A obj) {
                holder.setImageResource(R.id.img, obj.imgID);
                holder.setOnClickListener(R.id.root, v -> {
                    startActivity(new Intent(StarShopActivity.this, GoodsViewActivity.class));
                });
            }
        });
    }
    class A {
        String name;
        int imgID;

        public A(String name, int imgID) {
            this.name = name;
            this.imgID = imgID;
        }
    }
}