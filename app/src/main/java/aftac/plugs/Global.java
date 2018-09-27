package aftac.plugs;

import android.app.Application;
import aftac.plugs.plugs.PlugsManager;

public final class Global extends Application
{

    public static aftac.plugs.MainActivity context = null;
    public static PlugsManager pm = new PlugsManager();
    public static boolean initialized = false;
}
