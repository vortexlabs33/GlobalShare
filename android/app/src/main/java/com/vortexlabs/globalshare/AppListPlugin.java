package com.vortexlabs.globalshare;

import android.os.Bundle;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Register our custom app scanner plugin
        registerPlugin(AppListPlugin.class);
    }
}
