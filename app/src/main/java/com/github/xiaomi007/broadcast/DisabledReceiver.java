package com.github.xiaomi007.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by xiaomi on 15/10/02.
 */
public class DisabledReceiver extends BroadcastReceiver {

    private static final String TAG = DisabledReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (MainActivity.ACTION_DISABLED.equals(action)) {
            Log.d(TAG, "disabled receiver reached");
        }
    }
}
