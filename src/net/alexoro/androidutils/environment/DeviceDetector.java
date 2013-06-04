package net.alexoro.androidutils.environment;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import net.alexoro.androidutils.R;
import net.alexoro.androidutils.security.MD5;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * User: alexoro
 * Date: 29.05.13
 * Time: 12:07
 */
public class DeviceDetector {

    private Context mContext;

    public DeviceDetector(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context is null");
        }
        mContext = context;
    }

    public DeviceInfo deviceInfo() {
        //NOTICE there is a bug: on emulator density is 160, when really is 240
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);

        DeviceInfo di = new DeviceInfo();
        di.uuid = deviceUUID();
        di.manufacturer = Build.MANUFACTURER;
        di.name = Build.MODEL;
        di.screenWidth  = (int) Math.ceil(dm.widthPixels * (dm.densityDpi / 160.0));
        di.screenHeight = (int) Math.ceil(dm.heightPixels * (dm.densityDpi / 160.0));
        di.screenDensity = dm.densityDpi;
        di.isSmartphone = mContext.getResources().getBoolean(R.bool.is_mobile);
        di.isTablet = !di.isSmartphone;
        return di;
    }

    public GeoInfo geoInfo() {
        String countryIso3 = connectionInfo().country;
        if (countryIso3 == null || countryIso3.length() != 3) {
            countryIso3 = mContext.getResources().getConfiguration().locale.getISO3Country();
        }

        GeoInfo gi = new GeoInfo();
        gi.language = Locale.getDefault().getLanguage();
        gi.countryIso3 = countryIso3;
        return gi;
    }

    public OSInfo OSInfo() {
        OSInfo oi = new OSInfo();
        oi.api = Build.VERSION.SDK_INT;
        oi.apiText = Build.VERSION.RELEASE;
        oi.isRooted = isDeviceRooted();
        return oi;
    }

    public ConnectionInfo connectionInfo() {
        ConnectionInfo ci = new ConnectionInfo();
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            ci.MccMnc = tm.getNetworkOperator();
            ci.carrierName = tm.getNetworkOperatorName();
            ci.country = tm.getNetworkCountryIso();
            if (ci.country.length() == 2) {
                ci.country = CountryCodeMapper.getInstance().getIso3(ci.country);
            }
        }
        return ci;
    }

    /**
     * Checks if the device is rooted.
     *
     * @return <code>true</code> if the device is rooted, <code>false</code>
     * otherwise.
     */
    public boolean isDeviceRooted() {
        // get from build info
        String buildTags = Build.TAGS;
        if (buildTags != null && buildTags.contains("test-keys")) {
            return true;
        }

        // check if /system/app/Superuser.apk is present
        try {
            File file = new File("/system/app/Superuser.apk");
            if (file.exists()) {
                return true;
            }
        } catch (Throwable ex) {
            // ignore
        }
        return false;
    }

    public String deviceUUID() {
        // vars used to generate uuid, default values
        String deviceId = "unknown";
        String serialNumber = "unknown";
        String androidId = "unknown";

        // get device id
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            deviceId = tm.getDeviceId();
        }

        // get serial number
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            serialNumber = (String) get.invoke(c, "ro.serialno", "");
        } catch (Exception ex) {
            // ignoring
        }

        // ANDROID_ID
        androidId = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);

        // hash all and return
        return MD5.encode(deviceId + serialNumber + androidId);
    }


    //region Results

    public static class DeviceInfo {
        public String uuid;
        public String manufacturer;
        public String name;
        public int screenWidth;
        public int screenHeight;
        public int screenDensity;
        public boolean isSmartphone;
        public boolean isTablet;
    }

    public static class GeoInfo {
        public String language;
        public String countryIso3;
    }

    public static class OSInfo {
        public int api;
        public String apiText;
        public boolean isRooted;
    }

    public static class ConnectionInfo {
        public String MccMnc;
        public String carrierName;
        public String country;
    }

    //endregion

}