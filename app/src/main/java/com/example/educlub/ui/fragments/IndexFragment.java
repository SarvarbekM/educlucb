package com.example.educlub.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.cleveroad.sy.cyclemenuwidget.CycleMenuWidget;
import com.cleveroad.sy.cyclemenuwidget.OnMenuItemClickListener;
import com.example.educlub.App;
import com.example.educlub.R;
import com.example.educlub.config.Utility;
import com.example.educlub.ui.activities.LoginActivity;
import com.example.educlub.ui.adapters.TabAdapter;
import com.example.educlub.ui.fragments.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

public class IndexFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter adapter;
    private TextView signInTV;


    public static IndexFragment newInstance() {
        Bundle args = new Bundle();
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.index_fragment_index_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        adapter = new TabAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), getResources().getString(R.string.tab1_title));
        adapter.addFragment(new Tab2Fragment(), getResources().getString(R.string.tab2_title));
        adapter.addFragment(new Tab3Fragment(), getResources().getString(R.string.tab3_title));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        signInTV=view.findViewById(R.id.signinTV);
        signInTV.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
        final CycleMenuWidget cycleMenuWidget = view.findViewById(R.id.itemCycleMenuWidget);
        createMyCycleMenuWidget(cycleMenuWidget);
    }

    private void createMyCycleMenuWidget(CycleMenuWidget cycleMenuWidget) {
        cycleMenuWidget.setMenuRes(R.menu.popup_menu_index_activity);
        cycleMenuWidget.setScrollType(CycleMenuWidget.SCROLL.BASIC);
        String lan= App.getInstance().getLanguage();
        switch (lan){
            case Utility.LANGUAGE_EN:
                cycleMenuWidget.setCornerImageResource(R.mipmap.en_icon);
                break;
            case Utility.LANGUAGE_RU:
                cycleMenuWidget.setCornerImageResource(R.mipmap.ru_icon);
                break;
            case Utility.LANGUAGE_UZ:
                cycleMenuWidget.setCornerImageResource(R.mipmap.uz_icon);
                break;
        }
        cycleMenuWidget.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(View view, int itemPosition) {
                switch (view.getId()) {
                    case R.id.uz:
                        App.getInstance().changeLanguages(Utility.LANGUAGE_UZ);
                        cycleMenuWidget.setCornerImageResource(R.mipmap.uz_icon);
                        cycleMenuWidget.close(true);
                        break;
                    case R.id.ru:
                        App.getInstance().changeLanguages(Utility.LANGUAGE_RU);
                        cycleMenuWidget.setCornerImageResource(R.mipmap.ru_icon);
                        cycleMenuWidget.close(true);
                        break;
                    case R.id.en:
                        App.getInstance().changeLanguages(Utility.LANGUAGE_EN);
                        cycleMenuWidget.setCornerImageResource(R.mipmap.en_icon);
                        cycleMenuWidget.close(true);
                        break;
                    default:
                        Toast.makeText(getContext(), "DEFAULT", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onMenuItemLongClick(View view, int itemPosition) {

            }
        });
    }
}