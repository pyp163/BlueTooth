package com.wrapper.proxyapplication;

import android.app.*;
import android.content.*;
import android.os.*;
import android.content.pm.*;
import com.tencent.bugly.yaq.crashreport.*;

public abstract class WrapperProxyApplication extends Application
{
    static Context baseContext;
    static String className;
    static Application shellApp;
    static String tinkerApp;
    
    static {
        WrapperProxyApplication.shellApp = null;
        WrapperProxyApplication.className = "com.qx.qgbox.activity.MyApplication";
        WrapperProxyApplication.tinkerApp = "tinker not support";
    }
    
    private boolean Fixappname() {
        synchronized (this) {
            if (WrapperProxyApplication.className.startsWith(".")) {
                WrapperProxyApplication.className = String.valueOf(super.getPackageName()) + WrapperProxyApplication.className;
            }
            else if (WrapperProxyApplication.className.indexOf(".") < 0) {
                WrapperProxyApplication.className = String.valueOf(super.getPackageName()) + "." + WrapperProxyApplication.className;
            }
            return true;
        }
    }
    
    public static void fixAndroid(final Context context, final Application application) {
        if (Build$VERSION.SDK_INT != 28) {
            return;
        }
        try {
            AndroidNClassLoader.inject(context.getClassLoader(), application);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    private static String getVersionCode(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }
        catch (PackageManager$NameNotFoundException ex) {
            ex.printStackTrace();
            return "0";
        }
    }
    
    static Context getWrapperProxyAppBaseContext() {
        return WrapperProxyApplication.baseContext;
    }
    
    native void Ooo0ooO0oO();
    
    protected void attachBaseContext(final Context context) {
        super.attachBaseContext(context);
        WrapperProxyApplication.baseContext = this.getBaseContext();
        if (WrapperProxyApplication.shellApp == null) {
            WrapperProxyApplication.shellApp = this;
        }
        CrashReport.setAppVersion(context, "4.1.0.19");
        CrashReport.setSdkExtraData((Context)this, "900053609", "4.1.0.19");
        CrashReport.initCrashReport((Context)this, "900053609", false);
        this.Fixappname();
        this.initProxyApplication(context);
    }
    
    protected abstract void initProxyApplication(final Context p0);
    
    public void onCreate() {
        super.onCreate();
        this.Ooo0ooO0oO();
    }
}
