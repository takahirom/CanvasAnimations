package com.kogitune.canvasanimations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * TODO: document your custom view class.
 */
public class CropView extends View {
    private int width;
    private int height;
    private Bitmap bitmap;
    private Rect bitmapRect;
    private Rect drawRect;
    private int percent = 0;
    private int bitmapHeight;


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        invalidateTextPaintAndMeasurements();
        invalidate();
    }

    public CropView(Context context) {
        super(context);
        init(null, 0);
    }

    public CropView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CropView(Context context, AttributeSet attrs, int defStyle) {
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
        drawRect = new Rect(0, 0, width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        percent += 1;
        if (percent > 100) {
            percent = 0;
        }

        drawRect.bottom = height * percent / 100;
        bitmapRect.bottom = bitmapHeight * percent / 100;

        canvas.drawBitmap(bitmap, bitmapRect, drawRect, new Paint());

        ViewCompat.postInvalidateOnAnimation(this);

    }

}
