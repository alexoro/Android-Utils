package net.alexoro.androidutils.environment;

import android.app.Activity;
import android.os.Bundle;

/**
 * User: UAS
 * Date: 05.06.13
 * Time: 1:10
 */
public class RefreshScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finish();
    }

}
