package aftac.plugs.plugs;

import java.util.ArrayList;
import java.util.List;

public class PlugsTriggerManager {

    private List<PlugsTrigger> ptl = new ArrayList();
    public PlugsTrigger plugsTriggerLight;
    public PlugsTriggerManager()
    {

    }

    public void AddTrigger(PlugsTrigger trigger)
    {
        if(trigger != null)ptl.add(trigger);
    }

    public void RemoveTrigger(PlugsTrigger trigger)
    {
        if(trigger != null)
        {
            ptl.remove(trigger);
        }
    }

}
