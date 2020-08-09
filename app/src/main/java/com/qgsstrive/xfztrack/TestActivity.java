package com.qgsstrive.xfztrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qgsstrive.xfztrack.bjxt.DrawTop;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import util.HexUtil;

public class TestActivity extends SerialPortActivity {

    private Context mContext = null;

    MyHandler myHandler = new MyHandler(new WeakReference<TestActivity>(this));

    private boolean gpscar = true;
    private boolean gpsmap = false;
    private DrawTop map;

    private Timer canvasTimer = null;
    private TimerTask canvasTimerTask = null;

    private Timer gpsTrainTimer = null;
    private TimerTask gpsTrainTimerTask = null;
    private Button mSend;
    private Button mReceived;
    private TextView mText;

    String data = "AA AA A7 01 0E 0F 10 85 A0 14 00 01 00";
    private String mEncodeHexStr;
    private String mSubstring;

    /**
     * 该类用来处理handler
     */
    static class MyHandler extends Handler {


        private WeakReference<TestActivity> activity;
        int num = 1;
        int pianyi = 5;
        boolean open = true;

        public MyHandler(WeakReference<TestActivity> activity) {
            this.activity = activity;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x1233://移动画布
                    if (activity.get().map.getGpsPointTwo().getX() > 300 && activity.get().map.getGpsPointTwo().getX() < 700) {
                        activity.get().map.setScrollX(pianyi);
                    } else {
                        activity.get().gpscar = true;
                        activity.get().gpsmap = false;
                    }

                    if (!activity.get().gpscar) {
                        if (activity.get().map.getGpsPointTwo().getX() < 700) {
                            activity.get().gpscar = true;
                        }
                        pianyi = pianyi + 5;
                        activity.get().map.setScrollX(pianyi);
                    }
                    break;
                case 0x1234:
                    //该消息用来刷新画布，呈现车辆移动的效果
                    /*if (activity.get().gpscar){
                        activity.get().map.invalidate();
                        activity.get().stopGpsTrainTimer();
                        Log.i("TAG",String.valueOf(activity.get().map.getGpsPointTwo().getX()));
                    }*/

                    if (activity.get().gpscar) {
                        if (activity.get().map.getGpsPointTwo().getX() > 300 && activity.get().map.getGpsPointTwo().getX() < 700) {
                            activity.get().gpscar = false;
                        }
                        if (activity.get().map.getGpsPointTwo().getX() > 1000) {
                            activity.get().stopTimer();
                        }
                        activity.get().map.invalidate();
                    }
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mContext = TestActivity.this;

        initView();

        //定时器，用来移动控件
        //startTimer();
    }

    @Override
    protected void onDataReceived(final byte[] buffer, final int size, final int type) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("testData", "ddddddddd");
                try {
                    if (type == 485) {
                        mEncodeHexStr = HexUtil.encodeHexStr(buffer, false, size);
                        mSubstring = mEncodeHexStr.substring(mEncodeHexStr.length() - 2, mEncodeHexStr.length());
                        Log.i("testData","485555数据: "+ HexUtil.encodeHexStr(buffer,false,size));
//                    Log.i("这是485收到的数据",MainActivity.this.toString(buffer,size,0));
                        Log.i("TAG串口打开",String.valueOf(type));
                        Log.i("TAGbuffer[0]",String.valueOf(buffer[0]));
                        Log.i("TAG_size","定位协议的长度"+String.valueOf(size));
                        Log.i("hexStr", mEncodeHexStr + "    hexStr");
                        Log.i("substring", mSubstring + "    substring");
                        mText.setText(mEncodeHexStr);
                    } else if (type == 232) {
                        Toast.makeText(mContext, "232串口", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }
        });
    }

    private void initView() {
        map = findViewById(R.id.draw);
        mSend = findViewById(R.id.send);
        mReceived = findViewById(R.id.received);
        mText = findViewById(R.id.received_text);
        mText.setText("AB12AC34");
        String s = mText.getText().toString();
        String substring = s.substring(2, 4);
        int length = mText.length();
        Log.e("123456",substring+"");
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendHexString(data.replaceAll("\\s*", ""), "485");
            }
        });
    }

    /**
     * 打开定时器
     */
    private void startTimer() {
        if (canvasTimer == null) {
            canvasTimer = new Timer();
        }
        if (canvasTimerTask == null) {
            canvasTimerTask = new TimerTask() {
                @Override
                public void run() {
                    myHandler.sendEmptyMessage(0x1233);
                    myHandler.sendEmptyMessage(0x1234);
                }
            };
        }
        if (canvasTimer != null && canvasTimerTask != null) {
            canvasTimer.schedule(canvasTimerTask, 0, 1000);
        }
    }

    /**
     * 关闭定时器
     */
    private void stopTimer() {
        if (canvasTimer != null) {
            canvasTimer.cancel();
            canvasTimer = null;  //这里需要将定时器置为空，否则再次启动定时器会报错
        }
        if (canvasTimerTask != null) {
            canvasTimerTask.cancel();
            canvasTimerTask = null;
        }
    }

    /**
     * 将字节数转换为int类型数据
     * @param b
     * @return
     */
    private int toInt(byte b){
        if(b<0){
            return b+256;
        }else{
            return b;
        }
    }

    /**
     * 校验数据
     * @param buffer 接收到的数据
     * @param size 数据长度
     */
    private boolean checkData(byte[] buffer, int size) {
        String strData = HexUtil.encodeHexStr(buffer,false,size);
        Log.i("TAGstrData收到的数据",strData);
        /**
         * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
         * A 8 0 1 1 2 3 4 5 6  7  8  0  0  A  A
         *
         */
        //total里存放的是累加和
        int total = 0;
        for (int i = 0;i<strData.length()-2;i+=2){
            //strB.append("0x").append(strData.substring(i,i+2));  //0xC30x3C0x010x120x340x560x780xAA
            total = total + Integer.parseInt(strData.substring(i,i+2),16);

        }
        //noTotal为累加和取反加一
        int noTotal = ~total +1;
        Log.i("total",String.valueOf(noTotal));
        String hex = Integer.toHexString(noTotal).toUpperCase();
        Log.i("TAGhex",hex);
        String key = hex.substring(hex.length()-2);
        Log.i("TAG校验码key",key);
        Log.i("TAGhex",key);
        if (key.equals(strData.substring(strData.length()-2))){
            Log.i("jiaoyan","校验成功");
            return true;
        }else {

            Log.i("jiaoyan","校验失败");
            return false;
        }
        //Log.i("total", hex.substring(hex.length()-2));

    }
}
