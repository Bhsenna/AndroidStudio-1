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

public class PreparoAdapter extends ArrayAdapter<Preparo> {
    int mResource;
    public PreparoAdapter(@NonNull Context context, int resource, @NonNull List<Preparo> objects) {
        super(context, resource, objects);
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(mResource, parent, false);

        Preparo preparo = getItem(position);

        TextView textViewCod = view.findViewById(R.id.cod_preparo);
        TextView textViewForma = view.findViewById(R.id.forma_preparo);

        textViewCod.setText(preparo.cod+"");
        textViewForma.setText(preparo.forma);

        return view;
    }
}
