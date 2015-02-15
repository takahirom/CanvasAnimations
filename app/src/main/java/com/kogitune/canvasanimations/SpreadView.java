package com.kogitune.canvasanimations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;


public class SpreadView extends View {
    private int width;
    private int height;
    private Bitmap bitmap;
    private Rect bitmapRect;
    private Rect drawRect;
    private int percent = 0;
    private int bitmapHeight;
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
        bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_android_black_48dp)).getBitmap();
        bitmapRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        bitmapHeight = bitmap.getHeight();

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

        final int top = (spreadRect.top * percent / 100) + drawRect.top * (100 - percent) / 100;
        final int left = (spreadRect.left * percent / 100) + drawRect.left * (100 - percent) / 100;
        final int right = (spreadRect.right * percent / 100) + drawRect.right * (100 - percent) / 100;
        final int bottom = (spreadRect.bottom * percent / 100) + drawRect.bottom * (100 - percent) / 100;

        canvas.drawBitmap(bitmap, bitmapRect, new Rect(left, top, right, bottom), new Paint());

        invalidate();

    }

}
