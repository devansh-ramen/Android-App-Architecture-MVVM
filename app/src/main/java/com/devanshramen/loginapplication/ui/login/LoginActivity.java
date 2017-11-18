package com.devanshramen.loginapplication.ui.login;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.devanshramen.loginapplication.R;
import com.devanshramen.loginapplication.databinding.ActivityLoginBinding;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    // UI references

    private LoginViewModel mViewModel;
    ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        // Get the ViewModel.
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setViewModel(mViewModel);

        // Create the observer which updates the UI.

        //Login Response
        mViewModel.getLoginResponse().observe(this, loginResponse -> {

            if (loginResponse.isSuccess()) {
                Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                finish();

            } else {
                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });


        binding.btnLogin.setOnClickListener(
            (View view) -> {
                mViewModel.onBtnLoginClick();

            });
    }

}

