package com.vortexlabs.globalshare;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.util.List;

@CapacitorPlugin(name = "AppList")
public class AppListPlugin extends Plugin {

    @PluginMethod()
    public void getInstalledApps(PluginCall call) {
        PackageManager pm = getContext().getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> allApps = pm.queryIntentActivities(intent, 0);

        JSArray appArray = new JSArray();

        for (ResolveInfo ri : allApps) {
            try {
                JSObject appObj = new JSObject();
                String appName = ri.loadLabel(pm).toString();
                String packageName = ri.activityInfo.packageName;
                String appPath = ri.activityInfo.applicationInfo.publicSourceDir;

                appObj.put("name", appName);
                appObj.put("packageName", packageName);
                appObj.put("path", appPath != null ? appPath : "");

                appArray.put(appObj);
            } catch (Exception e) {
                // Skip safely if any app fails to read
            }
        }

        JSObject result = new JSObject();
        result.put("apps", appArray);
        call.resolve(result);
    }
}
