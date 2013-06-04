package net.alexoro.androidutils.app;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * User: UAS
 * Date: 05.06.13
 * Time: 1:18
 */
public class ApplicationInfo {

    private Context mContext;
    private int mBuildCode;
    private String mVersion;

    public ApplicationInfo(Context context) {
        mContext = context;

        try {
            PackageInfo pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            mBuildCode = pi.versionCode;
            mVersion = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // cannot be happen
            throw new RuntimeException(e);
        }
    }

    public int getBuildCode() {
        return mBuildCode;
    }

    public String getVersion() {
        return mVersion;
    }

    public boolean isServiceDeclared(Class<? extends Service> clazz) {
        return isComponentDeclared(clazz);
    }

    public boolean isActivityDeclared(Class<? extends Activity> clazz) {
        return isComponentDeclared(clazz);
    }

    protected boolean isComponentDeclared(Class<?> clazz) {
        List resolveInfo = mContext.getPackageManager().queryIntentServices(
                new Intent(mContext, clazz),
                PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.size() > 0;
    }

}