package com.example.educlub.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends BaseFragment {
    //<editor-fold desc="Controls">
    private TextView forgotTV;
    private TextView registerTV;
    private Button signinBTN;
    private EditText loginET;
    private EditText passwordET;
    private CheckBox savemeCB;
    //</editor-fold>

    private ILoginChangeFragment changeFragments;
    private UserInfo userInfo;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        forgotTV = view.findViewById(R.id.forgotTV);
        registerTV = view.findViewById(R.id.registerTV);
        signinBTN = view.findViewById(R.id.signInBTN);
        loginET = view.findViewById(R.id.loginET);
        passwordET = view.findViewById(R.id.passwordET);
        savemeCB = view.findViewById(R.id.savemeCB);
        changeFragments = (ILoginChangeFragment) getActivity();
        signinBTN.setOnClickListener(v -> signIn());
        forgotTV.setOnClickListener(v -> changeFragments.forgotClick());
        registerTV.setOnClickListener(v -> changeFragments.registerClick());
    }

    private void signIn() {
        String login = loginET.getText().toString();
        String password = passwordET.getText().toString();
        if (login.matches("")) {
            loginET.setError(getResources().getString(R.string.login_error));
            return;
        }
        if (password.matches("")) {
            passwordET.setError(getResources().getString(R.string.password_error));
            return;
        }

        IAPIMethods service = RetrofitClientInstance.getRetrofitInstance().create(IAPIMethods.class);
        Call<UserInfo> call = service.signIn(login, password);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                try {
                    if (response.isSuccessful()) {
                        userInfo = response.body();
                        App.getInstance().setUserInfo(userInfo);
                        if (savemeCB.isChecked()) {
                            App.getInstance().saveUserInfo();
                        }
                        changeFragments.signInClick();
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
                } catch (Exception ex) {
                    showSuperToast(ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                showSuperToast(t.getMessage());
            }
        });
    }
}
