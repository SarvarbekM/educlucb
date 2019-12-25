package com.example.educlub.ui.fragments;

import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.educlub.App;
import com.example.educlub.R;
import com.example.educlub.interfaces.IAPIMethods;
import com.example.educlub.interfaces.ILoginChangeFragment;
import com.example.educlub.network.RetrofitClientInstance;
import com.example.educlub.pojo.ExceptionInfo;
import com.example.educlub.pojo.UserInfo;
import com.example.educlub.ui.fragments.base.BaseFragment;
import com.github.pinball83.maskededittext.MaskedEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterFragment extends BaseFragment {
    private EditText loginET;
    private EditText passwordET;
    private EditText fioET;
    private MaskedEditText phoneET;
    private CheckBox saveCB;
    private Button registrBTN;

    private ILoginChangeFragment changeFragment;


    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginET = view.findViewById(R.id.loginET);
        passwordET = view.findViewById(R.id.passwordET);
        fioET = view.findViewById(R.id.fioET);
        phoneET = view.findViewById(R.id.phoneET);
        saveCB = view.findViewById(R.id.savemeCB);
        registrBTN = view.findViewById(R.id.registrBTN);
        registrBTN.setOnClickListener(view1 -> registerUser());
        changeFragment= (ILoginChangeFragment) getActivity();
    }

    private void registerUser() {
        String login = loginET.getText().toString();
        String password = passwordET.getText().toString();
        String fio = fioET.getText().toString();
        String phone = phoneET.getUnmaskedText();

        if (login.matches("")) {
            loginET.setError(getResources().getString(R.string.login_error));
            return;
        }
        if (password.matches("")) {
            passwordET.setError(getResources().getString(R.string.password_error));
            return;
        }
        if (fio.matches("")) {
            fioET.setError(getResources().getString(R.string.fio_empty_error));
            return;
        }
        if (phone.matches("")) {
            fioET.setError(getResources().getString(R.string.phone_empty_error));
            return;
        }

        IAPIMethods service = RetrofitClientInstance.getRetrofitInstance().create(IAPIMethods.class);
        Call<UserInfo> call = service.registration(login, password, fio, phone);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.isSuccessful()) {
                    App.getInstance().setUserInfo(response.body());
                    if(saveCB.isChecked()){
                        App.getInstance().saveUserInfo();
                    }
                    changeFragment.signInClick();
                } else {
                    Gson gson = new Gson();
                    try {
                        ExceptionInfo exceptionInfo = gson.fromJson(response.errorBody().string(), ExceptionInfo.class);
                        showSuperToast(exceptionInfo.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                        showSuperToast(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                showSuperToast("Failure: " + t.getMessage());
            }
        });

    }
}
