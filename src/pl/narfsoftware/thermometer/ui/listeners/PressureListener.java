package pl.narfsoftware.thermometer.ui.listeners;

import pl.narfsoftware.thermometer.ui.SensorsListViewAdapter;
import pl.narfsoftware.thermometer.utils.SensorRow;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.util.Log;

public class PressureListener extends BaseUIListener {
	static final String TAG = "PressureListener";

	public PressureListener(Context context, SensorsListViewAdapter adapter,
			SensorRow sensorRow) {
		super(context, adapter, sensorRow);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		value = event.values[0];
		stringValue = (String.format("%.0f", value) + " hPa");

		super.onSensorChanged(event);

		Log.d(TAG, "Got pressure sensor event with value: " + value);
	}

	@Override
	public boolean register() {
		return sensors.sensorManager.registerListener(this,
				sensors.sensors.get(Sensor.TYPE_PRESSURE),
				SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	public void unregister() {
		sensors.sensorManager.unregisterListener(this,
				sensors.sensors.get(Sensor.TYPE_PRESSURE));
	}
}
