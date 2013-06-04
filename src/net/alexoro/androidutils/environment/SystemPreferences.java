package net.alexoro.androidutils.environment;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import net.alexoro.androidutils.app.ApplicationInfo;

/**
 * User: UAS
 * Date: 05.06.13
 * Time: 0:41
 */
public class SystemPreferences {

    private Context mContext;

    public SystemPreferences(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context is null");
        }
        mContext = context;
    }

    public void setAirplaneMode(boolean enabled) {
        android.provider.Settings.System.putInt(
                mContext.getContentResolver(),
                android.provider.Settings.System.AIRPLANE_MODE_ON,
                enabled ? 1 : 0
        );
    }

    public boolean isAirplaneModeEnabled() {
        try {
            int s = android.provider.Settings.System.getInt(
                    mContext.getContentResolver(),
                    android.provider.Settings.System.AIRPLANE_MODE_ON
            );
            return s != 0;
        } catch (Settings.SettingNotFoundException e) {
            return false;
        }
    }

    public void setBrightness(int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("Available value of brightness is [0, 255]");
        }
        if (!new ApplicationInfo(mContext).isActivityDeclared(RefreshScreenActivity.class)) {
            throw new IllegalStateException(RefreshScreenActivity.class.getCanonicalName() + "must be declared in AndroidManifest.xml");
        }

        android.provider.Settings.System.putInt(
                mContext.getContentResolver(),
                android.provider.Settings.System.SCREEN_BRIGHTNESS,
                value
        );

        // it is required to apply the new color (refresh the screen)
        mContext.startActivity(new Intent(mContext, RefreshScreenActivity.class));
    }

    public int getBrightnessValue() {
        return android.provider.Settings.System.getInt(
                mContext.getContentResolver(),
                android.provider.Settings.System.SCREEN_BRIGHTNESS,
                -1
        );
    }

    public void setScreenAlwaysOnEnabled(boolean enable) {
        int screenTimeoutPreviousValue = android.provider.Settings.System.getInt(
                mContext.getContentResolver(),
                android.provider.Settings.System.SCREEN_OFF_TIMEOUT,
                0
        );
        android.provider.Settings.System.putInt(
                mContext.getContentResolver(),
                android.provider.Settings.System.SCREEN_OFF_TIMEOUT,
                enable ? -1 : screenTimeoutPreviousValue
        );
    }

    public boolean isScreenAlwaysOnEnabled() {
        int value = android.provider.Settings.System.getInt(
                mContext.getContentResolver(),
                android.provider.Settings.System.SCREEN_OFF_TIMEOUT,
                0
        );
        return (value == -1);
    }

    public void setAutoSyncEnabled(boolean enable) {
        ContentResolver.setMasterSyncAutomatically(enable);
    }

    public boolean isAutoSyncEnabled() {
        return ContentResolver.getMasterSyncAutomatically();
    }

    public void setAutoRotateEnabled(boolean enable) {
        android.provider.Settings.System.putInt(
                mContext.getContentResolver(),
                android.provider.Settings.System.ACCELEROMETER_ROTATION,
                enable ? 1 : 0
        );
    }

    public boolean getAutoRotate() {
        int value = android.provider.Settings.System.getInt(
                mContext.getContentResolver(),
                android.provider.Settings.System.ACCELEROMETER_ROTATION,
                0
        );
        return (value == 1);
    }

}