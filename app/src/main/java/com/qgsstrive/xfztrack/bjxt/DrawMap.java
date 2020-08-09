package com.qgsstrive.xfztrack.bjxt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @date 2020/7/30 16:22
 */
public class DrawMap extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private Canvas mCanvas1;
    private Paint paint;
    int circleRadius;

    public DrawMap(Context context) {
        this(context, null);
    }

    public DrawMap(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawMap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("DrawAllocation")
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
        //设置画笔为圆形样式
        //mPaint.setStrokeCap(Paint.Cap.ROUND);
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        Log.e("111", width + "  " + height);

        //绘制一组线
        canvas.drawLines(new float[]{
                391, 70, 340, 110, 830, 155, 850, 175, 849, 175, 860, 175, 820, 155, 831, 155,
                392, 145, 380, 175, 382, 175, 370, 175, 750, 155, 730, 175, 749, 155, 760, 155,
                731, 175, 720, 175, 455, 78, 462, 92, 319, 145, 460, 85, 307, 175, 295, 175, 315,
                155, 305, 175, 313, 155, 325, 155, 90, 145, 320, 145, 200, 155, 210, 175, 202, 155,
                190, 155, 208, 175, 220, 175
        }, mPaint);
        //第1条线
        canvas.drawLine(391, 145, 930, 145, mPaint);
        canvas.drawText("1", 430, 130, paint);
        canvas.drawText("687（41）", 480, 130, paint);
        //第2条线
        canvas.drawLine(50, 185, 330, 185, mPaint);
        canvas.drawLine(350, 185, 930, 185, mPaint);
        canvas.drawText("11", 430, 175, paint);
        canvas.drawText("714（43）", 480, 175, paint);
        //第3条线
        canvas.drawLine(328, 185, 370, 240, mPaint);
        canvas.drawLine(368, 240, 700, 240, mPaint);
        canvas.drawText("3", 430, 230, paint);
        canvas.drawText("725（44）", 480, 230, paint);
        //交叉线
        canvas.drawLines(new float[]{
                245, 195, 270, 260, 268, 260, 280, 260, 247, 195, 235, 195,
                270, 195, 245, 260, 247, 260, 235, 260, 268, 195, 280, 195,
        }, mPaint);
        //第4条线
        canvas.drawLine(70, 270, 680, 270, mPaint);
        canvas.drawLines(new float[]{
                310, 280, 340, 310, 312, 280, 300, 280,
                380, 280, 400, 300, 398, 300, 410, 300, 382, 280, 370, 280,
                70, 263, 70, 278
        }, mPaint);
        canvas.drawLine(680, 270, 830, 230, mPaint);
        canvas.drawLine(830, 230, 940, 270, mPaint);
        canvas.drawLine(942, 263, 935, 278, mPaint);
        //平行线
        canvas.drawLine(880, 200, 950, 215, mPaint);
        canvas.drawText("468（32）", 930, 200, paint);
        canvas.drawLine(860, 220, 950, 245, mPaint);

        canvas.drawText("IV", 430, 260, paint);
        canvas.drawText("767（47）", 480, 260, paint);
        //第5条线
        canvas.drawLine(90, 310, 720, 310, mPaint);
        canvas.drawLine(90, 303, 90, 318, mPaint);
        canvas.drawLine(720, 310, 800, 290, mPaint);
        canvas.drawLine(800, 290, 845, 305, mPaint);
        canvas.drawLine(843, 305, 852, 295, mPaint);

        //空心圆
        circleRadius = 7;
        canvas.drawCircle(800, 300, circleRadius, mPaint);

        canvas.drawText("5", 430, 300, paint);
        canvas.drawText("709（43）", 480, 300, paint);
        //第6条线
        canvas.drawLine(350, 350, 660, 350, mPaint);
        canvas.drawLine(658, 350, 690, 310, mPaint);
        canvas.drawLine(310, 280, 330, 310, mPaint);
        canvas.drawLine(330, 310, 350, 350, mPaint);

        canvas.drawText("6", 430, 340, paint);
        canvas.drawText("699（42）", 480, 340, paint);
        //第8条线
        canvas.drawLine(392, 430, 712, 430, mPaint);
        canvas.drawLine(394, 430, 390, 420, mPaint);
        canvas.drawLine(712, 430, 860, 310, mPaint);
        canvas.drawLine(860, 310, 935, 350, mPaint);
        canvas.drawText("8", 470, 420, paint);
        canvas.drawText("545（28）", 520, 420, paint);
        //第9条线
        canvas.drawLine(402, 470, 660, 470, mPaint);
        canvas.drawLine(658, 470, 670, 440, mPaint);
        canvas.drawLine(668, 440, 690, 440, mPaint);
        canvas.drawLine(404, 470, 400, 460, mPaint);
        canvas.drawText("9", 470, 460, paint);
        canvas.drawText("503（20）", 520, 460, paint);
        //第10条线
        canvas.drawLine(420, 520, 650, 520, mPaint);
        canvas.drawLine(650, 522, 680, 440, mPaint);
        canvas.drawLine(422, 520, 418, 510, mPaint);
        canvas.drawText("10", 470, 510, paint);
        canvas.drawText("551（208）", 520, 510, paint);
        //第11条线
        canvas.drawLine(430, 560, 662, 560, mPaint);
        canvas.drawLines(new float[]{
                660, 560, 705, 460, 705, 450, 810, 200, 808, 200, 820, 200, 432, 560, 428, 550,
        }, mPaint);

        circleRadius = 7;
        canvas.drawCircle(705, 455, circleRadius, mPaint);

        canvas.drawText("11", 470, 550, paint);
        canvas.drawText("492（25）", 520, 550, paint);
        //第12线
        canvas.drawLine(442, 600, 640, 600, mPaint);
        canvas.drawLines(new float[]{
                638, 600, 650, 590, 444, 600, 440, 590,
        }, mPaint);
        canvas.drawText("12", 470, 590, paint);
        canvas.drawText("354（18）", 520, 590, paint);
        //第13条线
        canvas.drawLine(442, 640, 610, 640, mPaint);
        circleRadius = 7;
        canvas.drawCircle(366, 380, circleRadius, mPaint);
        //右侧
        canvas.drawLine(609, 640, 680, 600, mPaint);
        canvas.drawLine(680, 601, 710, 480, mPaint);
        canvas.drawLine(710, 481, 720, 460, mPaint);
        //左侧
        canvas.drawLine(444, 640, 368, 385, mPaint);
        canvas.drawLine(363, 372, 360, 360, mPaint);
        canvas.drawLine(362, 360, 350, 360, mPaint);
        canvas.drawText("13", 470, 630, paint);
        canvas.drawText("353（18）", 520, 630, paint);
        //第14条线
        canvas.drawLine(450, 680, 620, 680, mPaint);
        //右侧
        canvas.drawLine(618, 680, 635, 720, mPaint);
        canvas.drawLine(633, 720, 647, 720, mPaint);
        canvas.drawLine(645, 720, 650, 710, mPaint);
        //左侧
        canvas.drawLines(new float[]{
                452, 680, 380, 570, 378, 570, 385, 560,
        }, mPaint);
        canvas.drawText("14", 470, 670, paint);
        canvas.drawText("417（21）", 520, 670, paint);
        //第15条线
        canvas.drawLine(460, 720, 620, 720, mPaint);
        //右侧
        canvas.drawLine(618, 720, 625, 730, mPaint);
        //左侧
        canvas.drawLine(462, 720, 420, 650, mPaint);
        canvas.drawText("15", 470, 710, paint);
        canvas.drawText("417（21）", 520, 710, paint);
        //第16条线
        canvas.drawLine(470, 760, 610, 760, mPaint);
        //右侧
        canvas.drawLine(608, 760, 630, 730, mPaint);
        canvas.drawLine(628, 730, 640, 730, mPaint);
        //左侧
        canvas.drawLine(472, 760, 380, 610, mPaint);
        canvas.drawText("16", 470, 750, paint);
        canvas.drawText("470（24）", 520, 750, paint);
    }
}
