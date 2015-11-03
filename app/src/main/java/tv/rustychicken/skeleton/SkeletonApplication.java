package tv.rustychicken.skeleton;

import android.app.Application;

import timber.log.Timber;

public class SkeletonApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // create some sort of crashlytics tree or whatever
        }
    }

}
