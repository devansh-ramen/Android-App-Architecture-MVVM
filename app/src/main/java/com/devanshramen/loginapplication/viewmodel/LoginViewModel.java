package com.devanshramen.loginapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.devanshramen.loginapplication.db.UserRepository;
import com.devanshramen.loginapplication.model.LoginResponse.User;
import com.devanshramen.loginapplication.utils.EmailUtils;

/**
 * Created by devanshramen on 12/11/2017.
 */

public class LoginViewModel extends ViewModel {

    // Create a LiveData
    private LiveData<User> userResponse;

    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    public final ObservableField<String> errorEmail = new ObservableField<>();
    public final ObservableField<String> errorPassword = new ObservableField<>();

    UserRepository userRepository;

    public LoginViewModel() {
        userRepository = new UserRepository();
        email.set("devansh@gmail.com");
        password.set("12345");

        userResponse = userRepository.getUser();
    }

    public LiveData<User> getUser() {
        return userResponse;
    }

    public void onBtnLoginClick() {
        if (validateInputs()) {
            userRepository.loginUser(email.get(), password.get());
        }
    }


    public boolean validateInputs() {
        boolean isValid = true;

        if (email.get() == null || !EmailUtils.isEmailValid(email.get())) {

            errorEmail.set("Invalid Email");

            isValid = false;

        } else {
            errorEmail.set(null);
        }

        if (password.get() == null || password.get().length() < 4) {
            errorPassword.set("Password too short");

            isValid = false;

        } else {
            errorPassword.set(null);
        }

        return isValid;
    }

}
