package com.qgsstrive.xfztrack;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.qgsstrive.xfztrack.bjxt.CustomMap;
import com.qgsstrive.xfztrack.bjxt.DefinitionMap;
import com.qgsstrive.xfztrack.bjxt.DrawMap;
import com.qgsstrive.xfztrack.bjxt.DrawTop;
import com.qgsstrive.xfztrack.bjxt.GPSPointF;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import util.ByteUtil;
import util.HexUtil;

public class MainActivity extends SerialPortActivity implements View.OnClickListener {

    private Context mContext = null;
    //private DrawMap mDrawmap;
    private TextView mTitle;
    private PopupWindow mPopupWindow;
    private DrawTop mDrawTop;
    private Timer gpsTrainTimer = null;
    private TimerTask gpsTrainTimerTask = null;

    String data = "41 54 2B 54 58 44 3D 01 00 10 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F 10 67 0D 0A";
    private String mEncodeHexStr;
    private String mTail;

    static int x, y;
    public static GPSPointF gpsPoint = new GPSPointF(x, y);
    private DefinitionMap mCustommap;
    private String mTrack;
    boolean aa = false;
    boolean bb = false;
    boolean cc = false;
    boolean dd = false;
    boolean ee = false;
    boolean ff = false;
    boolean a1 = false;
    boolean a2 = false;
    boolean a3 = false;
    boolean a4 = false;
    boolean a5 = false;
    boolean a6 = false;
    private String mDistance;
    //小车纵向减
    int disparity = 25;
    //小车横向减
    int transverse = 20;
    int mTime = 800;
    int tota = 100;
    private String mHeader;
    private TextView mTwotext;
    private TextView mFirsttext;

    @Override
    protected void onDataReceived(final byte[] buffer, final int size, final int type) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("testData", "ddddddddd");
                try {
                    if (type == 485) {
                        String toString = buffer.toString();
                        char[] chars = HexUtil.encodeHex(buffer);
                        mEncodeHexStr = ByteUtil.bytes2HexString(buffer, size);
                        Log.e("121212",mEncodeHexStr+"      121212");
                        //mEncodeHexStr = HexUtil.encodeHexStr(buffer, false, size);
                        //int length = mEncodeHexStr.length();
                        mHeader = mEncodeHexStr.substring(0, 2);
                        //mTail = mEncodeHexStr.substring(mEncodeHexStr.length() - 2, mEncodeHexStr.length());
                        //Log.i("TAG串口打开", mHeader + "帧头"+length);
                        //Log.i("testData", "485数据: " + HexUtil.encodeHexStr(buffer, false, size));
//                    Log.i("这是485收到的数据",MainActivity.this.toString(buffer,size,0));
                        /*Log.i("TAG串口打开", String.valueOf(type));
                        Log.i("TAGbuffer[0]", String.valueOf(buffer[0]));
                        Log.i("TAG_size", "定位协议的长度" + String.valueOf(size));
                        Log.i("hexStr", mEncodeHexStr + "    hexStr");
                        Log.i("substring", mTail + "    substring");*/
                        mTitle.setText(mEncodeHexStr);
                        //行车数
                        String car = mEncodeHexStr.substring(4, 6);
                        //股道数
                        mTrack = mEncodeHexStr.substring(6, 8);
                        //距离
                        mDistance = mEncodeHexStr.substring(8, 10);
                        int hh = Integer.parseInt(mDistance);
                        int h = Integer.parseInt(mDistance, 16);
                        int track1 = Integer.parseInt(mTrack, 16);
                        String valueOf = String.valueOf(track1);
                        Log.e("十六进制十进制", h + "    " + track1 + "    " + mEncodeHexStr + "    十进制");
                        mDrawTop.invalidate();
                        if (mHeader.equals("A7")) {
                            if (valueOf.equals("05")) {
                                bb = false;
                                cc = false;
                                dd = false;
                                ee = false;
                                ff = false;
                                a1 = false;
                                a2 = false;
                                a3 = false;
                                a4 = false;
                                a5 = false;
                                a6 = false;
                                if (aa == false) {
                                    mDrawTop.setTranslationX(50 - transverse);
                                    mDrawTop.setTranslationY(90 - disparity);
                                    aa = true;
                                    float translationX = mDrawTop.getTranslationX();
                                    float translationY = mDrawTop.getTranslationY();
                                    ObjectAnimator animator
                                            = ObjectAnimator.ofFloat(mDrawTop, "translationX", 30 + h * 9.2f);
                                    animator.setDuration(mTime);
                                    animator.start();
                                    Log.e("123456", translationX + "  05  " + translationY);
                                } else {
                                    float translationX = mDrawTop.getTranslationX();
                                    float translationY = mDrawTop.getTranslationY();
                                    ObjectAnimator animator
                                            = ObjectAnimator.ofFloat(mDrawTop, "translationX", 30 + h * 9.2f);
                                    animator.setDuration(mTime);
                                    animator.start();
                                    Log.e("123456", translationX + "  05  " + translationY);
                                }
                            }
                            if (valueOf.equals("06")) {
                                aa = false;
                                cc = false;
                                dd = false;
                                ee = false;
                                ff = false;
                                a1 = false;
                                a2 = false;
                                a3 = false;
                                a4 = false;
                                a5 = false;
                                a6 = false;
                                if (bb == false) {
                                    mDrawTop.setTranslationX(50 - transverse);
                                    mDrawTop.setTranslationY(150 - disparity);
                                    bb = true;
                                    float translationX = mDrawTop.getTranslationX();
                                    float translationY = mDrawTop.getTranslationY();
                                    ObjectAnimator animator
                                            = ObjectAnimator.ofFloat(mDrawTop, "translationX", 30 + h * 9.2f);
                                    animator.setDuration(mTime);
                                    animator.start();
                                } else {
                                    float translationX = mDrawTop.getTranslationX();
                                    float translationY = mDrawTop.getTranslationY();
                                    ObjectAnimator animator
                                            = ObjectAnimator.ofFloat(mDrawTop, "translationX", 30 + h * 9.2f);
                                    animator.setDuration(mTime);
                                    animator.start();
                                    Log.e("123456", translationX + "  06  " + translationY);
                                }
                            }
                            if (mTrack.equals("07")) {
                                aa = false;
                                bb = false;
                                dd = false;
                                ee = false;
                                ff = false;
                                a1 = false;
                                a2 = false;
                                a3 = false;
                                a4 = false;
                                a5 = false;
                                a6 = false;
                                if (cc == false) {
                                    mDrawTop.setTranslationX(50 - transverse);
                                    mDrawTop.setTranslationY(210 - disparity);
                                    cc = true;
                                    float translationX = mDrawTop.getTranslationX();
                                    float translationY = mDrawTop.getTranslationY();
                                    ObjectAnimator animator
                                            = ObjectAnimator.ofFloat(mDrawTop, "translationX", 30 + h * 9.2f);
                                    animator.setDuration(mTime);
                                    animator.start();
                                } else {
                                    float translationX = mDrawTop.getTranslationX();
                                    float translationY = mDrawTop.getTranslationY();
                                    ObjectAnimator animator
                                            = ObjectAnimator.ofFloat(mDrawTop, "translationX", 30 + h * 9.2f);
                                    animator.setDuration(mTime);
                                    animator.start();
                                    Log.e("123456", translationX + "  07  " + translationY);
                                }
                            }
                            if (mTrack.equals("08")) {
                                aa = false;
                                bb = false;
                                cc = false;
                                ee = false;
                                ff = false;
                                a1 = false;
                                a2 = false;
                                a3 = false;
                                a4 = false;
                                a5 = false;
                                a6 = false;
                                if (dd == false) {
                                    mDrawTop.setTranslationX(50 - transverse);
                                    mDrawTop.setTranslationY(270 - disparity);
                                    dd = true;
                                    float translationX = mDrawTop.getTranslationX();
                                    float translationY = mDrawTop.getTranslationY();
                                    ObjectAnimator animator
                                            = ObjectAnimator.ofFloat(mDrawTop, "translationX", 30 + h * 9.2f);
                                    animator.setDuration(mTime);
                                    animator.start();
                                } else {
                                    float translationX = mDrawTop.getTranslationX();
                                    float translationY = mDrawTop.getTranslationY();
                                    ObjectAnimator animator
                                            = ObjectAnimator.ofFloat(mDrawTop, "translationX", 30 + h * 9.2f);
                                    animator.setDuration(mTime);
                                    animator.start();
                                    Log.e("123456", translationX + "  08  " + translationY);
                                }
                            }
                            if (mTrack.equals("09")) {
                                aa = false;
                                bb = false;
                                cc = false;
                                dd = false;
                                ff = false;
                                a1 = false;
                                a2 = false;
                                a3 = false;
                                a4 = false;
                                a5 = false;
                                a6 = false;
                                if (ee == false) {
                                    mDrawTop.setTranslationX(50 - transverse);
                                    mDrawTop.setTranslationY(330 - disparity);
                                    ee = true;
                                    float translationX = mDrawTop.getTranslationX();
                                    float translationY = mDrawTop.getTranslationY();
                                    ObjectAnimator animator
                                            = ObjectAnimator.ofFloat(mDrawTop, "translationX", 30 + h * 9.2f);
                                    animator.setDuration(mTime);
                                    animator.start();
                                } else {
                                    float translationX = mDrawTop.getTranslationX();
                                    float translationY = mDrawTop.getTranslationY();
                                    ObjectAnimator animator
                                            = ObjectAnimator.ofFloat(mDrawTop, "translationX", 30 + h * 9.2f);
                                    animator.setDuration(mTime);
                                    animator.start();
                                    Log.e("123456", translationX + "  09  " + translationY);

                                }
                            }
                            if (valueOf.equals("10")) {
                                aa = false;
                                bb = false;
                                cc = false;
                                dd = false;
                                ee = false;
                                a1 = false;
                                a2 = false;
                                a3 = false;
                                a4 = false;
                                a5 = false;
                                a6 = false;
                                if (ff == false) {
                                    mDrawTop.setTranslationX(110 - transverse);
                                    mDrawTop.setTranslationY(330);
                                    ff = true;
                                    if (h <= 5) {
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 90 + h * 8f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 310 + h * 12f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    } else {
                                        mDrawTop.setTranslationY(370);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 150 + h * 8f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                    }
                                } else {
                                    if (h <= 5) {
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 90 + h * 8f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 310 + h * 12f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    } else {
                                        mDrawTop.setTranslationY(370);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 150 + h * 8f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                    }
                                }
                            }
                            if (valueOf.equals("11")) {
                                aa = false;
                                bb = false;
                                cc = false;
                                dd = false;
                                ee = false;
                                ff = false;
                                a2 = false;
                                a3 = false;
                                a4 = false;
                                a5 = false;
                                a6 = false;
                                if (a1 == false) {
                                    mDrawTop.setTranslationX(190 - transverse);
                                    mDrawTop.setTranslationY(390);
                                    a1 = true;
                                    if (h <= 5) {
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 170 + h * 8f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 370 + h * 12f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    } else {
                                        mDrawTop.setTranslationY(430);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 150 + h * 8f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                    }
                                } else {
                                    if (h <= 5) {
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 170 + h * 8f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 370 + h * 12f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    } else {
                                        mDrawTop.setTranslationY(430);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 150 + h * 8f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                    }
                                }
                            }
                            if (valueOf.equals("12")) {
                                aa = false;
                                bb = false;
                                cc = false;
                                dd = false;
                                ee = false;
                                ff = false;
                                a1 = false;
                                a3 = false;
                                a4 = false;
                                a5 = false;
                                a6 = false;
                                if (a2 == false) {
                                    mDrawTop.setTranslationX(290 - transverse);
                                    mDrawTop.setTranslationY(450);
                                    a2 = true;
                                    if (h <= 5) {
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 270 + h * 8f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 430 + h * 12f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    } else if (h > 5 && h < 90) {
                                        mDrawTop.setTranslationY(490);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 290 + h * 6.7f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                    } else if (h >= 90 && h < 100) {
                                        mDrawTop.setTranslationY(490);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 890 + h * 0.2f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 510 - h * 0.6f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    }
                                } else {
                                    if (h <= 5) {
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 270 + h * 8f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 430 + h * 12f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    } else if (h > 5 && h < 90) {
                                        mDrawTop.setTranslationY(490);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 290 + h * 6.7f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                    } else if (h >= 90 && h < 100) {
                                        mDrawTop.setTranslationY(490);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 890 + h * 0.2f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 510 - h * 0.6f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    }
                                }
                            }
                            if (valueOf.equals("13")) {
                                aa = false;
                                bb = false;
                                cc = false;
                                dd = false;
                                ee = false;
                                ff = false;
                                a1 = false;
                                a2 = false;
                                a4 = false;
                                a5 = false;
                                a6 = false;
                                if (a3 == false) {
                                    mDrawTop.setTranslationX(50 - transverse);
                                    mDrawTop.setTranslationY(390);
                                    a3 = true;
                                    if (h <= 20) {
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 30 + h * 11.5f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 370 + h * 9f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    } else {
                                        mDrawTop.setTranslationY(550);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 100 + h * 8f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                    }
                                } else {
                                    if (h <= 20) {
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 30 + h * 11.5f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 370 + h * 9f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    } else {
                                        mDrawTop.setTranslationY(550);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 100 + h * 8f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                    }
                                }
                            }
                            if (valueOf.equals("14")) {
                                aa = false;
                                bb = false;
                                cc = false;
                                dd = false;
                                ee = false;
                                ff = false;
                                a1 = false;
                                a2 = false;
                                a3 = false;
                                a5 = false;
                                a6 = false;
                                if (a4 == false) {
                                    mDrawTop.setTranslationX(85 - transverse);
                                    mDrawTop.setTranslationY(420);
                                    a4 = true;
                                    if (h <= 15) {
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 65 + h * 13f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 400 + h * 14);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    } else {
                                        mDrawTop.setTranslationY(610);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 160 + h * 6.1f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                    }
                                } else {
                                    if (h <= 15) {
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 65 + h * 13f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 400 + h * 14f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    } else {
                                        mDrawTop.setTranslationY(610);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 160 + h * 6.1f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                    }
                                }
                            }
                            if (valueOf.equals("15")) {
                                aa = false;
                                bb = false;
                                cc = false;
                                dd = false;
                                ee = false;
                                ff = false;
                                a1 = false;
                                a2 = false;
                                a3 = false;
                                a4 = false;
                                a6 = false;
                                if (a5 == false) {
                                    mDrawTop.setTranslationX(50);
                                    mDrawTop.setTranslationY(450 - disparity);
                                    a5 = true;
                                    if (h <= 20) {
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 30 + h * 11.5f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 430 + h * 12f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    } else if (h > 20 && h <= 90) {
                                        mDrawTop.setTranslationY(670);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 90 + h * 7.6f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                    } else if (h > 90) {
                                        mDrawTop.setTranslationY(670);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 800 + h * 0.4f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 690 - h * 1.3f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    }
                                } else {
                                    if (h <= 20) {
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 30 + h * 11.5f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 430 + h * 12f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    } else if (h > 20 && h <= 90) {
                                        mDrawTop.setTranslationY(670);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 90 + h * 7.6f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                    } else if (h > 90) {
                                        mDrawTop.setTranslationY(670);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 800 + h * 0.4f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 690 - h * 1.1f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    }
                                }
                            }
                            if (valueOf.equals("16")) {
                                aa = false;
                                bb = false;
                                cc = false;
                                dd = false;
                                ee = false;
                                ff = false;
                                a1 = false;
                                a2 = false;
                                a3 = false;
                                a4 = false;
                                a5 = false;
                                if (a6 == false) {
                                    a6 = true;
                                    mDrawTop.setTranslationX(75 - transverse);
                                    mDrawTop.setTranslationY(480);
                                    if (h <= 15) {
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 55 + h * 13.7f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 460 + h * 18f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    } else if (h > 15 && h <= 80) {
                                        mDrawTop.setTranslationY(730);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 250 + h * 7f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        Log.e("123456", translationX + "  16  " + translationY);
                                    } else if (h > 80) {
                                        //mDrawTop.setTranslationX(830);
                                        mDrawTop.setTranslationY(730);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 830 + h * 0.9f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 750 - h * 1.8f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    }
                                } else {
                                    if (h <= 15) {
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 55 + h * 13.7f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 460 + h * 18f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                    } else if (h > 15 && h <= 80) {
                                        mDrawTop.setTranslationY(730);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 250 + h * 7f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        Log.e("123456", translationX + "  16  " + translationY);
                                    } else if (h > 80) {
                                        //mDrawTop.setTranslationX(830);
                                        mDrawTop.setTranslationY(730);
                                        float translationX = mDrawTop.getTranslationX();
                                        float translationY = mDrawTop.getTranslationY();
                                        ObjectAnimator animator
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", 830 + h * 0.9f);
                                        animator.setDuration(mTime);
                                        animator.start();
                                        ObjectAnimator animator1
                                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", 750 - h * 1.8f);
                                        animator1.setDuration(mTime);
                                        animator1.start();
                                        Log.e("123456", translationX + "  16  " + translationY);
                                    }
                                }
                            }
                        }
                    } else if (type == 232) {
                        Toast.makeText(mContext, "232串口", Toast.LENGTH_SHORT).show();
                        //String hexStr = HexUtil.encodeHexStr(buffer, false, size);
                        String toString = buffer.toString();
                        char[] chars = HexUtil.encodeHex(buffer);
                        String bytes2HexString = ByteUtil.bytes2HexString(buffer, size);
                        mTwotext.setText(bytes2HexString+"");
                        Log.e("121212",bytes2HexString+"      121212");
                    }
                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        /*gpsPoint.setX(a);
        gpsPoint.setY(b);*/
        //隐藏状态栏和导航栏
        setSystemUIVisible(false);
        //初始化控件
        initView();
        //scaleWindow();

    }

    private void initView() {
        mCustommap = findViewById(R.id.custommap);
        mTitle = findViewById(R.id.title_text);
        mTitle.setText("香坊站");
        mTitle.setOnClickListener(this);
        mDrawTop = findViewById(R.id.drawtop);
        mFirsttext = findViewById(R.id.onetext);
        mTwotext = findViewById(R.id.twotext);
        mFirsttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dat="AA 55";
                sendHexString(dat.replaceAll("\\s*",""),"232");
            }
        });
        /*mDrawTop.setTranslationX(50-20);
        mDrawTop.setTranslationY(90 - disparity);*/
    }

    /**
     * 隐藏状态栏和导航栏
     *
     * @param show boolean类型，true:显示  false ：隐藏
     */
    private void setSystemUIVisible(boolean show) {
        if (show) {
            int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            uiFlags |= 0x00001000;
            getWindow().getDecorView().setSystemUiVisibility(uiFlags);
        } else {
            int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            uiFlags |= 0x00001000;
            getWindow().getDecorView().setSystemUiVisibility(uiFlags);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_text:
                sendHexString(data.replaceAll("\\s*", ""), "485");
                // narrowWindow();
                /*//隐藏状态栏和导航栏
                //setSystemUIVisible(false);
                LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.gps, null);
                mPopupWindow = new PopupWindow(layout,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                mPopupWindow.setFocusable(true);
                mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
                //添加弹出、弹入的动画
                //mPopupWindow.setAnimationStyle(R.style.Popupwindow);
                int[] location = new int[2];
                v.getLocationOnScreen(location);
                mPopupWindow.showAtLocation(v, Gravity.LEFT | Gravity.BOTTOM, 0, -location[1]);
                //添加按键事件监听
                setButtonListeners(layout);*/
                break;
            /*case R.id.drawmap:
                //隐藏状态栏和导航栏
                setSystemUIVisible(false);
                //narrowWindow();
                break;*/
        }
    }

    /**
     * 设置popupWindow布局中按钮的点击事件
     */
    private void setButtonListeners(LinearLayout layout) {
        final TextView tv_showResult = layout.findViewById(R.id.mattersNeedingAttention);
        final LinearLayout linear = layout.findViewById(R.id.linear);
        tv_showResult.setText("123");
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
    }

    private void scaleWindow() {

        /** 设置缩放动画 */
        final ScaleAnimation animation = new ScaleAnimation(1f, 2f, 1f, 2f,
                512, 768);// 从相对于自身0.5倍的位置开始缩放，也就是从控件的位置缩放
        animation.setDuration(2000);//设置动画持续时间

        /** 常用方法 */
        //animation.setRepeatCount(int repeatCount);//设置重复次数
        animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        //animation.setStartOffset(long startOffset);//执行前的等待时间

        //mDrawmap.setAnimation(animation);
        /** 开始动画 */
        animation.startNow();
    }

    //缩小
    private void narrowWindow() {

        /** 设置缩放动画 */
        final ScaleAnimation animation = new ScaleAnimation(1f, 1f, 1f, 1f,
                512, 768);// 从相对于自身0.5倍的位置开始缩放，也就是从控件的位置缩放
        animation.setDuration(2000);//设置动画持续时间

        /** 常用方法 */
        //animation.setRepeatCount(int repeatCount);//设置重复次数
        animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        //animation.setStartOffset(long startOffset);//执行前的等待时间

        //mDrawmap.setAnimation(animation);
        /** 开始动画 */
        animation.startNow();
    }
}
