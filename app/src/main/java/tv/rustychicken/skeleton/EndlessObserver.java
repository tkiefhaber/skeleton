package tv.rustychicken.skeleton;

import rx.Observer;

public abstract class EndlessObserver<T> implements Observer<T> {

    @Override
    public void onCompleted() {
        // ignored
    }

    @Override
    public void onError(Throwable e) {
        // ignored
    }
}