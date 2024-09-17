package br.ifsc.edu.br.contador;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mybutton = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);
        EditText editTextInicial = findViewById(R.id.editTextNumInicial);
        EditText editTextFinal = findViewById(R.id.editTextNumFinal);
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                Random random = new Random();
                int num_inicial;
                try {
                    num_inicial = Integer.parseInt(editTextInicial.getText().toString().trim());
                } catch (Exception e) {
                    num_inicial = 0;
                }
                int num_final;
                try {
                    num_final = Integer.parseInt(editTextFinal.getText().toString().trim());
                } catch (Exception e) {
                    num_final = 1;
                }
                textView.setText(Integer.toString(random.nextInt(num_final+1 - num_inicial) + num_inicial));
            }
        });
    }
}