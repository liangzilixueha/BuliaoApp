package com.wifi_camera.mylibrary.foot_banner;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wifi_camera.mylibrary.R;
import com.wifi_camera.mylibrary.databinding.FragmentHomeBinding;
import com.wifi_camera.mylibrary.foot_banner.Home.NewestFragment;
import com.wifi_camera.mylibrary.foot_banner.Home.PowerFragment;
import com.wifi_camera.mylibrary.foot_banner.Home.SearchActivity;
import com.wifi_camera.mylibrary.foot_banner.Home.StarShopActivity;
import com.wifi_camera.mylibrary.foot_banner.Home.ZhengZhiActivity;
import com.wifi_camera.mylibrary.foot_banner.Home.acticitymoreActivity;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        //默认显示Newest界面
        requireFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout1, new NewestFragment()).commit();
        binding.tvnewest.setTextColor(getResources().getColor(R.color.橙黄));
        binding.tvnewest.setOnClickListener(v -> {
            requireFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout1, new NewestFragment()).commit();
            //设置选中状态
            binding.tvnewest.setTextColor(getResources().getColor(R.color.橙黄));
            binding.tvpower.setTextColor(getResources().getColor(R.color.black));
            binding.tvunderlines.setTranslationX(0);
        });
        binding.tvpower.setOnClickListener(v -> {
            requireFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout1, new PowerFragment()).commit();
            binding.tvnewest.setTextColor(getResources().getColor(R.color.black));
            binding.tvpower.setTextColor(getResources().getColor(R.color.橙黄));
            binding.tvunderlines.setTranslationX(binding.tvnewest.getWidth());
        });
        binding.clStarShop.setOnClickListener(v -> {
            //跳转到商城
            startActivity(new Intent(requireActivity(), StarShopActivity.class));
        });
        binding.tvZhengZhiMore.setOnClickListener(view -> {
            //跳转到针织
            Intent intent = new Intent(requireActivity(), ZhengZhiActivity.class);
            intent.putExtra("title", "针织推荐");
            startActivity(intent);
        });
        binding.tvYingHuaMore.setOnClickListener(view -> {
            //跳转到针织
            Intent intent = new Intent(requireActivity(), ZhengZhiActivity.class);
            intent.putExtra("title", "印花推荐");
            startActivity(intent);
        });
        binding.clSearch.setOnClickListener(view -> {
            //跳转到搜索
            startActivity(new Intent(requireActivity(), SearchActivity.class));
        });
        binding.tvActivityMore.setOnClickListener(view -> {
            //跳转到活动
            Intent intent = new Intent(requireActivity(), acticitymoreActivity.class);
            intent.putExtra("title", "活动");
            startActivity(intent);
        });
        return binding.getRoot();
    }
}