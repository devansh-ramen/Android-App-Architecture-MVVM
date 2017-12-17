package com.devanshramen.loginapplication.db;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.devanshramen.loginapplication.MApplication;
import com.devanshramen.loginapplication.db.local.AppDatabase;
import com.devanshramen.loginapplication.db.local.UserDao;
import com.devanshramen.loginapplication.model.LoginRequest;
import com.devanshramen.loginapplication.model.LoginResponse;
import com.devanshramen.loginapplication.model.LoginResponse.User;
import com.devanshramen.loginapplication.utils.RxUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.devanshramen.loginapplication.utils.NetworkUtils.getAPIService;

/**
 * Created by devanshramen on 11/19/17.
 */

public class UserRepository {
    UserDao userDao;
    Executor executor;

    public UserRepository() {
        this.userDao = AppDatabase.getAppDatabase(MApplication.context).userDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public void clearUserCached() {
        executor.execute(() -> {
            userDao.deleteAll();
        });
    }

    public void loginUser(String email, String password) {

        getAPIService().login(new LoginRequest(email, password))
            .compose(RxUtils.applySchedulers())
            .subscribe(
                (LoginResponse response) -> {
                    executor.execute(() -> {
                        userDao.insert(response.getUser());
                    });
                },
                (Throwable e) -> {
                    e.printStackTrace();
                }
            );
    }

    public LiveData<User> getUser() {
        return userDao.getUser();
    }
}

