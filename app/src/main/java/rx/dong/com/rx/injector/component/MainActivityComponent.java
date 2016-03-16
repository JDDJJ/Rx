package rx.dong.com.rx.injector.component;

import dagger.Component;
import rx.dong.com.rx.injector.ActivityScope;
import rx.dong.com.rx.injector.module.MainActivityModule;
import rx.dong.com.rx.ui.MainActivity;

/**
 * Created by SkyEyesStion on 2016/3/14.
 */
@ActivityScope
@Component(modules = {MainActivityModule.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
