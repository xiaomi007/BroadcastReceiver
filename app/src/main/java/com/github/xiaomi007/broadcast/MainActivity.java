package com.github.xiaomi007.broadcast;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_LBM = "action_lbm";
    public static final String ACTION_REGISTER = "action_register";
    public static final String ACTION_DISABLED = "action_disabled";
    public static final String ACTION_ENABLED = "action_enabled";

    private LBMReceiver lbmReceiver;
    private RegisteredReceiver registeredReceiver;
    private ComponentName disabledReceiverName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        lbmReceiver = new LBMReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(lbmReceiver, new IntentFilter(ACTION_LBM));
        Intent intentlbm = new Intent(this, LBMReceiver.class);
        intentlbm.setAction(ACTION_LBM);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intentlbm);


        registeredReceiver = new RegisteredReceiver();
        registerReceiver(registeredReceiver, new IntentFilter(ACTION_REGISTER));
        Intent registeredIntent = new Intent(this, RegisteredReceiver.class);
        registeredIntent.setAction(ACTION_REGISTER);
        sendBroadcast(registeredIntent);


        disabledReceiverName = new ComponentName(this, DisabledReceiver.class);
        getPackageManager().setComponentEnabledSetting(disabledReceiverName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        Intent disabledIntent = new Intent(this, DisabledReceiver.class);
        disabledIntent.setAction(ACTION_DISABLED);
        sendBroadcast(disabledIntent);

        Intent enabledIntent = new Intent(this, EnabledReceiver.class);
        enabledIntent.setAction(ACTION_ENABLED);
        sendBroadcast(enabledIntent);

    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(lbmReceiver);
        unregisterReceiver(registeredReceiver);
        getPackageManager().setComponentEnabledSetting(disabledReceiverName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        super.onPause();
    }
}
