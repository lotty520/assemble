package com.ckkj.router.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @ProjectName: router
 * @Package: com.ckkj.router.ui
 * @ClassName: ClipView
 * @Description: 利用裁剪绘制区域，减少View的叠加层级
 * @Author: 吴晗
 */
public class ClipView extends View {

    private Paint paint = new Paint();


    private boolean isClip = false;

    public ClipView(Context context) {
        super(context);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 是否裁剪
     */
    public void setClip(boolean clip) {
        if (isClip != clip) {
            isClip = clip;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Note:不要在onDraw中创建对象
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor("#FF3030"));
        paint.setTextSize(dp2pix(14));

        int width = getWidth();
        int height = getHeight();
        if (isClip) {
            canvas.clipRect(0, 0, width / 2, height);
            canvas.drawRGB(248, 248, 255);
            canvas.drawText("A", 5, height / 2 + 5, paint);
        } else {
            canvas.drawRGB(248, 248, 255);
            canvas.drawText("A", 5, height / 2 + 5, paint);
        }

    }

    private int dp2pix(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }

}
