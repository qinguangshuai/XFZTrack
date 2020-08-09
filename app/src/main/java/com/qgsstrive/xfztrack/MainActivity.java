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
import com.qgsstrive.xfztrack.bjxt.DrawMap;
import com.qgsstrive.xfztrack.bjxt.DrawTop;
import com.qgsstrive.xfztrack.bjxt.GPSPointF;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

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
    private String mSubstring;

    static int x, y;
    public static GPSPointF gpsPoint = new GPSPointF(x, y);
    private CustomMap mCustommap;
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
                        if (valueOf.equals("05")) {
                            if (aa == false) {
                                mDrawTop.setTranslationX(100);
                                mDrawTop.setTranslationY(90);
                                aa = true;
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 100+h * 7f);
                                animator.setDuration(1500);
                                animator.start();
                                Log.e("123456", translationX + "  05  " + translationY);
                            } else {
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 100+h * 7f);
                                animator.setDuration(1500);
                                animator.start();
                                Log.e("123456", translationX + "  05  " + translationY);
                            }
                        }
                        if (valueOf.equals("06")) {
                            if (bb == false) {
                                mDrawTop.setTranslationX(100);
                                mDrawTop.setTranslationY(150);
                                bb = true;
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 100 + h * 6.5f);
                                animator.setDuration(1500);
                                animator.start();
                            } else {
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 100 + h * 6.5f);
                                animator.setDuration(1500);
                                animator.start();
                                Log.e("123456", translationX + "  06  " + translationY);
                            }
                        }
                        if (valueOf.equals("07")) {
                            if (cc == false) {
                                mDrawTop.setTranslationX(160);
                                mDrawTop.setTranslationY(220);
                                cc = true;
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 160 + h * 6.4f);
                                animator.setDuration(1500);
                                animator.start();
                            } else {
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 160 + h * 6.4f);
                                animator.setDuration(1500);
                                animator.start();
                                Log.e("123456", translationX + "  07  " + translationY);
                            }
                        }
                        if (valueOf.equals("08")) {
                            if (dd == false) {
                                mDrawTop.setTranslationX(200);
                                mDrawTop.setTranslationY(280);
                                dd = true;
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 200 + h * 6f);
                                animator.setDuration(1500);
                                animator.start();
                            }else {
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 200 + h * 6f);
                                animator.setDuration(1500);
                                animator.start();
                                Log.e("123456", translationX + "  08  " + translationY);
                            }
                        }
                        if (valueOf.equals("09")) {
                            if (ee == false) {
                                mDrawTop.setTranslationX(220);
                                mDrawTop.setTranslationY(340);
                                ee = true;
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 220 + h * 5.6f);
                                animator.setDuration(1500);
                                animator.start();
                            }else {
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 220 + h * 5.6f);
                                animator.setDuration(1500);
                                animator.start();
                                Log.e("123456", translationX + "  09  " + translationY);

                            }
                        }
                        if (valueOf.equals("10")) {
                            if (ff == false) {
                                mDrawTop.setTranslationX(250);
                                mDrawTop.setTranslationY(400);
                                ff = true;
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 250 + h * 5.3f);
                                animator.setDuration(1500);
                                animator.start();
                            }else {
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 250 + h * 5.3f);
                                animator.setDuration(1500);
                                animator.start();
                                Log.e("123456", translationX + "  10  " + translationY);

                            }
                        }
                        if (valueOf.equals("11")) {
                            if (a1 == false) {
                                mDrawTop.setTranslationX(280);
                                mDrawTop.setTranslationY(460);
                                a1 = true;
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 280 + h * 5f);
                                animator.setDuration(1500);
                                animator.start();
                            }else {
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 280 + h * 5f);
                                animator.setDuration(1500);
                                animator.start();
                                Log.e("123456", translationX + "  11  " + translationY);

                            }
                        }
                        if (valueOf.equals("12")) {
                            if (a2 == false) {
                                mDrawTop.setTranslationX(300);
                                mDrawTop.setTranslationY(520);
                                a2 = true;
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 300 + h * 4f);
                                animator.setDuration(1500);
                                animator.start();
                            }else {
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 300 + h * 4f);
                                animator.setDuration(1500);
                                animator.start();
                                Log.e("123456", translationX + "  12  " + translationY);
                            }
                        }
                        if (valueOf.equals("13")) {
                            if (a3 == false) {
                                mDrawTop.setTranslationX(280);
                                mDrawTop.setTranslationY(580);
                                a3 = true;
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 280 + h * 4.1f);
                                animator.setDuration(1500);
                                animator.start();
                            }else {
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 280 + h * 4.1f);
                                animator.setDuration(1500);
                                animator.start();
                                Log.e("123456", translationX + "  13  " + translationY);
                            }
                        }
                        if (valueOf.equals("14")) {
                            if (a4 == false) {
                                mDrawTop.setTranslationX(270);
                                mDrawTop.setTranslationY(630);
                                a4 = true;
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 270 + h * 4.2f);
                                animator.setDuration(1500);
                                animator.start();
                            }else {
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 270 + h * 4.2f);
                                animator.setDuration(1500);
                                animator.start();
                                Log.e("123456", translationX + "  14  " + translationY);
                            }
                        }
                        if (valueOf.equals("15")) {
                            if (a5 == false) {
                                mDrawTop.setTranslationX(270);
                                mDrawTop.setTranslationY(680);
                                a5 = true;
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 270 + h * 4.2f);
                                animator.setDuration(1500);
                                animator.start();
                            }else {
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 270 + h * 4.2f);
                                animator.setDuration(1500);
                                animator.start();
                                Log.e("123456", translationX + "  15  " + translationY);
                            }
                        }
                        if (valueOf.equals("16")) {
                            if (a6 == false) {
                                mDrawTop.setTranslationX(250);
                                mDrawTop.setTranslationY(730);
                                a6 = true;
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 250 + h * 4.5f);
                                animator.setDuration(1500);
                                animator.start();
                            }else {
                                float translationX = mDrawTop.getTranslationX();
                                float translationY = mDrawTop.getTranslationY();
                                ObjectAnimator animator
                                        = ObjectAnimator.ofFloat(mDrawTop, "translationX", 250 + h * 4.5f);
                                animator.setDuration(1500);
                                animator.start();
                                Log.e("123456", translationX + "  16  " + translationY);
                            }
                        }
                        /*float translationX = mDrawTop.getTranslationX();
                        float translationY = mDrawTop.getTranslationY();
                        ObjectAnimator animator
                                = ObjectAnimator.ofFloat(mDrawTop, "translationX", translationX + 50*6f);
                        animator.setDuration(1500);
                        animator.start();
                        Log.e("123456",translationX+"  11  "+translationY);*/
                        /*ObjectAnimator animator1
                                = ObjectAnimator.ofFloat(mDrawTop, "translationY", translationY + 100f);
                        animator1.setDuration(2000);
                        animator1.start();*/
                    } else if (type == 232) {
                        Toast.makeText(mContext, "232串口", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }
        });
    }

    int a = 100;
    int b = 90;

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
        /*mDrawTop.setTranslationX(100);
        mDrawTop.setTranslationY(90);*/
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
