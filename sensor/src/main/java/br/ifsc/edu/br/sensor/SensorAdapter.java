package br.ifsc.edu.br.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.List;

public class SensorAdapter extends ArrayAdapter<Sensor> {
    int mResource;
    private HashMap<Integer, Float> mValores;

    public SensorAdapter(@NonNull Context context, int resource, @NonNull List<Sensor> objects) {
        super(context, resource, objects);
        mResource = resource;
        mValores = new HashMap<>();

        for (Sensor sensor : objects) {
            mValores.put(sensor.getType(), 0f);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(mResource, parent, false);

        Sensor sensor = getItem(position);

        TextView tvNome = view.findViewById(R.id.sensor_name);
        TextView tvDado = view.findViewById(R.id.sensor_dado);

        tvNome.setText(sensor.getName());
        tvDado.setText(String.format("%.2f", mValores.getOrDefault(sensor.getType(), 0f)));

        return view;
    }

    public void atualizaSensores(int sensorType, float valor) {
        mValores.put(sensorType, valor);
        notifyDataSetChanged();
    }
}
