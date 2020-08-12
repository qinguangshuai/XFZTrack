package com.qgsstrive.xfztrack.bjxt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.qgsstrive.xfztrack.MainActivity;
import com.qgsstrive.xfztrack.R;

/**
 * @date 2020/8/1 13:35
 */
public class DrawTop extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private Canvas mCanvas1;
    //private GPSPointF gpsPoint = new GPSPointF(5,80);
    private Bitmap mBitmap1;

    public DrawTop(Context context) {
        this(context,null);
    }

    public DrawTop(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawTop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.train);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //创建一个画笔
        mPaint = new Paint(Paint.DITHER_FLAG);
        //设置位图的宽高
        mBitmap = Bitmap.createBitmap(1280, 800, Bitmap.Config.RGB_565);
        //绘制内容保存到Bitmap
        mCanvas1 = new Canvas(mBitmap);
        //设置非填充
        mPaint.setStyle(Paint.Style.STROKE);
        //笔宽5像素
        mPaint.setStrokeWidth(5);
        //设置为红笔
        mPaint.setColor(Color.RED);
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        //设置图像抖动处理
        mPaint.setDither(true);
        //设置图像的结合方式
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        //设置画笔为圆形样式
        //mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawBitmap(mBitmap1, 0,0, null);
        //drawGps(canvas,getGpsPoint(),mPaint);
    }

    /**
     * 画车
     *
     * @param canvas   画布
     * @param gpsPoint gps坐标
     * @param paint    画笔
     */
    private void drawGps(Canvas canvas, GPSPointF gpsPoint, Paint paint) {
        //paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        canvas.drawBitmap(mBitmap1, MainActivity.gpsPoint.getX(), MainActivity.gpsPoint.getY(), null);
    }

    public static GPSPointF getGpsPoint() {
        MainActivity.gpsPoint.setX(MainActivity.gpsPoint.getX() + 10);
        if (MainActivity.gpsPoint.getX() <= 348) {
            MainActivity.gpsPoint.setY(MainActivity.gpsPoint.getY());
        } else if (MainActivity.gpsPoint.getX() > 348 && MainActivity.gpsPoint.getX() < 448) {
            MainActivity.gpsPoint.setY((float) (MainActivity.gpsPoint.getX() * 1.6 - 476.8));
        } else if (448 < MainActivity.gpsPoint.getX()) {
            MainActivity.gpsPoint.setY(240);
        }

        Log.i("TAGgpsPoint", String.valueOf(MainActivity.gpsPoint.getX()));

        return MainActivity.gpsPoint;
    }

    public static GPSPointF getGpsPointTwo() {
        return MainActivity.gpsPoint;
    }
}
