package com.devanshramen.loginapplication.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;

import com.devanshramen.loginapplication.R;
import com.devanshramen.loginapplication.model.LoginRequest;
import com.devanshramen.loginapplication.model.LoginResponse;

import java.util.List;

/**
 * Created by devanshramen on 12/11/2017.
 */

public class LoginViewModel extends ViewModel {

    // Create a LiveData
    private MutableLiveData<LoginRequest> loginRequest;

    private MutableLiveData<LoginResponse> loginResponse;

    public MutableLiveData<LoginRequest> getLoginRequest() {
        if (loginRequest == null) {
            loginRequest = new MutableLiveData<LoginRequest>();
        }
        return loginRequest;
    }

    public MutableLiveData<LoginResponse> getLoginResponse() {
        if (loginResponse == null) {
            loginResponse = new MutableLiveData<LoginResponse>();
        }
        return loginResponse;
    }

    public boolean isUsernameAndPasswordValid(String username, String password) {
        //validate email and password
        if (username == null || username.isEmpty()) {
            return false;
        }

        if (password == null || password.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    public boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }



    public void login(String email, String password) {
        new UserLoginTask(email, password).execute();
    }


    /**
     * For example, we are using AsyncTask
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            LoginResponse response = new LoginResponse();
            response.setSuccess(success);

            getLoginResponse().postValue(response);
        }

        @Override
        protected void onCancelled() {}
    }

}
