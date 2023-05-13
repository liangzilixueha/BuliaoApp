package com.wifi_camera.mylibrary;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.wifi_camera.mylibrary.databinding.ActivityHomeBinding;
import com.wifi_camera.mylibrary.foot_banner.BillFragment;
import com.wifi_camera.mylibrary.foot_banner.ClassifyFragment;
import com.wifi_camera.mylibrary.foot_banner.HomeFragment;
import com.wifi_camera.mylibrary.foot_banner.MyFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Home extends AppCompatActivity {


    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //设置状态栏颜色
        getWindow().setStatusBarColor(getResources().getColor(R.color.background_blue));
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
        binding.clBill
                .setOnClickListener(v -> getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, new BillFragment()).commit());
        binding.clHome
                .setOnClickListener(v -> getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, new HomeFragment()).commit());
        binding.clClassify
                .setOnClickListener(v -> getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, new ClassifyFragment()).commit());
        binding.clMy
                .setOnClickListener(v -> getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, new MyFragment()).commit());
        binding.clCamera
                .setOnClickListener(v -> {
                    //跳转到摄像头
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                        Log.d("进入：", "requestPermissions");
                    }
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                        Log.d("进入：", "requestPermissions");
                    }
//                    无视错误（这个好赖皮啊
                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builder.build());
                    builder.detectFileUriExposure();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/" + "test.png"));
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, 1);
                });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Log.e("TAG", "拍摄成功");
            //压缩文件
            Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/" + "test.png");
            qualityCompress(bitmap, new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/" + "test-yasuo.jpeg"));
            //提示图库更新
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/"));
            intent.setData(uri);
            sendBroadcast(intent);
            new Thread(() -> {
                try {
                    上传结果 rel = 发送图片到服务器();
                    识别结果 a = 请求识别(rel.data);
                    //输出提示
                } catch (Exception e) {
                    Log.e("TAG", "错误结果：" + e);
                }
            }).start();
        }
    }

    private 上传结果 发送图片到服务器() throws Exception {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/" + "test-yasuo.jpeg";
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "https://mbzt.maibuyi.com/mbz-xcx/common/pic/up";
        File file = new File(path);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"), file))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String str = response.body().string();
        上传结果 rel = new Gson().fromJson(str, 上传结果.class);
        return rel;
    }

    /**
     * @param data 请传入上传结果的中的data数据
     * @return
     * @throws Exception
     */
    private 识别结果 请求识别(String data) throws Exception {
        Log.e("TAG", "请求识别-目标连接: " + data);
        //新建一个post请求，往请求体中加入json格式的字符串
        String url = "https://mbzt.maibuyi.com/mbz-xcx/common/pic/search";
        识别需要的json search = new 识别需要的json("http://" + data);
        Request request = new Request.Builder()
                .addHeader("content-type", "application/json")
                .url(url)
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(search)))
                .build();
        Response response = new OkHttpClient().newCall(request).execute();
        String json = response.body().string();
        Log.e("TAG", "请求识别: " + json);
        return new Gson().fromJson(json, 识别结果.class);
    }

    /**
     * 质量压缩
     * 设置bitmap options属性，降低图片的质量，像素不会减少
     * 第一个参数为需要压缩的bitmap图片对象，第二个参数为压缩后图片保存的位置
     * 设置options 属性0-100，来实现压缩（因为png是无损压缩，所以该属性对png是无效的）
     *
     * @param bmp
     * @param file
     */
    public void qualityCompress(Bitmap bmp, File file) {
        // 0-100 100为不压缩
        int quality = 20;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        bmp.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
            Log.e("TAG", "压缩成功");
        } catch (Exception e) {
            Log.e("TAG", "压缩失败: " + e);
        }
    }


    class 上传结果 {
        @SerializedName("code")
        public Integer code;
        @SerializedName("message")
        public String message;
        @SerializedName("data")
        public String data;
    }

    class 识别需要的json {
        public String picUrl;

        public 识别需要的json(String picUrl) {
            this.picUrl = picUrl;
        }
    }

    class 识别结果 {

        @SerializedName("code")
        public Integer code;//网络代码
        @SerializedName("message")
        public String message;//信息
        @SerializedName("data")
        public List<DataDTO> data;//具体数据

        public class DataDTO {
            @SerializedName("proId")
            public Long proId;//产品id
            @SerializedName("itemId")
            public Integer itemId;//规格id
            @SerializedName("itemName")
            public String itemName;//规格名称
            @SerializedName("colorName")
            public String colorName;//颜色名称
            @SerializedName("img")
            public String img;//图片地址
            @SerializedName("proCode")
            public String proCode;//产品代码
            @SerializedName("proName")
            public String proName;//产品名称
            @SerializedName("showPrice")
            public Double showPrice;//价格
        }
    }
}