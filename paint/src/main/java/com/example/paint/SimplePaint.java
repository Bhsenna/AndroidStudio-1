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
    public enum Shape {
        FINGER, LINE, SQUARE, CIRCLE
    }
    Shape shape = Shape.FINGER;
    public static class CoordenadasTraco {
        public float InicialX;
        public float InicialY;
    }
    ArrayList<Layer> layers;
    CoordenadasTraco coordenadasTraco;


    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        layers = new ArrayList<Layer>();
        layers.add(new Layer());
        setupLayer(getCurrentLayer());
    }

    public void setupLayer(Layer layer) {
        layer.paint.setAntiAlias(true);
        layer.paint.setStrokeWidth(6f);
        layer.paint.setColor(0xff000000);
        layer.paint.setStyle(Paint.Style.STROKE);
        layer.paint.setStrokeJoin(Paint.Join.ROUND);
    }

    public Layer getCurrentLayer() {
        return layers.get(layers.size() - 1);
    }

    public void clear() {
        getCurrentLayer().path.reset();
        invalidate(); // RE-renderiza a View
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for(Layer layer : layers) {
            canvas.drawPath(layer.path, layer.paint);
        }
    }

    public void changeColor(int color) {
        layers.add(new Layer(getCurrentLayer().paint));
        getCurrentLayer().paint.setColor(color);
    }

    public void changeStrokeWidth(int width) {
        layers.add(new Layer(getCurrentLayer().paint));
        getCurrentLayer().paint.setStrokeWidth(width);
    }

    private void addLayer() {
        layers.add(new Layer(getCurrentLayer().paint));
        invalidate();
    }

    public void changeShape(Shape shape) {
        this.shape = shape;
    }

    public void removeLastLayer() {
        if (layers.size() > 1) {
            layers.remove(layers.size() - 1);
            invalidate();
        } else {
            clear();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                addLayer();
                getCurrentLayer().path.moveTo(x, y);
                coordenadasTraco = new CoordenadasTraco();
                coordenadasTraco.InicialX = x;
                coordenadasTraco.InicialY = y;
                return true;
            case MotionEvent.ACTION_MOVE:
                switch (shape) {
                    case CIRCLE:
                        getCurrentLayer().path.reset();
                        getCurrentLayer().path.addOval(coordenadasTraco.InicialX, coordenadasTraco.InicialY, x, y, Path.Direction.CCW);

                        break;
                    case SQUARE:
                        getCurrentLayer().path.reset();
                        getCurrentLayer().path.addRect(coordenadasTraco.InicialX, coordenadasTraco.InicialY, x, y, Path.Direction.CCW);
                        break;
                    case LINE:
                        getCurrentLayer().path.reset();
                        getCurrentLayer().path.moveTo(coordenadasTraco.InicialX, coordenadasTraco.InicialY);
                        getCurrentLayer().path.lineTo(x, y);
                        break;
                    default:
                        getCurrentLayer().path.lineTo(x, y);
                        break;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        invalidate();
        return super.onTouchEvent(event);
    }
}
