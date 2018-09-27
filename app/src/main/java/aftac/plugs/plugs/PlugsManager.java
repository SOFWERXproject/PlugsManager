package aftac.plugs.plugs;

import android.content.Context;


public class PlugsManager{
    private String name;
    public Context context;
    //public List<PlugsTrigger> ptg = Collections.synchronizedList(new ArrayList<PlugsTrigger>());
    public PlugsSensorManager psm = new PlugsSensorManager();
    public PlugsTriggerManager ptm = new PlugsTriggerManager();

    public PlugsManager()
    {
       // Default Constructor
    }

    public void Init(Context context)
    {
        this.context = context;
        name = "SOFWERX";
        //ptg.add(new PlugsTrigger());
        psm.Init(this.context);

        // Enable Environmental Sensors
        psm.SensorEnabled(psm.plugsLight, true);
        psm.SensorEnabled(psm.plugsPressure, true);

        // Enable Motion Sensors
        psm.SensorEnabled(psm.plugsAccelerometer, true);
        psm.SensorEnabled(psm.plugsGravity, true);
        psm.SensorEnabled(psm.plugsGyroscope, true);

        // Enable Position Sensors
        psm.SensorEnabled(psm.plugsCompass, true);
        psm.SensorEnabled(psm.plugsMagnetometer, true);

       // ptm.AddTrigger(new PlugsTrigger(psm.plugsLight));
        ptm.plugsTriggerLight = new PlugsTrigger(psm.plugsLight);
    }
    public String getName()
    {
        return name;
    }

}
