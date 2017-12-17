package com.devanshramen.loginapplication.utils;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by devanshramen on 10/20/17.
 */

public class RxUtils {
    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return observable ->
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

    }
}