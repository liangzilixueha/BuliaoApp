package com.wifi_camera.mylibrary.foot_banner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wifi_camera.mylibrary.R;


public class HomeFragment extends Fragment {

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
        //将状态栏设置绿色
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.dark_blue));

        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}