package com.example.educlub.ui.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.educlub.App;
import com.example.educlub.R;
import com.example.educlub.interfaces.IAPIMethods;
import com.example.educlub.network.RetrofitClientInstance;
import com.example.educlub.pojo.Company;
import com.example.educlub.pojo.Group;
import com.example.educlub.pojo.UserInfo;
import com.example.educlub.ui.adapters.EmptyAdapter;
import com.example.educlub.ui.adapters.Tab1AdapterRV;
import com.example.educlub.ui.adapters.Tab2AdapterRV;
import com.example.educlub.ui.fragments.base.BaseFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class

MainFragment extends BaseFragment implements NavigationView.OnNavigationItemSelectedListener {
    private boolean mSlideState;
    private DrawerLayout dl;

    private NavigationView nv;
    private Toolbar toolbar;
    //    private FloatingActionButton actionButton;
    private TextView countGroupTV;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swiperefresh;
    private ProgressBar prg;
    private MenuItem selectedMI;


    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dl = view.findViewById(R.id.drawer_layout);
//        this.message=getArguments().getString(KEY_FOR_MESSAGE,"");
        mSlideState = false;
        toolbar = view.findViewById(R.id.toolbar);
        //actionButton = view.findViewById(R.id.fab);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), dl, toolbar, R.string.Open, R.string.Close);
        dl.addDrawerListener(toggle);
        toggle.syncState();
        nv =  view.findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);
        recyclerView=view.findViewById(R.id.listRV);
        recyclerView.setAdapter(new EmptyAdapter());
        swiperefresh=view.findViewById(R.id.swiperefresh);
        swiperefresh.setOnRefreshListener(this::refreshContent);
        prg=view.findViewById(R.id.prg);
        getJoinedGroupList();
        getNotificationForNavigationMenu();
    }

    private void printGroups(ArrayList<Group> groups){
        Tab2AdapterRV adapter = new Tab2AdapterRV(groups);
        recyclerView.setAdapter(adapter);
    }

    private void printCompanies(ArrayList<Company> groups){
        Tab1AdapterRV adapter=new Tab1AdapterRV(groups,this);
        recyclerView.setAdapter(adapter);
    }

    private void showProgress(boolean value){
        if(value){
            prg.setVisibility(View.VISIBLE);
        }else {
            prg.setVisibility(View.GONE);
            swiperefresh.setRefreshing(false);
        }
    }

    private void getAllGroupList() {
        showProgress(true);
        IAPIMethods service = RetrofitClientInstance.getRetrofitInstance().create(IAPIMethods.class);
//        IAPIMethods service = RetrofitClientInstance.getRetrofitService();
        Call<ArrayList<Group>> call = service.getGroupList();
        call.enqueue(new Callback<ArrayList<Group>>() {
            @Override
            public void onResponse(Call<ArrayList<Group>> call, Response<ArrayList<Group>> response) {
                try{
                    if(response.isSuccessful()){
                        Log.d(getMyTAG(),"groups.size="+response.body().size());
                        printGroups(response.body());
                    }
                }catch(Exception ex){
                    showSuperToast("Exception 2:"+ex.getMessage());
                    Log.d(getMyTAG(),"Exception 2:"+ex.getLocalizedMessage());
                }
                showProgress(false);
            }
            @Override
            public void onFailure(Call<ArrayList<Group>> call, Throwable t) {
                showSuperToast("Failure 2:"+t.getMessage());
                Log.d(getMyTAG(),"Failure 2 :"+t.toString());
                showProgress(false);
            }
        });
    }

    private void getJoinedGroupList(){
        showProgress(true);
        IAPIMethods service = RetrofitClientInstance.getRetrofitInstance().create(IAPIMethods.class);
//        IAPIMethods service = RetrofitClientInstance.getRetrofitService();
        UserInfo userInfo= App.getInstance().getUserInfo();
        Call<ArrayList<Group>> call = service.getJoinedGroupList(userInfo.getUserId(),userInfo.getToken());
        call.enqueue(new Callback<ArrayList<Group>>() {
            @Override
            public void onResponse(Call<ArrayList<Group>> call, Response<ArrayList<Group>> response) {
                try{
                    if(response.isSuccessful()){
                        Log.d(getMyTAG(),"groups.size="+response.body().size());
                        printGroups(response.body());
                    }
                }catch(Exception ex){
                    showSuperToast("Exception 2:"+ex.getMessage());
                    Log.d(getMyTAG(),"Exception 2:"+ex.getLocalizedMessage());
                }
                showProgress(false);
            }
            @Override
            public void onFailure(Call<ArrayList<Group>> call, Throwable t) {
                showSuperToast("Failure 2:"+t.getMessage());
                Log.d(getMyTAG(),"Failure 2 :"+t.toString());
                showProgress(false);
            }
        });
    }

    private void getCompanyList(){
        showProgress(true);
        IAPIMethods service = RetrofitClientInstance.getRetrofitInstance().create(IAPIMethods.class);
//        IAPIMethods service = RetrofitClientInstance.getRetrofitService();
        Call<ArrayList<Company>> call = service.getCompanyList();
        call.enqueue(new Callback<ArrayList<Company>>() {
            @Override
            public void onResponse(Call<ArrayList<Company>> call, Response<ArrayList<Company>> response) {
                try{
                    if(response.isSuccessful()){
                        Log.d(getMyTAG(),"groups.size="+response.body().size());
                        printCompanies(response.body());
                    }
                }catch(Exception ex){
                    showSuperToast("Exception 2:"+ex.getMessage());
                    Log.d(getMyTAG(),"Exception 2:"+ex.getLocalizedMessage());
                }
                showProgress(false);
            }
            @Override
            public void onFailure(Call<ArrayList<Company>> call, Throwable t) {
                showSuperToast("Failure 2:"+t.getMessage());
                Log.d(getMyTAG(),"Failure 2 :"+t.toString());
                showProgress(false);
            }
        });
    }

    private void refreshContent(){
        swiperefresh.setRefreshing(true);
        if(selectedMI==null){
            getJoinedGroupList();
        }
        else{
            onNavigationItemSelected(selectedMI);
        }
    }

    private void getNotificationForNavigationMenu(){
        countGroupTV = (TextView) nv.getMenu().findItem(R.id.joinedGroupMI).getActionView();
        countGroupTV.setGravity(Gravity.CENTER_VERTICAL);
        countGroupTV.setTypeface(null, Typeface.BOLD);
        countGroupTV.setTextColor(getResources().getColor(R.color.colorAccent));
        countGroupTV.setText("99+");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.navigation_menu, menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        selectedMI=item;
        switch (selectedMI.getItemId()) {
            case R.id.allGroupMI:
                getAllGroupList();
                break;
            case R.id.joinedGroupMI:
                getJoinedGroupList();
                break;
            case R.id.courseMI:
                Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.companyMI:
                getCompanyList();
                break;
            case R.id.chatMI:
                Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.alertMI:
                Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.advertisingMI:
                Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.settingMI:
                Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }

        DrawerLayout drawer =  getView().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}