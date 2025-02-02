package br.ifsc.edu.br.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager mSensorManager;
    List<Sensor> listSensor;
    ListView listView;
    SensorAdapter sensorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview_sensors);

        getApplicationContext();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        listSensor = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor : listSensor) {
            mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        sensorAdapter = new SensorAdapter(this, R.layout.item_lista, listSensor);
        listView.setAdapter(sensorAdapter);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        sensorAdapter.atualizaSensores(event.sensor.getType(), event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}