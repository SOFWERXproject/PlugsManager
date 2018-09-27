package aftac.plugs;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.view.View;

import aftac.plugs.plugs.PlugsSensorManager;

import static aftac.plugs.Global.*;

public class DisplaySensors extends AppCompatActivity {

    ProgressBar pbLight;// = findViewById(R.id.pbLight);
    public static float LightValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        pbLight = findViewById(R.id.pbLight);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Get and Set Environment Sensor state
        ((Switch)findViewById(R.id.switchLightMeter)).setChecked(pm.psm.plugsLight.enabled);
        ((Switch)findViewById(R.id.switchPressure)).setChecked(pm.psm.plugsPressure.enabled);

        // Get and Set Motion Sensor state
        ((Switch)findViewById(R.id.switchAccelerometer)).setChecked(pm.psm.plugsAccelerometer.enabled);
        ((Switch)findViewById(R.id.switchGravity)).setChecked(pm.psm.plugsGravity.enabled);
        ((Switch)findViewById(R.id.switchGyroscope)).setChecked(pm.psm.plugsGyroscope.enabled);

        // Get and Set Position Sensor state
        ((Switch)findViewById(R.id.switchCompass)).setChecked(pm.psm.plugsCompass.enabled);
        ((Switch)findViewById(R.id.switchMagnetometer)).setChecked(pm.psm.plugsMagnetometer.enabled);


        pbLight.setMax((int)((PlugsSensorManager.Light)pm.psm.plugsLight).maximumRange);

        Runnable sensorUpdate = new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    try {
                        Thread.sleep(50);

                        // Get Sensor Data
                        LightValue = ((PlugsSensorManager.Light)pm.psm.plugsLight).getValue();
                        ((ProgressBar)findViewById(R.id.pbLight)).setProgress((int)((PlugsSensorManager.Light)pm.psm.plugsLight).getValue(), true);
                        if(pm.ptm.plugsTriggerLight.IsTriggered())
                        {
                            ((ProgressBar)findViewById(R.id.pbLight)).setProgressTintList(ColorStateList.valueOf(Color.GREEN));
                        }
                        else
                        {
                            ((ProgressBar)findViewById(R.id.pbLight)).setProgressTintList(ColorStateList.valueOf(Color.BLUE));
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread mSensorUpdate = new Thread(sensorUpdate);
        mSensorUpdate.start();
    }

    public void GoBack(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void EnableAccelerometer(View view)
    {
        pm.psm.SensorEnabled(pm.psm.plugsAccelerometer, ((Switch)findViewById(R.id.switchAccelerometer)).isChecked());
    }

    public void EnableCompass(View view)
    {
        pm.psm.SensorEnabled(pm.psm.plugsCompass, ((Switch)findViewById(R.id.switchCompass)).isChecked());
    }

    public void EnableGyroscope(View view)
    {
        pm.psm.SensorEnabled(pm.psm.plugsGyroscope, ((Switch)findViewById(R.id.switchGyroscope)).isChecked());
    }

    public void EnableMagnetometer(View view)
    {
        pm.psm.SensorEnabled(pm.psm.plugsMagnetometer, ((Switch)findViewById(R.id.switchMagnetometer)).isChecked());
    }

    public void EnableLight(View view)
    {
        pm.psm.SensorEnabled(pm.psm.plugsLight, ((Switch)findViewById(R.id.switchLightMeter)).isChecked());
    }

    public void EnablePressure(View view)
    {
        pm.psm.SensorEnabled(pm.psm.plugsPressure, ((Switch)findViewById(R.id.switchPressure)).isChecked());
    }

    public void EnableGravity(View view)
    {
        pm.psm.SensorEnabled(pm.psm.plugsGravity, ((Switch)findViewById(R.id.switchGravity)).isChecked());
    }

}
