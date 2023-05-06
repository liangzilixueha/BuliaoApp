package com.wifi_camera.mylibrary.foot_banner;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wifi_camera.mylibrary.ListAdapter;
import com.wifi_camera.mylibrary.R;
import com.wifi_camera.mylibrary.databinding.FragmentClassifyBinding;

import java.util.ArrayList;
import java.util.List;

public class ClassifyFragment extends Fragment {

    private FragmentClassifyBinding binding;
    private List<TextView> tvlist = new ArrayList<>();
    private List<ConstraintLayout> layoutlist = new ArrayList<>();
    private List<String> strlist = new ArrayList<>();

    public ClassifyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentClassifyBinding.inflate(inflater, container, false);
        strlist.add("通俗名称");
        strlist.add("成分");
        strlist.add("用途");
        strlist.add("织法");
        strlist.add("工艺");
        strlist.add("性能/功能");
        tvlist.add(binding.tv1);
        tvlist.add(binding.tv2);
        tvlist.add(binding.tv3);
        tvlist.add(binding.tv4);
        tvlist.add(binding.tv5);
        tvlist.add(binding.tv6);
        layoutlist.add(binding.cl1);
        layoutlist.add(binding.cl2);
        layoutlist.add(binding.cl3);
        layoutlist.add(binding.cl4);
        layoutlist.add(binding.cl5);
        layoutlist.add(binding.cl6);
        for (int i = 0; i < tvlist.size(); i++) {
            tvlist.get(i).setText(strlist.get(i));
            //设置颜色
            tvlist.get(i).setTextColor(getResources().getColor(R.color.dark_blue));
            //设置点击事件
            int finalI = i;
            tvlist.get(i).setOnClickListener(v -> {
                //改变颜色
                for (int j = 0; j < tvlist.size(); j++) {
                    if (j == finalI) {
                        tvlist.get(j).setTextColor(getResources().getColor(R.color.橙黄));
                    } else {
                        tvlist.get(j).setTextColor(getResources().getColor(R.color.dark_blue));
                    }
                }
                //对应的布局颜色白色
                for (int j = 0; j < layoutlist.size(); j++) {
                    if (j == finalI) {
                        layoutlist.get(j).setBackgroundColor(getResources().getColor(R.color.white));
                    } else {
                        layoutlist.get(j).setBackgroundColor(getResources().getColor(R.color.background_blue));
                    }
                }
            });
            //设置默认选中第一个
            if (i == 0) {
                tvlist.get(i).setTextColor(getResources().getColor(R.color.橙黄));
                layoutlist.get(i).setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("测试数据");
        }
        binding.list.setAdapter(new ListAdapter<String>(list, R.layout.list_classify) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                binding.tv1.setText("测试数据");
                binding.tv2.setText("测试数据");
                binding.tv3.setText("测试数据");
            }
        });
        return binding.getRoot();
    }
}