package com.devanshramen.loginapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.devanshramen.loginapplication.db.UserRepository;
import com.devanshramen.loginapplication.model.LoginResponse.User;

/**
 * Created by devanshramen on 16/12/2017.
 */

public class MainViewModel extends ViewModel {

    private UserRepository userRepository;

    public MainViewModel() {
        userRepository = new UserRepository();
       // userRepository.clearUserCached();
    }

    public LiveData<User> getUser() {
        return userRepository.getUser();
    }


    public void clearUserData() {
        userRepository.clearUserCached();
    }
}
