package com.example.educlub.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.educlub.R;
import com.example.educlub.config.Utility;
import com.example.educlub.ui.activities.base.BaseActivity;
import com.example.educlub.ui.fragments.MainFragment;

public class MainActivity extends BaseActivity {
    private final static int container=R.id.container_main_activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        replaceFragment(container, MainFragment.newInstance());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}