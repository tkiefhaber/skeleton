package tv.rustychicken.skeleton;

import android.content.SharedPreferences;

import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.Subscriptions;

public final class SharedPreferencesObservable {

    private SharedPreferencesObservable() {
        throw new AssertionError("No Instances");
    }

    public synchronized static Observable<String> observe(final SharedPreferences preferences) {
        if (preferences == null) {
            throw new IllegalArgumentException("sharedPreferences == null");
        }
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                final SharedPreferences.OnSharedPreferenceChangeListener listener =
                        (sharedPreferences, key) -> subscriber.onNext(key);

                preferences.registerOnSharedPreferenceChangeListener(listener);

                subscriber.add(Subscriptions.create(
                        () -> preferences.unregisterOnSharedPreferenceChangeListener(listener)));
            }
        });
    }
}
