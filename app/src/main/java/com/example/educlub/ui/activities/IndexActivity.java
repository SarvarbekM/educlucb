package com.example.educlub.ui.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.educlub.App;
import com.example.educlub.R;
import com.example.educlub.interfaces.IIndexChangeFragment;
import com.example.educlub.ui.activities.base.BaseActivity;
import com.example.educlub.ui.fragments.IndexFragment;

public class IndexActivity extends BaseActivity implements IIndexChangeFragment{
    private final static int container = R.id.container_index_activity;

    private TextView testTV;

//    @Override
//    protected void onStart() {
//        super.onStart();
//        App.getInstance().loadSettings();
//    }

//    @Override
//    protected void attachBaseContext(Context base) {
////        super.attachBaseContext(LocaleManager.setLocale(base));
////        super.attachBaseContext(LocaleHelper.setLocale(base));
//        super.attachBaseContext(base);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        App.getInstance().loadSettings();
//        LocaleManager.setNewLocale(this,Utility.LANGUAGE_UZ);
//        Log.d(getActivityTAG(),App.getInstance().getLanguage());
        testTV=findViewById(R.id.testTV);
        setContentView(R.layout.index_activity);
        replaceFragment(container, IndexFragment.newInstance());
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//    }

    @Override
    protected void onStop() {
        super.onStop();
        App.getInstance().saveSettings();
    }
}