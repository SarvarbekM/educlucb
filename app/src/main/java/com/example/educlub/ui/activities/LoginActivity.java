package com.example.educlub.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.educlub.R;
import com.example.educlub.config.Utility;
import com.example.educlub.interfaces.ILoginChangeFragment;
import com.example.educlub.pojo.UserInfo;
import com.example.educlub.ui.activities.base.BaseActivity;
import com.example.educlub.ui.fragments.ForgotFragment;
import com.example.educlub.ui.fragments.LoginFragment;
import com.example.educlub.ui.fragments.RegisterFragment;

public class LoginActivity extends BaseActivity implements ILoginChangeFragment {
    private final static int container=R.id.container_login_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        replaceFragment(container,LoginFragment.newInstance());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void signInClick() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //String message = "Login: " + login + " Password: " + password;
        //intent.putExtra(Utility.KEY_LOGIN_MAIN_CHANGE_ACTIVITY, message);
        startActivity(intent);
    }

    @Override
    public void forgotClick() {
        addFragment(container,ForgotFragment.newInstance());
    }

    @Override
    public void registerClick() {
        addFragment(container,RegisterFragment.newInstance());
    }
}