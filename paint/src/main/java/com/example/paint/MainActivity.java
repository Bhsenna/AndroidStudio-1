package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {
    ImageButton btn_cores;
    ImageButton btn_volta;
    ImageButton btn_finge;
    ImageButton btn_linha;
    ImageButton btn_quadr;
    ImageButton btn_circu;
    SimplePaint simplePaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_cores = findViewById(R.id.btn_cores);
        btn_volta = findViewById(R.id.btn_volta);
        btn_finge = findViewById(R.id.btn_finge);
        btn_linha = findViewById(R.id.btn_linha);
        btn_quadr = findViewById(R.id.btn_quadr);
        btn_circu = findViewById(R.id.btn_circu);
        simplePaint = findViewById(R.id.simplepaint);

        btn_cores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreColorPicker();
            }
        });
        btn_volta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.removeLastLayer();
            }
        });
        btn_finge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.changeShape(SimplePaint.Shape.FINGER);
            }
        });
        btn_linha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.changeShape(SimplePaint.Shape.LINE);
            }
        });
        btn_quadr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.changeShape(SimplePaint.Shape.SQUARE);
            }
        });
        btn_circu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.changeShape(SimplePaint.Shape.CIRCLE);
            }
        });
    }

    public void abreColorPicker() {
        new ColorPickerDialog.Builder(this)
            .setTitle("ColorPicker Dialog")
            .setPreferenceName("MyColorPickerDialog")
            .setPositiveButton("Confirmar",
                new ColorEnvelopeListener() {
                    @Override
                    public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                        setColor(envelope);
                    }
                })
            .setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
            .attachAlphaSlideBar(true) // the default value is true.
            .attachBrightnessSlideBar(true)  // the default value is true.
            .setBottomSpace(12) // set a bottom space between the last slidebar and buttons.
            .show();
    }

    public void setColor(ColorEnvelope envelope) {
        simplePaint.changeColor(envelope.getColor());
    }
}