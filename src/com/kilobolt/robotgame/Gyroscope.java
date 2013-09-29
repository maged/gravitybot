package com.kilobolt.robotgame;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.kilobolt.robotgame.SampleGame;

public class Gyroscope implements SensorEventListener{ 
	static float zRotation;
	static float xRotation;
	private float initZ;
	private float initX;
	static boolean setDefault = true; 
	private static SensorManager sensorManager;
    private static Sensor sensor;
	
    public Gyroscope(SampleGame samplegame) {
    	sensorManager = samplegame.mSensorManager;
    	sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    	sensorManager.registerListener(this, sensor, 10000);
    	zRotation = (float)0.0;
    }
    
    public void start() {
        // enable our sensor when the activity is resumed, ask for
        // 10 ms updates.
        sensorManager.registerListener(this, sensor, 10000);
    }

	
    public void onSensorChanged(SensorEvent event) {
        // we received a sensor event. it is a good practice to check
        // that we received the proper event
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
        	zRotation = event.values[2];
        	xRotation = event.values[0];
        	if (setDefault){
        		initZ = 0;
        		initX = 0;
        		setDefault = false; 
        	}

        	zRotation -= initZ;
        	xRotation -= initX;
        }
    }
    
    public float getRotation() {
    	return zRotation;
    }

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// do nothing
		
	}
}
