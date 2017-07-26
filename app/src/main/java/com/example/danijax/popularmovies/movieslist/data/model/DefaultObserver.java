package com.example.danijax.popularmovies.movieslist.data.model;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by danijax on 7/26/17.
 */

public class DefaultObserver<T> extends DisposableObserver<T> {
    @Override
    public void onNext(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
