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
 * @date 2020/8/6 8:37
 */
public class CustomMap extends View {

    //int x, y;
    private Paint mPaint, paint;
    private Bitmap mBitmap;
    private Canvas mCanvas1;
    private Bitmap mBitmap1;
    //private GPSPointF gpsPoint = new GPSPointF(x, y);

    public CustomMap(Context context) {
        this(context, null);
    }

    public CustomMap(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomMap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //mBitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.train);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);

        //创建一个画笔
        mPaint = new Paint(Paint.DITHER_FLAG);
        paint = new Paint(Paint.DITHER_FLAG);
        //设置位图的宽高
        mBitmap = Bitmap.createBitmap(1280, 800, Bitmap.Config.RGB_565);
        //绘制内容保存到Bitmap
        mCanvas1 = new Canvas(mBitmap);
        //设置非填充
        mPaint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        //笔宽5像素
        mPaint.setStrokeWidth(5);
        paint.setStrokeWidth(1);
        paint.setTextSize(18);
        //设置为红笔
        mPaint.setColor(Color.RED);
        paint.setColor(Color.GRAY);
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        paint.setAntiAlias(true);
        //设置图像抖动处理
        mPaint.setDither(true);
        paint.setDither(true);
        //设置图像的结合方式
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);

        int width = canvas.getWidth();
        int height = canvas.getHeight();
        Log.e("444444",width + "  " + height);

        //第5条线（50-800）
        canvas.drawLine(50, 90, 800, 90, mPaint);
        canvas.drawLine(800, 90, 950, 70, mPaint);
        canvas.drawText("5", 500, 80, paint);
        canvas.drawText("38/44", 890, 70, paint);
        //canvas.drawText("709（43）", 550, 80, paint);
        //第6条线（100-750）
        canvas.drawLine(98, 150, 752, 150, mPaint);
        //右侧
        canvas.drawLine(750, 150, 780, 110, mPaint);
        canvas.drawLine(778, 110, 800, 110, mPaint);
        //左侧
        canvas.drawLine(100, 150, 70, 60, mPaint);
        canvas.drawText("6", 500, 140, paint);
        canvas.drawText("58", 780, 130, paint);
        //canvas.drawText("699（42）", 550, 140, paint);
        //第7条线（160-950）
        canvas.drawLine(158, 220, 852, 220, mPaint);
        //右侧
        canvas.drawLine(850, 220, 950, 150, mPaint);
        //左侧
        canvas.drawLine(160, 220, 155, 210, mPaint);
        canvas.drawText("7", 500, 210, paint);
        canvas.drawText("47", 160, 210, paint);
        //canvas.drawText("525（33）", 550, 210, paint);
        //第8条线
        canvas.drawLine(188, 280, 802, 280, mPaint);
        canvas.drawLine(800, 280, 820, 240, mPaint);
        canvas.drawLine(818, 240, 830, 240, mPaint);
        //左侧
        canvas.drawLine(190, 280, 185, 270, mPaint);
        canvas.drawText("8", 500, 270, paint);
        canvas.drawText("49", 190, 270, paint);
        canvas.drawText("80", 780, 260, paint);
        //canvas.drawText("491（31）", 550, 270, paint);
        //第9条线
        canvas.drawLine(218, 340, 782, 340, mPaint);
        //右侧
        canvas.drawLine(780, 340, 850, 230, mPaint);
        //左侧
        canvas.drawLine(220, 340, 215, 330, mPaint);
        canvas.drawText("9", 500, 330, paint);
        canvas.drawText("51", 220, 330, paint);
        canvas.drawText("78", 850, 250, paint);
        //canvas.drawText("490（31）", 550, 330, paint);
        //第10条线
        canvas.drawLine(248, 400, 782, 400, mPaint);
        //右侧
        canvas.drawLine(780, 400, 870, 260, mPaint);
        canvas.drawLine(870, 260, 950, 50, mPaint);
        //左侧
        canvas.drawLine(250, 400, 245, 390, mPaint);
        canvas.drawText("10", 500, 390, paint);
        canvas.drawText("53", 250, 390, paint);
        //canvas.drawText("492（25）", 550, 390, paint);
        //第11条线
        canvas.drawLine(278, 460, 782, 460, mPaint);
        //右侧
        canvas.drawLine(780, 460, 790, 450, mPaint);
        //左侧
        canvas.drawLine(280, 460, 275, 450, mPaint);
        canvas.drawText("11", 500, 450, paint);
        canvas.drawText("55", 280, 450, paint);
        canvas.drawText("90", 760, 450, paint);
        //canvas.drawText("354（18）", 550, 450, paint);
        //第12条线（300-950）
        canvas.drawLine(298, 520, 702, 520, mPaint);
        //右侧
        canvas.drawLine(700, 520, 812, 450, mPaint);
        canvas.drawLine(810, 450, 870, 270, mPaint);
        //左侧
        canvas.drawLine(110, 170, 300, 520, mPaint);
        canvas.drawLine(112, 170, 100, 170, mPaint);
        canvas.drawText("12", 500, 510, paint);
        canvas.drawText("41", 90, 190, paint);
        canvas.drawText("60", 880, 290, paint);
        //canvas.drawText("353（18）", 550, 510, paint);
        //第13条线(280-950)
        canvas.drawLine(278, 580, 692, 580, mPaint);
        //右侧
        canvas.drawLine(690, 580, 705, 640, mPaint);
        canvas.drawLine(703, 640, 715, 640, mPaint);
        //左侧
        canvas.drawLine(280, 580, 180, 420, mPaint);
        canvas.drawText("13", 500, 570, paint);
        canvas.drawText("59", 190, 420, paint);
        canvas.drawText("84", 700, 630, paint);
        //canvas.drawText("417（21）", 550, 570, paint);
        //第14条线(260-950)
        canvas.drawLine(268, 630, 692, 630, mPaint);
        //右侧
        canvas.drawLine(690, 630, 695, 640, mPaint);
        //左侧
        canvas.drawLine(270, 630, 220, 500, mPaint);
        canvas.drawText("14", 500, 620, paint);
        canvas.drawText("63", 200, 500, paint);
        canvas.drawText("88", 660, 650, paint);
        //canvas.drawText("417（21）", 550, 620, paint);
        //第15条线（230-950）
        canvas.drawLine(268, 680, 692, 680, mPaint);
        //右侧
        canvas.drawLine(690, 680, 702, 650, mPaint);
        canvas.drawLine(700, 650, 710, 650, mPaint);
        //左侧
        canvas.drawLine(270, 680, 190, 480, mPaint);
        canvas.drawText("15", 500, 670, paint);
        canvas.drawText("61", 180, 480, paint);
        //canvas.drawText("470（24）", 550, 670, paint);
        //第16条线（200-800）
        canvas.drawLine(248, 730, 702, 730, mPaint);
        canvas.drawLine(100, 200, 250, 730, mPaint);
        canvas.drawLine(700, 730, 810, 460, mPaint);
        canvas.drawText("16", 500, 720, paint);
        canvas.drawText("45", 80, 240, paint);
        canvas.drawText("86", 760, 600, paint);
        //canvas.drawText("496（26）", 550, 720, paint);

        drawGps(canvas, getGpsPoint(), mPaint);
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

        //canvas.drawBitmap(mBitmap1, MainActivity.gpsPoint.getX() - 25, MainActivity.gpsPoint.getY() - 25, null);
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
