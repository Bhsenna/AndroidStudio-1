package br.ifsc.edu.br.taco;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {
    private SQLiteDatabase banco;
    String pesquisa;
    int codigo;
    ImageButton btn_volta;
    ListView listview_preparos;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        btn_volta = findViewById(R.id.btn_volta);
        listview_preparos = findViewById(R.id.listview_preparos);

        Bundle bundle = getIntent().getExtras();
        pesquisa = bundle.getString("pesquisa");
        codigo = bundle.getInt("codigo");

        btn_volta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InfoActivity.this, MainActivity.class);
                i.putExtra("pesquisa", pesquisa);
                startActivity(i);
            }
        });

        banco = openOrCreateDatabase("banco", getBaseContext().MODE_PRIVATE, null);

        String query =  "SELECT distinct cod_preparo, forma_preparo from ( " +
                            "SELECT codigo, cod_preparo, forma_preparo from alimentos_lip_acucares_100g " +
                                "union " +
                            "SELECT codigo, cod_preparo, forma_preparo from alimentos_macros_100g " +
                                "union " +
                            "SELECT codigo, cod_preparo, forma_preparo from minerais_100g " +
                                "union " +
                            "SELECT codigo, cod_preparo, forma_preparo from vitaminas " +
                        ") where codigo = " + codigo;
        cursor = banco.rawQuery(query, null);
        if (cursor.moveToNext()) {
            int indexCod = cursor.getColumnIndexOrThrow("cod_preparo");
            int indexForma = cursor.getColumnIndexOrThrow("forma_preparo");
            ArrayList<Preparo> preparos = new ArrayList<>();

            do {
                int cod = cursor.getInt(indexCod);
                String forma = cursor.getString(indexForma);
                preparos.add(new Preparo(cod, forma));
            } while (cursor.moveToNext());

            PreparoAdapter appAdapter = new PreparoAdapter(this, R.layout.item_lista, preparos);
            listview_preparos.setAdapter(appAdapter);
        }
        cursor.close();

        query = "SELECT * from alimentos_lip_acucares_100g " +
                "where codigo = " + codigo;
        cursor = banco.rawQuery(query, null);
        if (cursor.moveToNext()) {
            ((TextView) findViewById(R.id.colesterol_mg)).setText(String.valueOf(cursor.getFloat(cursor.getColumnIndexOrThrow("colesterol_mg"))));
            ((TextView) findViewById(R.id.axidosgraxos_saturados_g)).setText(String.valueOf(cursor.getFloat(cursor.getColumnIndexOrThrow("axidosgraxos_saturados_g"))));
            ((TextView) findViewById(R.id.axidosgraxos_monosaturados_g)).setText(String.valueOf(cursor.getFloat(cursor.getColumnIndexOrThrow("axidosgraxos_monosaturados_g"))));
            ((TextView) findViewById(R.id.axidosgraxos_polisaturados_g)).setText(String.valueOf(cursor.getFloat(cursor.getColumnIndexOrThrow("axidosgraxos_polisaturados_g"))));
            ((TextView) findViewById(R.id.axidograxos_linoleicos_g)).setText(String.valueOf(cursor.getFloat(cursor.getColumnIndexOrThrow("axidograxos-linoleicos_g"))));
            ((TextView) findViewById(R.id.Axidograxos_linoleinos_g)).setText(String.valueOf(cursor.getFloat(cursor.getColumnIndexOrThrow("Axidograxos-linoleinos_g"))));
            ((TextView) findViewById(R.id.axidograxo_trans_totais_g)).setText(String.valueOf(cursor.getFloat(cursor.getColumnIndexOrThrow("axidograxo-trans_totais_g"))));
            ((TextView) findViewById(R.id.acucares_totais_g)).setText(String.valueOf(cursor.getFloat(cursor.getColumnIndexOrThrow("acucares_totais_g"))));
            ((TextView) findViewById(R.id.acucares_adicao_g)).setText(String.valueOf(cursor.getFloat(cursor.getColumnIndexOrThrow("acucares_adicao_g"))));
        }
        cursor.close();
    }
}