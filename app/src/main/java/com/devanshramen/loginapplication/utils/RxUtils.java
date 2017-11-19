package com.devanshramen.loginapplication.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by devanshramen on 10/20/17.
 */

public class RxUtils {
    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return observable ->
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

    }
}