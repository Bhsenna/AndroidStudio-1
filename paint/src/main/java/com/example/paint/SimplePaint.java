package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SimplePaint extends View {
    ArrayList<Path> lPaths;
    ArrayList<Paint> lPaints;
    Path mPath;
    Paint mPaint;

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        lPaths = new ArrayList<>();
        lPaints = new ArrayList<>();

        mPath = new Path();
        mPaint = new Paint();

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(6f);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public void removeUltimaCamada() {
        if (!lPaths.isEmpty()) {
            lPaths.remove(lPaths.size() - 1);
            lPaints.remove(lPaints.size() - 1);
        }
        invalidate();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0; i < lPaths.size(); i++) {
            canvas.drawPath(lPaths.get(i), lPaints.get(i));
        }
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                invalidate();
                return (true);
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                invalidate();
                return (true);
            case MotionEvent.ACTION_UP:
                lPaths.add(mPath);
                lPaints.add(mPaint);

                int currentColor = mPaint.getColor();
                mPath = new Path();
                mPaint = new Paint();
                mPaint.setColor(currentColor);
                mPaint.setStrokeWidth(6f);
                mPaint.setAntiAlias(true);
                mPaint.setStyle(Paint.Style.STROKE);

                break;
        }

        return super.onTouchEvent(event);
    }

}
