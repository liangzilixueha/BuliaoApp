package com.wifi_camera.mylibrary.foot_banner;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wifi_camera.mylibrary.R;
import com.wifi_camera.mylibrary.databinding.FragmentHomeBinding;
import com.wifi_camera.mylibrary.foot_banner.Home.NewestFragment;
import com.wifi_camera.mylibrary.foot_banner.Home.PowerFragment;
import com.wifi_camera.mylibrary.foot_banner.Home.SearchActivity;
import com.wifi_camera.mylibrary.foot_banner.Home.StarShopActivity;
import com.wifi_camera.mylibrary.foot_banner.Home.ZhengZhiActivity;
import com.wifi_camera.mylibrary.foot_banner.Home.acticitymoreActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<ImageView> imageViews = new ArrayList<>();
    private int[] images = {R.raw.b1, R.raw.b3, R.raw.s10, R.raw.s6,R.raw.banner3,R.raw.idea1,R.raw.idea2,
    R.raw.idea3,R.raw.idea4,R.raw.idea5,R.raw.idea6,R.raw.idea7,R.raw.idea8,R.raw.idea9};
//    private int[] images={R.raw.home1,R.raw.home2,R.raw.home3,R.raw.home4,R.raw.home5,R.raw.home6,R.raw.home7,
//            R.raw.home8,R.raw.home9};

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
        添加全部图片();
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

    private void 添加全部图片() {
        imageViews.add(binding.imageView1);
        imageViews.add(binding.imageView2);
        imageViews.add(binding.imageView3);
        imageViews.add(binding.imageView4);
        imageViews.add(binding.imageView5);
        imageViews.add(binding.imageView6);
        imageViews.add(binding.imageView7);
        imageViews.add(binding.imageView8);
        imageViews.add(binding.imageView9);
        imageViews.add(binding.imageView10);
        imageViews.add(binding.imageView11);
        imageViews.add(binding.imageView12);
        imageViews.add(binding.imageView13);
        imageViews.add(binding.imageView14);
        for (int i = 0; i < imageViews.size(); i++) {
            if (i < images.length)
                imageViews.get(i).setImageResource(images[i]);
            else
                imageViews.get(i).setImageResource(images[i % images.length]);

        }
    }
}