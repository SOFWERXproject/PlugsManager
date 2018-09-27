package aftac.plugs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import aftac.plugs.plugs.PlugsManager;

import static aftac.plugs.Global.*;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "Place holder text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Init();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getBaseContext(), "Welcome to the PLUGS application", Toast.LENGTH_LONG).show();
        Log.d("onCreate","Starting Application");
    }

    public void Init()
    {
        if(!initialized) {

            // Set initialize to true
            initialized = true;

            // Assign context to this object
            context = this;

            // Instantiate object
            pm = new PlugsManager();

            // Initialize object
            pm.Init(context);


        }
    }

    public void Settings(View view)
    {
        Intent intent = new Intent(this, DisplaySensors.class);
        startActivity(intent);
    }
}
