package br.ifsc.edu.br.taco;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    SearchView searchView;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private SQLiteDatabase banco;
    private ArrayList<Alimento> alimentos;
    String pesquisa;
    String sqlQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        banco = openOrCreateDatabase("banco", getBaseContext().MODE_PRIVATE, null);

//        lerBanco(banco, "taco_converted_sqlite.sql");

        pesquisa = "";
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) pesquisa = bundle.getString("pesquisa", "");
        fazPesquisa(pesquisa);
        searchView.setQuery(pesquisa, false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                pesquisa = query;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pesquisa = newText;
                fazPesquisa(pesquisa);
                return false;
            }
        });
    }

    private void fazPesquisa(String param) {
        sqlQuery =  "SELECT * from ( " +
                        "SELECT codigo, nome_alimento, categoria from alimentos_lip_acucares_100g " +
                            "union " +
                        "SELECT codigo, nome_alimento, categoria from alimentos_macros_100g " +
                            "union " +
                        "SELECT codigo, nome_alimento, categoria from minerais_100g " +
                            "union " +
                        "SELECT codigo, nome_alimento, categoria from vitaminas " +
                    ") where nome_alimento is not NULL " +
                    "and ( " +
                        "nome_alimento like '%" + param + "%' " +
                            "or " +
                        "categoria like '%" + param + "%' " +
                    ") " +
                    "order by nome_alimento";
        Cursor cursor = banco.rawQuery(sqlQuery, null);
        alimentos = new ArrayList<>();
        while (cursor.moveToNext()) {
            int codigo = cursor.getInt(cursor.getColumnIndexOrThrow("codigo"));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome_alimento"));
            String categoria = cursor.getString(cursor.getColumnIndexOrThrow("categoria"));
            alimentos.add(new Alimento(codigo, nome, categoria));
        }
        cursor.close();

        AlimentoAdapterRecyclerView alimentoAdapterRV = new AlimentoAdapterRecyclerView(this, R.layout.alimento_item, alimentos);
        recyclerView.setAdapter(alimentoAdapterRV);

        alimentoAdapterRV.setOnClickListener(new AlimentoAdapterRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(Alimento alimento) {
                Intent i = new Intent(MainActivity.this, InfoActivity.class);
                i.putExtra("pesquisa", pesquisa);
                i.putExtra("codigo", alimento.codigo);
                startActivity(i);
            }
        });
    }

    private void lerBanco(SQLiteDatabase banco, String nomeArquivo) {
        String queries = "";
        try {
            InputStream inputStream = getAssets().open(nomeArquivo);
            queries = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e);
        }
        for (String query : queries.split(";")) {
            if (!query.trim().equals("")) {
                banco.execSQL(query);
            }
        }
    }
}