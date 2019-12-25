package com.example.educlub.ui.activities.base;

import android.content.Context;
import android.os.Build;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;



public class BaseActivity extends AppCompatActivity {
    private String TAG;

    public BaseActivity(){TAG=getClass().getName();}

    public String getActivityTAG() {
        return TAG;
    }


    protected <T extends Fragment> void replaceFragment(int id_container, T fragment) {
        getSupportFragmentManager().beginTransaction().
                replace(id_container, fragment, fragment.getClass().getSimpleName()).commit();
    }

    protected <T extends Fragment> void addFragment(int id_container,T fragment) {
        getSupportFragmentManager().beginTransaction().
                addToBackStack(null).
                replace(id_container, fragment, fragment.getClass().getSimpleName()).commit();
    }


    @Override
    protected void onResume() {
        super.onResume();
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.getWindow().getDecorView().setSystemUiVisibility(flags);
            final View decorView = this.getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(visibility -> {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(flags);
                }
            });
        }
    }


    @Override
    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(LocaleManager.setLocale(base));
//        super.attachBaseContext(LocaleHelper.setLocale(base));
        super.attachBaseContext(base);
    }
}
