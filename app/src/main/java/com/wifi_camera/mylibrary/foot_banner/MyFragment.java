package com.wifi_camera.mylibrary.foot_banner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wifi_camera.mylibrary.R;
import com.wifi_camera.mylibrary.databinding.FragmentMyBinding;
import com.wifi_camera.mylibrary.foot_banner.My.ShoppingCarActivity;
import com.wifi_camera.mylibrary.foot_banner.My.YJXDActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;


public class MyFragment extends Fragment {

    private FragmentMyBinding binding;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/" + "userIcon.jpg";

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyBinding.inflate(inflater, container, false);

        StringBuilder doc = new StringBuilder(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/");
        //输出该文件下的所有文件
        File file = new File(doc.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.getName().contains("userIcon")) {
                doc.append(file1.getName());
                break;
            }
        }
        Log.e("TAG", "onCreateView: " + doc);
        Bitmap bitmap = BitmapFactory.decodeFile(doc.toString());
        if (bitmap != null) {
            //查看本地是否有头像

            binding.ivUserIcon.setImageBitmap(BitmapFactory.decodeFile(doc.toString()));
        }
        binding.exitLogin.setOnClickListener(v -> {
            getActivity().getSharedPreferences("user", getActivity().MODE_PRIVATE).edit().putBoolean("isLogin", false).apply();
            //账号密码置空
            getActivity().getSharedPreferences("user", getActivity().MODE_PRIVATE).edit().putString("username", "").apply();
            getActivity().getSharedPreferences("user", getActivity().MODE_PRIVATE).edit().putString("password", "").apply();
            getActivity().finish();
        });
        binding.userName.setText(Objects.requireNonNull(getActivity()).getSharedPreferences("user", getActivity().MODE_PRIVATE).getString("username", "未登录"));
        binding.ivUserIcon.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, 2);
        });
        binding.ivShoppingCar.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), ShoppingCarActivity.class));
        });
        binding.tvShoppingCar.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), ShoppingCarActivity.class));
        });
        binding.tvYJXD.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), YJXDActivity.class));
        });
        return binding.getRoot();
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2) {
            //创建文件夹
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/");
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                Uri uri = data.getData();
            } catch (Exception e) {
                Toast.makeText(getContext(), "未选择图片", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                //删除file下的所有文件
                File[] files = file.listFiles();
                for (File file1 : files) {
                    file1.delete();
                }
                Uri uri = data.getData();
                binding.ivUserIcon.setImageURI(uri);
                Drawable drawable = binding.ivUserIcon.getDrawable();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                FileOutputStream fos = new FileOutputStream(path);
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri1 = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/"));
                intent.setData(uri1);
                Objects.requireNonNull(getActivity()).sendBroadcast(intent);
            } catch (Exception e) {
                Toast.makeText(getContext(), "保存失败,请删除DCIM/Buliao文件夹", Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), "错误代码" + e, Toast.LENGTH_SHORT).show();
            }
        }
    }
}