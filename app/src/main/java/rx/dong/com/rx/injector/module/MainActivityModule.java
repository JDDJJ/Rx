package rx.dong.com.rx.injector.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import rx.dong.com.rx.injector.ActivityScope;

/**
 * Created by SkyEyesStion on 2016/3/14.
 */
@Module
public class MainActivityModule {
    private Context context;
    private int anInt;

    public MainActivityModule(Context context, int i) {
        this.context = context;
        anInt = i;
    }

    @Provides
    @ActivityScope
    Context provideActivityContext() {
        return context;
    }

    @Provides
    @ActivityScope
    int provideInt() {
        return anInt;
    }

}
