package com.kogitune.canvasanimations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;


public class ShinyView extends View {
    private int width;
    private int height;
    private Bitmap bitmap;
    private Rect bitmapRect;
    private Rect drawRect;
    private int percent = 0;
    private Bitmap maskBitmap;


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        invalidateTextPaintAndMeasurements();
        invalidate();
    }

    public ShinyView(Context context) {
        super(context);
        init(null, 0);
    }

    public ShinyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ShinyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        bitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.ic_android_black_48dp)).getBitmap();
        maskBitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.ic_android_white_48dp)).getBitmap();
        bitmapRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

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
        canvas.drawBitmap(bitmap, bitmapRect, drawRect, new Paint());

        float fPercent = percent/100f;

        Paint whiteLinePaint = new Paint();
        whiteLinePaint.setColor(Color.WHITE);
        whiteLinePaint.setStrokeWidth(10);


        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas tempCanvas = new Canvas(result);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        tempCanvas.drawLine(fPercent * width, 0, 0, fPercent * height, whiteLinePaint);
        tempCanvas.drawBitmap(maskBitmap, bitmapRect, drawRect, paint);
        paint.setXfermode(null);

        canvas.drawBitmap(result, 0, 0, new Paint());

        invalidate();

    }

}
