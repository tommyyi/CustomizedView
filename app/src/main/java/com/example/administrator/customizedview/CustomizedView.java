package com.example.administrator.customizedview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/7/1.
 *
 */
public class CustomizedView extends View
{

    private Handler mHandler;
    private Runnable mRunnable;
    private float mDegree=0;
    private Paint mPaint;

    public CustomizedView(Context context)
    {
        super(context);
    }

    public CustomizedView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        TypedArray typedArray = context.getResources().obtainAttributes(attrs, R.styleable.CustomizedView);
        int color = typedArray.getColor(R.styleable.CustomizedView_ovalColor, 0xffff0000);
        setBackgroundColor(color);
        typedArray.recycle();
        mHandler = new Handler();
        mRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                invalidate();
            }
        };
        mPaint = new Paint();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);

        mDegree++;
        canvas.save();
//        canvas.rotate(mDegree);
        canvas.rotate(mDegree,getWidth()/2,getHeight()/2);
        mPaint.setColor(Color.BLUE);
        canvas.drawOval(0,getHeight()/2-100,getWidth(),getHeight()/2+100, mPaint);
        canvas.rotate(90,getWidth()/2,getHeight()/2);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0,getHeight()/2-50,getWidth(),getHeight()/2+50,mPaint);
        canvas.restore();

        mHandler.post(mRunnable);
//        mHandler.postDelayed(mRunnable,50);
    }
}
