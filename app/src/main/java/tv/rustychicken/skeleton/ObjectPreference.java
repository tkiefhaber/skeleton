package tv.rustychicken.skeleton;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class ObjectPreference<T> extends AbstractPreference<T> {

    private final Gson mGson;
    private final TypeToken<T> mType;
    private T value;

    public ObjectPreference(SharedPreferences sharedPreferences, Gson gson, TypeToken<T> typeToken,
                            String key, T defaultValue) {
        super(sharedPreferences, key, defaultValue);
        this.mGson = gson;
        this.mType = typeToken;
    }

    public ObjectPreference(SharedPreferences sharedPreferences, Gson gson, Class<T> clazz,
                            String key) {
        this(sharedPreferences, gson, clazz, key, null);
    }

    public ObjectPreference(SharedPreferences sharedPreferences, Gson gson, Class<T> clazz,
                            String key, T defaultValue) {
        this(sharedPreferences, gson, TypeToken.get(clazz), key, defaultValue);
    }

    @Override
    public void delete() {
        // We have to update value before calling super.delete() so that any callback triggered
        // by an OnSharedPreferenceChangeListener won't end up seeing the unchanged value
        this.value = null;
        super.delete();
    }

    @Override
    public T get() {
        if (this.value == null) {
            String data = sharedPreferences.getString(key, null);
            if (data == null) {
                this.value = defaultValue;
            } else {
                this.value = mGson.fromJson(data, mType.getType());
            }
        }
        return this.value;
    }

    @Override
    public void set(T value) {
        this.value = value;
        String str = mGson.toJson(value, mType.getType());
        sharedPreferences.edit().putString(key, str).apply();
    }
}
