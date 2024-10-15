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
    ArrayList<Camada> layers;

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        layers = new ArrayList<Camada>();
        layers.add(new Camada(initialSetupPaint()));
    }

    public Paint initialSetupPaint() {
        Paint lPaint = new Paint();
        lPaint.setStrokeWidth(5f);
        lPaint.setColor(Color.RED);
        lPaint.setStyle(Paint.Style.STROKE);
        return lPaint;
    }

    public void changeColor(int color) {
        layers.add(new Camada(getCurrentLayer().paint));
        getCurrentLayer().paint.setColor(color);
    }

    public void changeStrokeWidth(int width) {
        layers.add(new Camada(getCurrentLayer().paint));
        getCurrentLayer().paint.setStrokeWidth(width);
    }

    public void removeLastLayer() {
        if (!layers.isEmpty()) {
            Paint lastPaint = getCurrentLayer().paint;

            layers.remove(getCurrentLayer());

            layers.add(new Camada(lastPaint));
            invalidate();
        }
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for(Camada camada : layers) {
            canvas.drawPath(camada.path, camada.paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getCurrentLayer().path.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                getCurrentLayer().path.lineTo(x, y);
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    public Camada getCurrentLayer() {
        return layers.get(layers.size() - 1);
    }
}
