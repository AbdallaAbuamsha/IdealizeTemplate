package sy.syriatel.abdalla.idealizetemplate;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.multidex.MultiDex;

import java.util.UUID;
/**
 * Created by Abdalla on 10/30/2018.
 */

public class IdealizeApplication extends Application {
    public static String APP_VERSION;
    public static String DEVICE_ID;

    private static IdealizeApplication sInstance;

    public static synchronized IdealizeApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        APP_VERSION = getAppVersion();
        DEVICE_ID = getUniqueDeviceID();
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    private String getAppVersion() {
        PackageManager manager = getPackageManager();
        PackageInfo info;
        String version;
        try {
            info = manager.getPackageInfo(getPackageName(), 0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            version = "1";
        }
        return version;
    }

    private static String getUniqueDeviceID() {
        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) +
                (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) +
                (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);

        String serial;
        try {
            serial = android.os.Build.class.getField("SERIAL").get(null).toString();

            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            serial = Settings.Secure.ANDROID_ID;
        }

        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }
}
