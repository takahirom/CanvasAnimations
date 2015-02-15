package com.kogitune.canvasanimations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;


public class SpreadView extends View {
    private int width;
    private int height;
    private Bitmap bitmap;
    private Rect bitmapRect;
    private Rect drawRect;
    private int percent = 0;
    private Rect spreadRect;


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        invalidateTextPaintAndMeasurements();
        invalidate();
    }

    public SpreadView(Context context) {
        super(context);
        init(null, 0);
    }

    public SpreadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public SpreadView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_android_black_48dp);
        bitmapRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        final int margin = 40;
        drawRect = new Rect(margin, margin, width - margin, height - margin);
        spreadRect = new Rect(0, 0, width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        percent += 1;
        if (percent > 100) {
            percent = 0;
        }
        final float fPercent = percent / 100f;

        final float top = spreadRect.top * fPercent + drawRect.top * (1 - fPercent);
        final float left = spreadRect.left * fPercent + drawRect.left * (1 - fPercent);
        final float right = spreadRect.right * fPercent + drawRect.right * (1 - fPercent);
        final float bottom = spreadRect.bottom * fPercent + drawRect.bottom * (1 - fPercent);

        canvas.drawBitmap(bitmap, bitmapRect, new RectF(left, top, right, bottom), new Paint());

        ViewCompat.postInvalidateOnAnimation(this);
    }

}
