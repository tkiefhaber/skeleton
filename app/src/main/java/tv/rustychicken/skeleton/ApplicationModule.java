package tv.rustychicken.skeleton;

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
    library = false,
    complete = false
)
public class ApplicationModule {
    @Provides
    @Singleton
    ApiService providesApiService() {
        return new RestAdapter.Builder()
            .setEndpoint("www.google.com")
            .build()
            .create(ApiService.class);
    }

}
