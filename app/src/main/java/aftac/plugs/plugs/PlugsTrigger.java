package aftac.plugs.plugs;

import android.hardware.Sensor;

public class PlugsTrigger implements PlugsSensorManager.PlugsSensorEventListener
{
    private PlugsSensorManager.PlugsSensor plugsSensor;
    private boolean isTriggered = false;
    public PlugsTrigger(PlugsSensorManager.PlugsSensor plugsSensor)
    {
        this.plugsSensor = plugsSensor;
        this.plugsSensor.setSensorEventListener(this);
    }

    @Override
    public void onSensorEvent(PlugsSensorManager.PlugsSensor plugsSensor)
    {
        if(plugsSensor.sensor.getType() == Sensor.TYPE_LIGHT)
        {
            if(((PlugsSensorManager.Light)plugsSensor).value > 10000)
            {
                isTriggered = true;
            }
            else
            {
                isTriggered = false;
            }
        }
    }

    public boolean IsTriggered() {
        return isTriggered;
    }
}