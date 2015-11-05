package tv.rustychicken.skeleton;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public abstract class AbstractPreference<T> {

    final SharedPreferences sharedPreferences;
    final String key;
    final T defaultValue;

    AbstractPreference(SharedPreferences sharedPreferences, String key, T defaultValue) {
        if (sharedPreferences == null) {
            throw new IllegalArgumentException("sharedPreferences must not be null");
        }
        if (key == null || key.trim().length() == 0) {
            throw new IllegalArgumentException("key must not be null");
        }
        this.sharedPreferences = sharedPreferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public abstract T get();

    public abstract void set(T value);

    public boolean isSet() {
        return sharedPreferences.contains(key);
    }

    @SuppressLint("CommitPrefEdits")
    public void delete() {
        sharedPreferences.edit().remove(key).commit();
    }

    public final Observable<T> toObservable() {
        return Observable.create(new OnSubscribeFromPreference());
    }

    final class OnSubscribeFromPreference implements Observable.OnSubscribe<T> {

        @Override
        public void call(final Subscriber<? super T> subscriber) {
            subscriber.onNext(get());

            final Subscription subscription = SharedPreferencesObservable.observe(sharedPreferences)
                    .filter(s -> s.equals(key))
                    .subscribe(new EndlessObserver<String>() {
                        @Override
                        public void onNext(String s) {
                            subscriber.onNext(get());
                        }
                    });

            subscriber.add(Subscriptions.create(subscription::unsubscribe));
        }
    }
}
