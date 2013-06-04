package net.alexoro.androidutils.environment;

import android.content.Intent;

/**
 * User: UAS
 * Date: 05.06.13
 * Time: 1:31
 */
public class Battery {

    /**
     * Converts intent data to real battery level (in percents from 0 to 100)
     * @param intent
     * @return -1 If intent data cannot be parsed, or value in range [0, 100]
     */
    public static int decodeBatteryLevelFromIntent(Intent intent) {
        if (!Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
            throw new IllegalArgumentException("Method can apply only intents with action = ACTION_BATTERY_CHANGED");
        }

        int rawlevel = intent.getIntExtra("level", -1);
        int scale = intent.getIntExtra("scale", -1);
        int level = -1;
        if (rawlevel >= 0 && scale > 0) {
            level = (rawlevel * 100) / scale;
        }
        return level;
    }

}
