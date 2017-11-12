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

import com.devanshramen.loginapplication.R;
import com.devanshramen.loginapplication.databinding.ActivityLoginBinding;
import com.devanshramen.loginapplication.model.LoginRequest;

import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    // UI references

    private LoginViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        // Get the ViewModel.
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        // Create the observer which updates the UI.
        final Observer<LoginRequest> nameObserver = new Observer<LoginRequest>() {
            @Override
            public void onChanged(@Nullable final LoginRequest loginRequest) {
                // Update the UI, in this case, a TextView.
                binding.edtUsername.setText(loginRequest.getUsername());
                binding.edtPassword.setText(loginRequest.getPassword());
            }
        };

        mViewModel.getLoginRequest().observe(this, nameObserver);
        LoginRequest request = new LoginRequest("Test", "User");
        binding.setLoginRequest(request);


        binding.btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.validateEmail();
            }
        });

    }
}

