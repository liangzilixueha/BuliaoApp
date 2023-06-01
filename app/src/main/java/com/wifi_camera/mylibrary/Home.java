package com.wifi_camera.mylibrary;

import static android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET;
import static android.net.NetworkCapabilities.TRANSPORT_CELLULAR;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.joyhonest.wifination.wifination;
import com.wifi_camera.mylibrary.databinding.ActivityHomeBinding;
import com.wifi_camera.mylibrary.foot_banner.BillFragment;
import com.wifi_camera.mylibrary.foot_banner.CarmerFragment;
import com.wifi_camera.mylibrary.foot_banner.ClassifyFragment;
import com.wifi_camera.mylibrary.foot_banner.HomeFragment;
import com.wifi_camera.mylibrary.foot_banner.MyFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Home extends AppCompatActivity {


    private ActivityHomeBinding binding;
    private ConstraintLayout choosingCl;//正在被选择的cl

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //设置状态栏颜色
        getWindow().setStatusBarColor(getResources().getColor(R.color.background_blue));
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
        //创建文件夹
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/");
        if (!file.exists()) {
            file.mkdirs();
        }
        binding.clBill
                .setOnClickListener(v -> {
                    if (choosingCl == binding.clBill) {
                        return;
                    }
                    choosingCl = binding.clBill;
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, new BillFragment()).commit();

                });
        binding.clHome
                .setOnClickListener(v -> {
                    if (choosingCl == binding.clHome) {
                        return;
                    }
                    choosingCl = binding.clHome;
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, new HomeFragment()).commit();
                });
        binding.clClassify
                .setOnClickListener(v -> {
                    if (choosingCl == binding.clClassify) {
                        return;
                    }
                    choosingCl = binding.clClassify;
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, new ClassifyFragment()).commit();
                });
        binding.clMy
                .setOnClickListener(v -> {
                    if (choosingCl == binding.clMy) {
                        return;
                    }
                    choosingCl = binding.clMy;
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, new MyFragment()).commit();
                });
        binding.clCamera
                .setOnClickListener(v -> {
//                    if (choosingCl == binding.clCamera) {
//                        return;
//                    }
//                    choosingCl = binding.clCamera;
//                    getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.frameLayout, new CarmerFragment()).commit();
                    startActivity(new Intent(this, MainActivity.class));
                    //跳转到摄像头
//                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
//                        Log.d("进入：", "requestPermissions");
//                    }
//                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
//                        Log.d("进入：", "requestPermissions");
//                    }
//                    //无视错误
//                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//                    StrictMode.setVmPolicy(builder.build());
//                    builder.detectFileUriExposure();
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/" + "test.png"));
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//                    startActivityForResult(intent, 1);
                });
    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            Log.e("TAG", "拍摄成功");
//            wifination.naSnapPhoto(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/" + "wifi.jpg", wifination.TYPE_ONLY_PHONE);
//            //压缩文件
//            Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/" + "test.png");
//            qualityCompress(bitmap, new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/" + "test-yasuo.jpeg"));
//            //提示图库更新
//            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//            Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/"));
//            intent.setData(uri);
//            sendBroadcast(intent);
//        }
//    }

    public void getTestWifi() {
        final ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            builder = new NetworkRequest.Builder();
            builder.addCapability(NET_CAPABILITY_INTERNET);
            builder.addTransportType(TRANSPORT_CELLULAR);
            NetworkRequest build = builder.build();
            connectivityManager.requestNetwork(build, new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    super.onAvailable(network);
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    Log.e("TAG", "当前使用的网络：" + activeNetworkInfo.getTypeName());
                    try {

                        if (Build.VERSION.SDK_INT >= 23) {
                            connectivityManager.bindProcessToNetwork(network); //这句话要加上哈，不然设置不生效
                        } else {// 23后这个方法舍弃了
                            ConnectivityManager.setProcessDefaultNetwork(network);
                        }
                        sentPost("wifi.jpg", Home.this);
                        //查看当前使用的网络

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

    public void sentPost(String file_name, Context context) {
        new Thread(() -> {
            try {
                上传结果 rel = 发送图片到服务器(file_name);
                识别结果 a = 请求识别(rel.data);
                runOnUiThread(() -> {
                    Toast.makeText(context, "识别成功", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(context, ResultActivity.class);
                    in.putExtra("data", new Gson().toJson(a));
                    startActivity(in);
                });

            } catch (Exception e) {
                Log.e("TAG", "错误结果：" + e);
            }
        }).start();
    }

    public 上传结果 发送图片到服务器(String file_name) throws Exception {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/" + file_name;
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
    public 识别结果 请求识别(String data) throws Exception {
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
    private void qualityCompress(Bitmap bmp, File file) {
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