package com.qgsstrive.xfztrack.bjxt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @date 2020/8/10 16:33
 */
public class DefinitionMap extends View {

    private Paint mPaint, paint;
    private Bitmap mBitmap;
    private Canvas mCanvas1;
    private Bitmap mBitmap1;
    int i = 90;

    public DefinitionMap(Context context) {
        this(context,null);
    }

    public DefinitionMap(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DefinitionMap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#FAEBD6"));

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
        mPaint.setStrokeWidth(3);
        paint.setStrokeWidth(1);
        paint.setTextSize(18);
        //设置为红笔
        mPaint.setColor(Color.BLACK);
        paint.setColor(Color.BLUE);
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        paint.setAntiAlias(true);
        //设置图像抖动处理
        mPaint.setDither(true);
        paint.setDither(true);
        //设置图像的结合方式
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);

        //第5条线
        canvas.drawLine(50, 90, 970, 90, mPaint);
        canvas.drawText("5", 512, 80, paint);
        //第6条线
        canvas.drawLine(50, 150, 970, 150, mPaint);
        canvas.drawText("6", 512, 140, paint);
        //第7条线
        canvas.drawLine(50, 210, 970, 210, mPaint);
        canvas.drawText("7", 512, 200, paint);
        //第8条线
        canvas.drawLine(50, 270, 970, 270, mPaint);
        canvas.drawText("8", 512, 260, paint);
        //第9条线
        canvas.drawLine(50, 330, 970, 330, mPaint);
        canvas.drawText("9", 512, 320, paint);
        //第10条线
        canvas.drawLine(150, 390, 970, 390, mPaint);
        canvas.drawLine(110, 330, 150, 390, mPaint);
        canvas.drawText("10", 512, 380, paint);
        //第11条线
        canvas.drawLine(240, 450, 970, 450, mPaint);
        canvas.drawLine(190, 390, 240, 450, mPaint);
        canvas.drawText("11", 512, 440, paint);
        //第12条线
        canvas.drawLine(340, 510, 890, 510, mPaint);
        canvas.drawLine(290, 450, 340, 510, mPaint);
        canvas.drawLine(930, 450, 890, 510, mPaint);
        canvas.drawText("12", 512, 500, paint);
        //第13条线
        canvas.drawLine(280, 570, 920, 570, mPaint);
        canvas.drawLine(50, 390, 280, 570, mPaint);
        canvas.drawLine(920, 570, 970, 510, mPaint);
        canvas.drawText("13", 512, 560, paint);
        //第14条线
        canvas.drawLine(280, 630, 800, 630, mPaint);
        canvas.drawLine(280, 630, 85, 420, mPaint);
        canvas.drawLine(800, 630, 830, 570, mPaint);
        canvas.drawText("14", 512, 620, paint);
        //第15条线
        canvas.drawLine(280, 690, 800, 690, mPaint);
        canvas.drawLine(50, 450, 280, 690, mPaint);
        canvas.drawLine(800, 690, 870, 570, mPaint);
        canvas.drawText("15", 512, 680, paint);
        //第16条线
        canvas.drawLine(280, 750, 850, 750, mPaint);
        canvas.drawLine(280, 750, 75, 480, mPaint);
        canvas.drawLine(850, 750, 970, 570, mPaint);
        canvas.drawText("16", 512, 740, paint);
    }
}
