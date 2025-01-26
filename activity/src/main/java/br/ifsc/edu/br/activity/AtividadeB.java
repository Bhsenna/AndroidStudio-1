package br.ifsc.edu.br.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AtividadeB extends AppCompatActivity {
    TextView txt_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_b);
        txt_text = findViewById(R.id.textView);

        Bundle bundle = getIntent().getExtras();
        String texto = bundle.getString("msg");

        txt_text.setText(texto);
    }
}