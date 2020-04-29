package com.ckkj.router.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.ckkj.router.R;

/**
 * 倒三角型的自定义View
 *
 * @author lotty
 */
public class TriangleView extends View {

    private Path path = new Path();

    private Paint paint = new Paint();

    private int paintColor;

    public TriangleView(Context context) {
        super(context);
    }

    public TriangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
    }

    private void initAttr(Context context, @Nullable AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.down_triangle);
        paintColor = array.getColor(R.styleable.down_triangle_paintColor, Color.BLACK);
        array.recycle();
    }

    public TriangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);

    }

    public TriangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttr(context, attrs);
    }

    public void setPaintColor(int color) {
        paintColor = color;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(paintColor);
        int width = getWidth();
        int height = getHeight();
        path.moveTo(0, 0);
        path.lineTo(width - 1, 0);
        path.lineTo(width / 2 - 1, height - 1);
        path.close();
        canvas.drawPath(path, paint);
    }
}
