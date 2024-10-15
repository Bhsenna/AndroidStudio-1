package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {
    Button btn_cores;
    Button btn_volta;
    SimplePaint simplePaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_cores = findViewById(R.id.btn_cores);
        btn_volta = findViewById(R.id.btn_volta);
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
    }

    public void abreColorPicker() {
        new ColorPickerDialog.Builder(this)
            .setTitle("ColorPicker Dialog")
            .setPreferenceName("MyColorPickerDialog")
            .setPositiveButton("Confirm",
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