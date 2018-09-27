package aftac.plugs.plugs;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class PlugsSensorManager extends Activity {
    public Context context;
    public SensorManager sm;
    public SensorEventListener sel;

    // Declare Environmental Sensors
    public PlugsSensor plugsLight;
    public PlugsSensor plugsPressure;

    // Declare Motion Sensors
    public PlugsSensor plugsAccelerometer;
    public PlugsSensor plugsGravity;
    public PlugsSensor plugsGyroscope;

    // Declare Position Sensors
    public PlugsSensor plugsCompass;
    public PlugsSensor plugsMagnetometer;

    public PlugsSensorManager()
    {

    }

    public void Init(Context context)
    {
        this.context = context;
        sm = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                {

                    Log.d("Accelerometer_Tag", Float.toString(event.values[0]) + "\t" + Float.toString(event.values[1]) +  "\t" + Float.toString(event.values[2]));
                }
                else if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE)
                {
                    Log.d("Gyroscope_Tag", Float.toString(event.values[0]) + " \t" + Float.toString(event.values[1]) +  "\t" + Float.toString(event.values[2]));
                }
                else if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
                {
                    Log.d("Magnetic_Field_Tag", Float.toString(event.values[0]) + " \t" + Float.toString(event.values[1]) +  "\t" + Float.toString(event.values[2]));
                }
                else if(event.sensor.getType() == Sensor.TYPE_ORIENTATION)
                {
                    Log.d("Orientation_Tag", Float.toString(event.values[0]));
                }
                else if(event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE)
                {
                    Log.d("AmbientTemperature_Tag", Float.toString(event.values[0]));
                }
                else if(event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY)
                {
                    Log.d("RelativeHumidity_Tag", Float.toString(event.values[0]));
                }
                else if(event.sensor.getType() == Sensor.TYPE_LIGHT)
                {
                    ((Light)plugsLight).value = event.values[0];
                    if(plugsLight.psel != null)plugsLight.psel.onSensorEvent(plugsLight);
                    Log.d("Light_Tag", Float.toString(event.values[0]));
                }
                else if(event.sensor.getType() == Sensor.TYPE_PRESSURE)
                {
                    Log.d("Pressure_Tag", Float.toString(event.values[0]));
                }
                else if(event.sensor.getType() == Sensor.TYPE_TEMPERATURE)
                {
                    Log.d("Temperature_Tag", Float.toString(event.values[0]));
                }
                else if(event.sensor.getType() == Sensor.TYPE_GRAVITY)
                {
                    Log.d("Gravity_Tag", Float.toString(event.values[0]) + " \t" + Float.toString(event.values[1]) +  "\t" + Float.toString(event.values[2]));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        // Set environmental sensor types
        plugsLight = new Light(sm);
        plugsPressure = new Pressure(sm);

        // Set motion sensor types
        plugsAccelerometer  = new Accelerometer(sm);
        plugsGravity = new Gravity(sm);
        plugsGyroscope = new Gyroscope(sm);

        // Set position sensor types
        plugsCompass = new Compass(sm);
        plugsMagnetometer = new Magnetometer(sm);

        // Add event listener
        //plugsLight.setSensorEventListener(new PlugsSensorEventListener);


    }

    // Enable PLUGS sensor
    public void SensorEnabled(PlugsSensor ps, boolean enabled)
    {
        if(enabled)
        {
            ps.enabled = true;
            sm.registerListener(sel, ps.sensor, SensorManager.SENSOR_DELAY_GAME);
        }
        else
        {
            ps.enabled = false;
            sm.unregisterListener(sel, ps.sensor);
        }
    }


    public class PlugsSensor
    {
        public boolean enabled = false;
        public SensorManager sm;
        public Sensor sensor;
        private PlugsSensorEventListener psel;

        public PlugsSensor()
        {

        }

        public void setSensorEventListener(PlugsSensorEventListener psel)
        {
            this.psel = psel;
        }
    }

    // Define environmental sensors
    public class Light extends PlugsSensor
    {
        public String units = "lx";
        public float value = 0;
        public float maximumRange;
        public Light(SensorManager sm)
        {
            this.sm = sm;
            sensor = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
            maximumRange = this.sensor.getMaximumRange();
        }

        public synchronized float getValue()
        {
            return value;
        }

    }

    public class Pressure extends PlugsSensor
    {
        public String units = "mBar";
        public Pressure(SensorManager sm)
        {
            this.sm = sm;
            sensor = sm.getDefaultSensor(Sensor.TYPE_PRESSURE);
        }
    }

    // Define motion sensors
    public class Accelerometer extends PlugsSensor
    {
        public Accelerometer(SensorManager sm)
        {
            this.sm = sm;
            sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }

    public class Gravity extends PlugsSensor
    {
        public Gravity(SensorManager sm)
        {
            this.sm = sm;
            sensor = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        }
    }

    public class Gyroscope extends PlugsSensor
    {
        public Gyroscope(SensorManager sm)
        {
            this.sm = sm;
            sensor = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        }
    }

    // Define position sensors
    public class Compass extends PlugsSensor
    {
        public Compass(SensorManager sm)
        {
            this.sm = sm;
            sensor = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        }
    }

    public class Magnetometer extends PlugsSensor
    {
        public Magnetometer(SensorManager sm)
        {
            this.sm = sm;
            sensor = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        }
    }

    public interface PlugsSensorEventListener
    {
        void onSensorEvent(PlugsSensor sensor);
    }
}
