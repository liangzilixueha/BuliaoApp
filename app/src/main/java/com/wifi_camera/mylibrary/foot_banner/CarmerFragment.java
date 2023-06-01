package com.wifi_camera.mylibrary.foot_banner;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.joyhonest.wifination.wifination;
import com.wifi_camera.mylibrary.Home;
import com.wifi_camera.mylibrary.MainActivity;
import com.wifi_camera.mylibrary.R;
import com.wifi_camera.mylibrary.databinding.FragmentCarmerBinding;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;


public class CarmerFragment extends Fragment {

    private FragmentCarmerBinding binding;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/" + "wifi.jpg";

    public CarmerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCarmerBinding.inflate(inflater, container, false);
        EventBus.getDefault().register(this);
        binding.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //菜单多选
                new AlertDialog.Builder(requireContext())
                        .setTitle("请选择操作")
                        .setItems(new String[]{"保存到图库", "上传识别", "取消"}, (dialog, which) -> {
                            switch (which) {
                                case 0:
                                    save();
                                    break;
                                case 1:
                                    save();
                                    break;
                                case 2:
                                    break;
                                default:
                                    break;
                            }
                        })
                        .show();
                return false;
            }
        });
        return binding.getRoot();
    }

    @Subscriber(tag = "ReceiveBMP")
    private void ReceiveBMP(Bitmap bmp) {
        binding.imageView.setImageBitmap(bmp);
    }

    private void save() {
        BitmapDrawable bd = (BitmapDrawable) binding.imageView.getDrawable();
        Bitmap bitmap = bd.getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        //系统bitmap保存到本地
        try {
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
            Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/"));
            intent.setData(uri);
            Objects.requireNonNull(getActivity()).sendBroadcast(intent);
        } catch (Exception e) {
            Toast.makeText(getContext(), "保存失败,请删除DCIM/Buliao文件夹", Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), "错误代码" + e, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        Log.e("TAG", "onDestroyView: ");
    }
}