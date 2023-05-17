package com.wifi_camera.mylibrary;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.joyhonest.wifination.wifination;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyMainActivity extends Activity {


    private final String TAG = "MainActivity";
    private String sLocalPath = "";

    boolean bExitTest;

    Button Snap_Btn;
    Button Record_Btn;
    Button Stop_Btn;
    Button SetMode0;
    Button SetMode1;
    Button SetMode2;
    Button ReadMode;


    TextView RecortTime_txt;
    private int nSnapCount = 0;


    boolean bSetDisp = false;


    private ImageView imageView;

    private int nLedMode = 1;

    private HandlerThread thread1;
    private Handler openHandler;
    private Runnable openRunnable = new Runnable() {
        @Override
        public void run() {

            wifination.naSetRevBmp(true);
            wifination.naInit("");
            bGetImage = false;
        }
    };


    boolean bRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        F_CreateLocalDir();      //Create a directory for your photos and videos
        //Android 10 or later: Obtain permissions

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        thread1 = new HandlerThread("MyHandlerThread");
        thread1.start(); //创建一个HandlerThread并启动它
        openHandler = new Handler(thread1.getLooper());

        RecortTime_txt = findViewById(R.id.RecortTime_txt);
        RecortTime_txt.setVisibility(View.INVISIBLE);

        Snap_Btn = (Button) findViewById(R.id.photo_btn);
        Record_Btn = (Button) findViewById(R.id.Record_btn);
        Stop_Btn = (Button) findViewById(R.id.stop_btn);
        SetMode0 = (Button) findViewById(R.id.SetMode0);
        SetMode1 = findViewById(R.id.SetMode1);
        SetMode2 = findViewById(R.id.SetMode2);
        ReadMode = findViewById(R.id.ReadMode);


        imageView = (ImageView) findViewById(R.id.imageView);
        Snap_Btn.setOnClickListener(view -> {
            Snap_Btn.setTextColor(0xFFFF0000);
            new Handler().postDelayed(() -> Snap_Btn.setTextColor(0xFF000000), 200);
            String recFilename = getFileNameFromDate(false);
            Log.e(TAG, "onCreate: " + recFilename);
            wifination.naSnapPhoto(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Buliao/" + "wifi.jpg", wifination.TYPE_ONLY_PHONE);

        });
        Record_Btn.setOnClickListener(view -> {
            if (wifination.isPhoneRecording()) {
                wifination.naStopRecord_All();
            } else {
                String recFilename = getFileNameFromDate(true);
                wifination.naStartRecord(recFilename, wifination.TYPE_ONLY_PHONE);
            }
        });

        Stop_Btn.setOnClickListener(view -> onBackPressed());
        SetMode0.setOnClickListener(v -> {
            nLedMode = 0;
            wifination.naSetLedMode(0);
        });


        SetMode1.setOnClickListener(v -> {
            nLedMode = 1;
            wifination.naSetLedMode(1);
        });

        SetMode2.setOnClickListener(v -> {
            nLedMode = 2;
            wifination.naSetLedMode(2);
        });
        ReadMode.setOnClickListener(v -> {
            wifination.naGetLedMode();
        });
        wifination.appContext = getApplicationContext();  // getApplicationContext();
        F_OpenStream();
        wifination.naGetLedMode();
        F_StartReadBattery(true);
        EventBus.getDefault().register(this);  // important！！！
    }


    private void F_BacktStartActivity() {
        bExitTest = true;
        F_StartReadBattery(false);
        wifination.naStopRecord_All();
        wifination.naStop();
    }


    @Override
    public void onBackPressed() {
        F_BacktStartActivity();
        super.onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);    // important！！！
        F_StartReadBattery(false);
        openHandler.removeCallbacksAndMessages(null);
        thread1.quit();
    }


    private void F_OpenStream() {
        openHandler.removeCallbacksAndMessages(null);
        openHandler.post(openRunnable);
    }


    private final Handler readBatteryHandler = new Handler(Looper.getMainLooper());
    private final Runnable readBatteryRunnable = new Runnable() {
        @Override
        public void run() {
            wifination.na4225_ReadStatus();    //读取电量
            readBatteryHandler.postDelayed(this, 500);
        }
    };

    private void F_StartReadBattery(boolean b) {
        if (b) {
            readBatteryHandler.removeCallbacksAndMessages(null);
            readBatteryHandler.post(readBatteryRunnable);
        } else {
            readBatteryHandler.removeCallbacksAndMessages(null);
        }
    }


    public String getFileNameFromDate(boolean bVideo)   //获取文件名称
    {

        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS", Locale.getDefault());
        String strDate = f.format(d);

        String ext = "mp4";
        if (!bVideo) {
            ext = "jpg";
        }

        String recDir = sLocalPath;
        File dirPath = new File(recDir);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        return String.format(Locale.ENGLISH, "%s/%s.%s", recDir, strDate, ext);
    }


    public void F_CreateLocalDir() {
        String sVendor = "JH_Demo";
        Context singleton = getApplicationContext();
        File file = singleton.getExternalFilesDir(sVendor);
        if (file != null) {
            sLocalPath = file.getAbsolutePath();
        }
    }


    Handler DispRecordHandler = new Handler();
    Runnable DispRecordRunnable = new Runnable() {
        @Override
        public void run() {
            int nRec = wifination.naGetRecordTime() / 1000;
            int nMin = nRec / 60;
            int nSec = nRec % 60;
            String str = String.format(Locale.ENGLISH, "%02d:%02d", nMin, nSec);
            RecortTime_txt.setText(str);
            DispRecordHandler.postDelayed(this, 250);
        }
    };

    private void F_DispRecordTime(boolean b) {
        if (b) {
            DispRecordHandler.removeCallbacksAndMessages(null);
            RecortTime_txt.setText("00:00");
            RecortTime_txt.setVisibility(View.VISIBLE);
            DispRecordHandler.post(DispRecordRunnable);
        } else {
            DispRecordHandler.removeCallbacksAndMessages(null);
            RecortTime_txt.setVisibility(View.GONE);
        }

    }

    //SDK通过Android EventBus来传递 模块状态。@Subscriber(tag= "SDStatus_Changed")
    //模块状态改变，SDStatus_Changed(Integer  nStatusA) 会自动调用，您在这函数中处理相关逻辑即可
    //        //#define  bit0_OnLine            1
    //        //#define  bit1_LocalRecording    2

    @Subscriber(tag = "SDStatus_Changed")
    private void SDStatus_Changed(Integer nStatusA) {
        int nStatus = nStatusA;
        if ((nStatus & 0x0002) != 0) {
            if (!bRecording) {
                F_DispRecordTime(true);
            }
            bRecording = true;
            Record_Btn.setTextColor(0xFFFF0000);  //Recording
        } else {
            if (bRecording) {
                F_DispRecordTime(false);
            }
            bRecording = false;
            Record_Btn.setTextColor(0xFF000000); //Stoprecording
        }
    }


    //如果 wifination.naSetRevBmp(true);  每一帧图片在  ReviceBMP 中传到应用层。

    boolean bGetImage = false;

    @Subscriber(tag = "ReceiveBMP")
    private void ReceiveBMP(Bitmap bmp) {
        /*
            如果之前 naSetREvBmp(true);
            那么，SDK就不会显示视频，而是把每一帧发送到此，由用户应用来处理。
            （注意，在此要尽快处理，不要太耗时，否则会影响显示的流畅度)）
         */
        bGetImage = true;
        imageView.setImageBitmap(bmp);
    }


    private void F_SnapPhoto() {
        String recFilename = getFileNameFromDate(false);
        wifination.naSnapPhoto(recFilename, wifination.TYPE_ONLY_PHONE);
    }

    @Subscriber(tag = "Key_Pressed")       //设备上的按键
    private void Key_Pressed(Integer n) {
        int nKey = n;
        Log.e(TAG, "Key = " + n);
        if (n == 1) {
            nSnapCount = 1;
            F_SnapPhoto();
        } else if (n == 2) {
            nSnapCount = 2;
            F_SnapPhoto();
        } else if (n == 3) {
            nSnapCount = 3;
            F_SnapPhoto();
        }
        //n ！=0 为按键值。
        //n = 0 按键松开
        // 在这里 根据键值 来做不同功能。。。。。。.....
    }

    @Subscriber(tag = "onGetBattery")       //电量返回
    private void onGetBattery(Integer nBattery_) {
        int nBattery = nBattery_;
        Log.e(TAG, "Battery = " + nBattery);
    }

    @Subscriber(tag = "onGetLedMode")
    private void onGetLedMode(Integer n) {
        nLedMode = n;
        Log.e(TAG, "LedMode = " + n);
    }

    @Subscriber(tag = "SavePhotoOK")   //拍照一张照片或者录像完成OK
    private void SavePhotoOK(String Sn) {
        String sType = Sn.substring(0, 2);
        String sName = Sn.substring(2, Sn.length());
        int nVideo = Integer.parseInt(sType);
        if (nVideo == 1) {  //视频录制完成

        }
        if (nVideo == 0) //一张相片拍照完成。
        {
            if (nSnapCount > 0) {
                nSnapCount--;
                if (nSnapCount > 0) {
                    F_SnapPhoto();
                }
            }
        }
    }


}