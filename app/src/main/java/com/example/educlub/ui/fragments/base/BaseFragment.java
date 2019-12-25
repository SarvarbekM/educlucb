package com.example.educlub.ui.fragments.base;

//import android.support.v4.app.Fragment;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

public class BaseFragment extends Fragment {
    private String TAG;

    public BaseFragment() {
        this.TAG = getClass().getName();
    }

    public String getMyTAG() {
        return TAG;
    }

    public void showSuperToast(String message) {
        Context context = getContext();
        if (context != null) {
            SuperActivityToast.create(getContext(), new Style(), Style.TYPE_BUTTON)
                    //setColor(getResources().getColor(R.color.cm_ripple_color)).
                    .setText(message)
                    .setDuration(Style.DURATION_LONG)
                    .setFrame(Style.FRAME_LOLLIPOP)
                    .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_PURPLE))
                    .setAnimations(Style.ANIMATIONS_POP)
                    .show();
        }
    }

    public void showSuperToast(Context context, String message) {
        SuperActivityToast.create(context, new Style(), Style.TYPE_BUTTON)
                //setColor(getResources().getColor(R.color.cm_ripple_color)).
                .setText(message)
                .setDuration(Style.DURATION_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_PURPLE))
                .setAnimations(Style.ANIMATIONS_POP)
                .show();
    }
}
