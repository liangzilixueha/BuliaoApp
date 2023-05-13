package com.wifi_camera.mylibrary.foot_banner.Home;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.wifi_camera.mylibrary.R;
import com.wifi_camera.mylibrary.adapter.ListAdapter;
import com.wifi_camera.mylibrary.databinding.FragmentHomeNewestBinding;

import java.util.ArrayList;
import java.util.List;

public class NewestFragment extends Fragment {

    private FragmentHomeNewestBinding binding;

    public NewestFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeNewestBinding.inflate(inflater, container, false);
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
        return binding.getRoot();
    }
}