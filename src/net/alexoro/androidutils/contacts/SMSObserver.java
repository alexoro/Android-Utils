package net.alexoro.androidutils.contacts;

import android.database.ContentObserver;
import android.os.Handler;

/**
 * User: alexoro
 * Date: 05.06.13
 * Time: 2:00
 */

//TODO not finished yet
public class SMSObserver extends ContentObserver {

    public SMSObserver(Handler handler) {
        super(handler);
    }


    @Override
    public boolean deliverSelfNotifications() {
        return true;
    }


    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
    } // <-- public void onChange()

} // <-- SMSObserver()