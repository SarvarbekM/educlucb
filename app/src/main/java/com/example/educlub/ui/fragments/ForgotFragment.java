package com.example.educlub.ui.fragments;

import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.educlub.R;
import com.example.educlub.ui.fragments.base.BaseFragment;

public class ForgotFragment extends BaseFragment {


    public static ForgotFragment newInstance() {
        Bundle args = new Bundle();
        ForgotFragment fragment = new ForgotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.forgot_fragment,container,false);
    }
}
