package com.kogitune.canvasanimations;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


public class SpinnerView extends View {
    private int width;
    private int height;
    private float sweepAngle;
    private float startAngle;
    private RectF rect;
    private Paint paint;


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        sweepAngle = 5;
        startAngle = 5;
        invalidateTextPaintAndMeasurements();
        invalidate();
    }

    public SpinnerView(Context context) {
        super(context);
        init(null, 0);
    }

    public SpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public SpinnerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        paint = new Paint();
        paint.setColor(Color.MAGENTA);
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        rect = new RectF(10, 10, width - 10, height - 10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(rect, startAngle % 360, sweepAngle, false, paint);

        if (sweepAngle < 180) {
            sweepAngle *= 1.05;
        }
        if (startAngle < 2000) {
            startAngle *= 1.05;
        } else {
            startAngle += 2000 * 0.05;
        }

        invalidate();

    }

}
