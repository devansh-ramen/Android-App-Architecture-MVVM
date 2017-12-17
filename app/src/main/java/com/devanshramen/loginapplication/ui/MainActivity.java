package com.devanshramen.loginapplication.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.devanshramen.loginapplication.R;
import com.devanshramen.loginapplication.databinding.ActivityMainBinding;
import com.devanshramen.loginapplication.db.UserRepository;
import com.devanshramen.loginapplication.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    UserRepository userRepository;

    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        // The observer updates the UI when Login Response is successful
        mViewModel.getUser().observe(this, userResponse -> {

            if (userResponse != null) {
                binding.txtWelcome.setText("Welcome " + userResponse.getFirstName() + " " + userResponse.getLastName()
                        + "\n\n" + "You are more than a " + userResponse.getJobTitle());

            } else {
                logoutUser();
            }
        });
    }


    public void btnLogout(View view) {
        logoutUser();
    }

    private void logoutUser() {
        mViewModel.clearUserData();
        finish();
    }
}
