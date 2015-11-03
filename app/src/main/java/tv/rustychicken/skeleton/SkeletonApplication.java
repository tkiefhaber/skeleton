package tv.rustychicken.skeleton;

import android.app.Application;

import dagger.ObjectGraph;
import timber.log.Timber;

public class SkeletonApplication extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // create some sort of crashlytics tree or whatever
        }
        objectGraph = ObjectGraph.create(new ApplicationModule());
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }

}
