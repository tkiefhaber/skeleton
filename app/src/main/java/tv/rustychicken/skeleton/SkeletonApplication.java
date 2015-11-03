package tv.rustychicken.skeleton;

import android.app.Application;

import retrofit.RestAdapter;
import timber.log.Timber;

public class SkeletonApplication extends Application {
    private ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // create some sort of crashlytics tree or whatever
        }
        apiService = new RestAdapter.Builder().setEndpoint("www.google.com").build().create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}
