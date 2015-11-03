package tv.rustychicken.skeleton;

import android.app.Application;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

@Module(
    injects = {
        SkeletonApplication.class,
        MainActivity.class
    },
    includes = {},
    library = true,
    complete = false
)
public class ApplicationModule {
    Application application;

    ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    ApiService providesApiService() {
        return new RestAdapter.Builder()
            .setEndpoint("www.google.com")
            .build()
            .create(ApiService.class);
    }

    @Provides
    @Singleton
    ReactiveLocationProvider providesReactiveLocationProvider() {
        return new ReactiveLocationProvider(application);
    }

}
