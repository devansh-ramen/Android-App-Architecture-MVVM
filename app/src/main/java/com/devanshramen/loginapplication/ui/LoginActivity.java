package com.devanshramen.loginapplication.ui;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.devanshramen.loginapplication.R;
import com.devanshramen.loginapplication.databinding.ActivityLoginBinding;
import com.devanshramen.loginapplication.viewmodel.LoginViewModel;

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


        // The observer updates the UI when Login Operation is successful
        mViewModel.getUser().observe(this, userResponse -> {
            if (userResponse != null) {
                Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();

            } else {
                Log.d("LoginActivity", "value user is null");
                // Show ERROR
            }
        });


        binding.btnLogin.setOnClickListener(
            (View view) -> {
                mViewModel.onBtnLoginClick();

            });
    }


}

