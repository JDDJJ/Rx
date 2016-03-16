package rx.dong.com.rx;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import rx.dong.com.rx.injector.component.AppComponent;

/**
 * Created by SkyEyesStion on 2016/2/23.
 */
public class BaseApp extends Application {
    private static BaseApp sInstance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }

    private void setupCompoent() {

    }

    public static BaseApp getsInstance() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
