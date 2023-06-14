package com.wifi_camera.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.wifi_camera.mylibrary.adapter.ListAdapter;
import com.wifi_camera.mylibrary.databinding.ActivityGoodsViewBinding;

import java.util.ArrayList;
import java.util.List;

public class GoodsViewActivity extends AppCompatActivity {

    private ActivityGoodsViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGoodsViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(R.color.background_blue));
        binding.imageView5.setImageResource(R.raw.s5);
        setListView1();
        setListView2();
        setListView3();
        setGridView1();
        setGridView2();

    }

    private void setGridView2() {
        List<String> list = new ArrayList<>();
        list.add(" ");
        list.add(" ");
        list.add(" ");
        list.add(" ");
        binding.gridView2.setAdapter(new ListAdapter<String>(list, R.layout.list_goods) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setVisibility(R.id.tvLook, View.INVISIBLE);
                holder.setImageResource(R.id.img, R.raw.s7);
            }
        });
    }

    private void setGridView1() {
        List<String> list = new ArrayList<>();
        list.add(" ");
        list.add(" ");
        binding.gridView1.setAdapter(new ListAdapter<String>(list, R.layout.list_goods) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setVisibility(R.id.tvLook, View.INVISIBLE);
                holder.setImageResource(R.id.img, R.raw.s8);
            }
        });
    }

    private void setListView3() {
        List<A> list = new ArrayList<>();
        list.add(new A("描述相符", "4.9"));
        list.add(new A("描述相符", "4.9"));
        list.add(new A("描述相符", "4.9"));
        binding.listView3.setAdapter(new ListAdapter<A>(list, R.layout.list_goodsview_3) {
            @Override
            public void bindView(ViewHolder holder, A obj) {
                holder.setText(R.id.name, obj.name);
                holder.setText(R.id.price, obj.price);
            }
        });
    }

    private void setListView2() {
        List<A> list = new ArrayList<>();
        list.add(new A("搜布编号", "SP0022087835"));
        list.add(new A("商家货号", "印花"));
        list.add(new A("品      类", "梭织"));
        list.add(new A("品      类", "梭织"));
        list.add(new A("品      类", "梭织"));
        list.add(new A("品      类", "梭织"));
        binding.listView2.setAdapter(new ListAdapter<A>(list, R.layout.list_goodsview_2) {
            @Override
            public void bindView(ViewHolder holder, A obj) {
                holder.setText(R.id.name, obj.name);
                holder.setText(R.id.price, obj.price);
            }
        });
    }

    private void setListView1() {
        List<A> list = new ArrayList<>();
        list.add(new A("色卡价格", "$99.9"));
        list.add(new A("剪样价格", "$99.9"));
        list.add(new A("大货价格", "$99.9"));
        binding.listView.setAdapter(new ListAdapter<A>(list, R.layout.list_goodsview_1) {
            @Override
            public void bindView(ViewHolder holder, A obj) {
                holder.setText(R.id.name, obj.name);
                holder.setText(R.id.price, obj.price);
            }
        });
    }

    class A {
        String name;
        String price;

        public A(String name, String price) {
            this.name = name;
            this.price = price;
        }
    }
}