package br.ifsc.edu.br.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AtividadeA extends AppCompatActivity {
    EditText edt_text;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade);
        edt_text = findViewById(R.id.editTextText);
        btn_send = findViewById(R.id.button_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AtividadeA.this, AtividadeB.class);
                String mensagem = edt_text.getText().toString();
                i.putExtra("msg", mensagem);
                startActivity(i);
            }
        });
    }
}