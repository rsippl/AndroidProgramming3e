package com.bignerdranch.android.mockwalker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ShutdownReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent shutdown = new Intent(context, MockWalkerService.class);
        context.stopService(shutdown);
    }
}
