package com.devanshramen.loginapplication.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devanshramen.loginapplication.R;
import com.devanshramen.loginapplication.databinding.ActivityLoginBinding;
import com.devanshramen.loginapplication.model.LoginRequest;
import com.devanshramen.loginapplication.model.LoginResponse;

import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    // UI references

    private LoginViewModel mViewModel;

    LoginRequest loginRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLoginRequest(loginRequest);

        // Get the ViewModel.
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        // Create the observer which updates the UI.
        final Observer<LoginRequest> loginRequestObserver = new Observer<LoginRequest>() {
            @Override
            public void onChanged(@Nullable final LoginRequest loginRequest) {
                // Update the UI, in this case, a TextView.
                binding.edtUsername.setText(loginRequest.getUsername());
                binding.edtPassword.setText(loginRequest.getPassword());
            }
        };

        mViewModel.getLoginRequest().observe(this, loginRequestObserver);

        // Create the observer which updates the UI.
        final Observer<LoginResponse> loginResponseObserver = new Observer<LoginResponse>() {
            @Override
            public void onChanged(@Nullable final LoginResponse loginResponse) {
                // Update the UI, in this case, a TextView.
                if (loginResponse.isSuccess()) {
                    Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        };

        mViewModel.getLoginResponse().observe(this, loginResponseObserver);


        binding.btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mViewModel.isUsernameAndPasswordValid(binding.edtUsername.toString(), binding.edtPassword.toString())) {
                    mViewModel.login(binding.edtUsername.toString(), binding.edtPassword.toString());

                } else {
                    Toast.makeText(LoginActivity.this, "Enter a valid Username and Password", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}

