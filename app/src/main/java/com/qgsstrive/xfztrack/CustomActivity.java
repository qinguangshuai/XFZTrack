package com.qgsstrive.xfztrack;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.qgsstrive.xfztrack.bjxt.DrawTop;

import java.util.Timer;
import java.util.TimerTask;

import util.HexUtil;

public class CustomActivity extends AppCompatActivity {

    private Context mContext = null;
    //private DrawMap mDrawmap;
    private TextView mTitle;
    private PopupWindow mPopupWindow;
    private DrawTop mTrainicon;
    private Timer gpsTrainTimer = null;
    private TimerTask gpsTrainTimerTask = null;

    String data = "41 54 2B 54 58 44 3D 01 00 10 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F 10 67 0D 0A";
    private String mEncodeHexStr;
    private String mSubstring;
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
    int disparity = 30;
    int mTime = 1500;
    private DrawTop mTop;

    //@Override
    /*protected void onDataReceived(final byte[] buffer, final int size, final int type) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("testData", "ddddddddd");
                try {
                    if (type == 485) {
                        mEncodeHexStr = HexUtil.encodeHexStr(buffer, false, size);
                        mSubstring = mEncodeHexStr.substring(mEncodeHexStr.length() - 2, mEncodeHexStr.length());
                        Log.i("testData", "485555数据: " + HexUtil.encodeHexStr(buffer, false, size));
//                    Log.i("这是485收到的数据",MainActivity.this.toString(buffer,size,0));
                        Log.i("TAG串口打开", String.valueOf(type));
                        Log.i("TAGbuffer[0]", String.valueOf(buffer[0]));
                        Log.i("TAG_size", "定位协议的长度" + String.valueOf(size));
                        Log.i("hexStr", mEncodeHexStr + "    hexStr");
                        Log.i("substring", mSubstring + "    substring");
                        mTitle.setText(mEncodeHexStr);
                        //股道数
                        mTrack = mEncodeHexStr.substring(6, 8);
                        //行车数
                        String car = mEncodeHexStr.substring(4, 6);
                        //距离
                        mDistance = mEncodeHexStr.substring(8, 10);
                        int h = Integer.parseInt(mDistance, 16);
                        int track1 = Integer.parseInt(mTrack, 16);
                        String valueOf = String.valueOf(track1);
                        Log.e("十六进制十进制", track1 + "    十进制");
                        mTrainicon.invalidate();
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
                                mTrainicon.setTranslationX(100);
                                mTrainicon.setTranslationY(90 - disparity);
                                aa = true;
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 100 + h * 9.1f);
                                animator.setDuration(mTime);
                                animator.start();
                                Log.e("123456", translationX + "  05  " + translationY);
                            } else {
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 100 + h * 9.1f);
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
                                mTrainicon.setTranslationX(100);
                                mTrainicon.setTranslationY(150 - disparity);
                                bb = true;
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 100 + h * 9.1f);
                                animator.setDuration(mTime);
                                animator.start();
                            } else {
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 100 + h * 9.1f);
                                animator.setDuration(mTime);
                                animator.start();
                                Log.e("123456", translationX + "  06  " + translationY);
                            }
                        }
                        if (valueOf.equals("07")) {
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
                                mTrainicon.setTranslationX(160);
                                mTrainicon.setTranslationY(220 - disparity);
                                cc = true;
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 160 + h * 9.1f);
                                animator.setDuration(mTime);
                                animator.start();
                            } else {
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 160 + h * 9.1f);
                                animator.setDuration(mTime);
                                animator.start();
                                Log.e("123456", translationX + "  07  " + translationY);
                            }
                        }
                        if (valueOf.equals("08")) {
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
                                mTrainicon.setTranslationX(200);
                                mTrainicon.setTranslationY(280 - disparity);
                                dd = true;
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 200 + h * 9.1f);
                                animator.setDuration(mTime);
                                animator.start();
                            } else {
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 200 + h * 9.1f);
                                animator.setDuration(mTime);
                                animator.start();
                                Log.e("123456", translationX + "  08  " + translationY);
                            }
                        }
                        if (valueOf.equals("09")) {
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
                                mTrainicon.setTranslationX(220);
                                mTrainicon.setTranslationY(340 - disparity);
                                ee = true;
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 220 + h * 9.1f);
                                animator.setDuration(mTime);
                                animator.start();
                            } else {
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 220 + h * 9.1f);
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
                                mTrainicon.setTranslationX(250);
                                mTrainicon.setTranslationY(400 - disparity);
                                ff = true;
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 250 + h * 5.3f);
                                animator.setDuration(mTime);
                                animator.start();
                            } else {
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 250 + h * 5.3f);
                                animator.setDuration(mTime);
                                animator.start();
                                Log.e("123456", translationX + "  10  " + translationY);
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
                                mTrainicon.setTranslationX(280);
                                mTrainicon.setTranslationY(460 - disparity);
                                a1 = true;
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 280 + h * 5f);
                                animator.setDuration(mTime);
                                animator.start();
                            } else {
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 280 + h * 5f);
                                animator.setDuration(mTime);
                                animator.start();
                                Log.e("123456", translationX + "  11  " + translationY);
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
                                mTrainicon.setTranslationX(300);
                                mTrainicon.setTranslationY(520 - disparity);
                                a2 = true;
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 300 + h * 4f);
                                animator.setDuration(mTime);
                                animator.start();
                            } else {
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 300 + h * 4f);
                                animator.setDuration(mTime);
                                animator.start();
                                Log.e("123456", translationX + "  12  " + translationY);
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
                                mTrainicon.setTranslationX(280);
                                mTrainicon.setTranslationY(580 - disparity);
                                a3 = true;
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 280 + h * 4.1f);
                                animator.setDuration(mTime);
                                animator.start();
                            } else {
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 280 + h * 4.1f);
                                animator.setDuration(mTime);
                                animator.start();
                                Log.e("123456", translationX + "  13  " + translationY);
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
                                mTrainicon.setTranslationX(270);
                                mTrainicon.setTranslationY(630 - disparity);
                                a4 = true;
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 270 + h * 4.2f);
                                animator.setDuration(mTime);
                                animator.start();
                            } else {
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 270 + h * 4.2f);
                                animator.setDuration(mTime);
                                animator.start();
                                Log.e("123456", translationX + "  14  " + translationY);
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
                                mTrainicon.setTranslationX(270);
                                mTrainicon.setTranslationY(680 - disparity);
                                a5 = true;
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 270 + h * 4.2f);
                                animator.setDuration(mTime);
                                animator.start();
                            } else {
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 270 + h * 4.2f);
                                animator.setDuration(mTime);
                                animator.start();
                                Log.e("123456", translationX + "  15  " + translationY);
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
                                mTrainicon.setTranslationX(250);
                                mTrainicon.setTranslationY(730 - disparity);
                                a6 = true;
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 250 + h * 4.5f);
                                animator.setDuration(mTime);
                                animator.start();
                            } else {
                                float translationX = mTrainicon.getTranslationX();
                                float translationY = mTrainicon.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mTrainicon, "translationX", 250 + h * 4.5f);
                                animator.setDuration(mTime);
                                animator.start();
                                Log.e("123456", translationX + "  16  " + translationY);
                            }
                        }
                    } else if (type == 232) {
                        Toast.makeText(mContext, "232串口", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }
        });
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        //隐藏状态栏和导航栏
        setSystemUIVisible(false);
        initView();
    }

    private void initView() {
        //mTrainicon = findViewById(R.id.trainicon);
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
}
