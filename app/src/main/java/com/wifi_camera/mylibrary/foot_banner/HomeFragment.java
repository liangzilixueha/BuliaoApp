package com.wifi_camera.mylibrary.foot_banner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wifi_camera.mylibrary.R;
import com.wifi_camera.mylibrary.databinding.FragmentHomeBinding;
import com.wifi_camera.mylibrary.foot_banner.Home.NewestFragment;
import com.wifi_camera.mylibrary.foot_banner.Home.PowerFragment;


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
        return binding.getRoot();
    }
}