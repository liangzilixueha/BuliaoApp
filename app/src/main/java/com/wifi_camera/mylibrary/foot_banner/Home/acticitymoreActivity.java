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
        List<A> list = new ArrayList<>();
        int[] img ={R.raw.more_grid1,R.raw.more_grid2,R.raw.more_grid3,R.raw.more_grid4,
                R.raw.more_grid5,R.raw.more_grid6,R.raw.more_grid7,R.raw.more_grid8};
        for (int j : img) {
            list.add(new A("", j));
        }
        binding.gridview6.setAdapter(new ListAdapter<A>(list, R.layout.list_newest) {
            @Override
            public void bindView(ViewHolder holder, A obj) {
                holder.setImageResource(R.id.img, obj.imgID);
            }
        });
    }

    private void setGridview1() {
        List<String> list = new ArrayList<>();
        list.add("");
        binding.gridview1.setAdapter(new ListAdapter<String>(list, R.layout.list_img) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setImageResource(R.id.img, R.raw.more1);
            }
        });
    }

    private void setGridview2() {
        List<A> list = new ArrayList<>();
        list.add(new A("热门", R.raw.more2));
        list.add(new A("最新", R.raw.more3));
        list.add(new A("关注", R.raw.more4));
        binding.gridview2.setAdapter(new ListAdapter<A>(list, R.layout.list_img) {

            @Override
            public void bindView(ViewHolder holder, A obj) {
                holder.setImageResource(R.id.img, obj.imgID);
            }
        });
    }

    private void setGridview3() {
        List<A> list = new ArrayList<>();
        list.add(new A("热门", R.raw.more5));
        list.add(new A("最新", R.raw.more6));
        list.add(new A("关注", R.raw.more7));
        binding.gridview3.setAdapter(new ListAdapter<A>(list, R.layout.list_img) {

            @Override
            public void bindView(ViewHolder holder, A obj) {
                holder.setImageResource(R.id.img, obj.imgID);
            }
        });
    }

    private void setGridview4() {
        List<String> list = new ArrayList<>();
        list.add("");
        binding.gridview4.setAdapter(new ListAdapter<String>(list, R.layout.list_img) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setImageResource(R.id.img, R.raw.more8);
            }
        });
    }

    private void setGridview5() {
        List<String> list = new ArrayList<>();
        list.add("");
        binding.gridview5.setAdapter(new ListAdapter<String>(list, R.layout.list_img) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setImageResource(R.id.img, R.raw.more9);
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