package com.ckkj.router.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 实验测量方法
 *
 * @author lotty
 */
public class MeasureView extends View {

    Paint paint = new Paint();

    Paint textPaint = new Paint();


    public MeasureView(Context context) {
        super(context);
    }

    public MeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor("#FF3030"));

        textPaint.setColor(Color.parseColor("#FF000000"));
        textPaint.setTextSize(dp2pix(14));

    }


    /**
     * MeasureSpec 32位，最高2位为测量模式，低30位为数值位
     * <li>MeasureSpec.getMode(int)</li>
     * <li>MeasureSpec.makeMeasureSpec(int, int)</li>
     * <li>setMeasuredDimension(int, int)</li>
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(dp2pix(40), MeasureSpec.EXACTLY);
        setMeasuredDimension(widthMeasureSpec, makeMeasureSpec);
    }

    private int dp2pix(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }
}
