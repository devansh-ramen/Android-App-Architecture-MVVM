package com.devanshramen.loginapplication.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.devanshramen.loginapplication.repository.model.LoginRequest;
import com.devanshramen.loginapplication.repository.model.LoginResponse;
import com.devanshramen.loginapplication.utils.EmailUtils;
import com.devanshramen.loginapplication.utils.RxUtils;

import static com.devanshramen.loginapplication.utils.NetworkUtils.getAPIService;

/**
 * Created by devanshramen on 12/11/2017.
 */

public class LoginViewModel extends ViewModel {

    // Create a LiveData
    private MutableLiveData<LoginResponse> loginResponse;

    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    public final ObservableField<String> errorEmail = new ObservableField<>();
    public final ObservableField<String> errorPassword = new ObservableField<>();

    public LiveData<LoginResponse> getLoginResponse() {
        if (loginResponse == null) {
            loginResponse = new MutableLiveData<LoginResponse>();
        }
        return loginResponse;
    }


    public void onBtnLoginClick() {

        if (validateInputs()) {

            // TO DO Get from UserRepository
            getAPIService().login(new LoginRequest(email.get(), password.get()))
                    .compose(RxUtils.applySchedulers())
                    .subscribe(
                            (LoginResponse response) -> {  // on Success
                                loginResponse.setValue(response);

                            },
                            (Throwable e) -> { // on Fail
                                e.printStackTrace();

                            },
                            () -> { // on Complete
                            });
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
