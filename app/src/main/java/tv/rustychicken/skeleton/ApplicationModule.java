package tv.rustychicken.skeleton;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
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

    public static final String PREF_NAME = "user_info";

    @Provides
    @Singleton
    @ForApplication
    Context providesApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    ApiService providesApiService() {
        return new RestAdapter.Builder()
                .setEndpoint("https://www.galleyfoods.com/api")
                .build()
                .create(ApiService.class);
    }

    @Provides
    @Singleton
    ReactiveLocationProvider providesReactiveLocationProvider() {
        return new ReactiveLocationProvider(application);
    }

    @Provides
    @Singleton
    Gson providesGson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(@ForApplication Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    @CurrentUser
    @Provides
    @Singleton
    ObjectPreference<User> providesCurrentUser(SharedPreferences sharedPreferences, Gson gson) {
        return new ObjectPreference<>(sharedPreferences, gson, User.class, "user");
    }

}
