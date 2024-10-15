package com.example.paint;

import android.graphics.Paint;
import android.graphics.Path;

public class Camada {
    Path path;
    Paint paint;
    public Camada(Paint paint) {
        this.path = new Path();
        this.paint = new Paint();
        this.paint.set(paint);
    }
    public Camada() {
        this.path = new Path();
        this.paint = new Paint();
    }
}
