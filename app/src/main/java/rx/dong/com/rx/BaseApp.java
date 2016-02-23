package rx.dong.com.rx;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by SkyEyesStion on 2016/2/23.
 */
public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
