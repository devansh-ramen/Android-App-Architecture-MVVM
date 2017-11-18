package com.devanshramen.loginapplication.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.os.AsyncTask;

import com.devanshramen.loginapplication.model.LoginResponse;
import com.devanshramen.loginapplication.utils.EmailUtils;

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

            new UserLoginTask().execute();
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
            errorPassword.set("Invalid Password");

            isValid = false;

        } else {
            errorPassword.set(null);
        }

        return isValid;
    }


    /**
     * For example, we are using AsyncTask
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

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

            LoginResponse response = new LoginResponse();   // hard coded response for the demo app

            loginResponse.setValue(response);
        }

        @Override
        protected void onCancelled() {}
    }

}
