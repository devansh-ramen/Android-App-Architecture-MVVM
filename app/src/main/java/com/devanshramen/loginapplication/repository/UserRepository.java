package com.devanshramen.loginapplication.repository;

import android.arch.lifecycle.LiveData;

import com.devanshramen.loginapplication.repository.local.AppDatabase;
import com.devanshramen.loginapplication.repository.model.LoginRequest;
import com.devanshramen.loginapplication.repository.model.LoginResponse;
import com.devanshramen.loginapplication.repository.model.LoginResponse.User;
import com.devanshramen.loginapplication.utils.RxUtils;

import static com.devanshramen.loginapplication.utils.NetworkUtils.getAPIService;

/**
 * Created by devanshramen on 11/19/17.
 */

public class UserRepository {
    AppDatabase appDatabase;
    // todo

    public void registerUser(LoginRequest request) {
        // TO DO Get from UserRepository

    }

    public LiveData<User> getRegisteredUser(String email, String password) {
        LiveData<User> cacheUser = appDatabase.userDao().getUser();
        if (cacheUser != null)
           return cacheUser;

        return getAPIService().login(new LoginRequest(email, password));

    }
}

