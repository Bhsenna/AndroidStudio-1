package br.ifsc.edu.br.taco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AlimentoAdapter extends ArrayAdapter<Alimento> {
    int mResource;
    public AlimentoAdapter(@NonNull Context context, int resource, @NonNull List<Alimento> objects) {
        super(context, resource, objects);
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(mResource, parent, false);

        Alimento alimento = getItem(position);

        TextView textViewNome = view.findViewById(R.id.nome_alimento);
        TextView textViewCategoria = view.findViewById(R.id.categoria);

        textViewNome.setText(alimento.nome_alimento);
        textViewCategoria.setText(alimento.categoria);
        return view;
    }
}
