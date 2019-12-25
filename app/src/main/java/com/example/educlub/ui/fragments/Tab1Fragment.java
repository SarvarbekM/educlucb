package com.example.educlub.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.educlub.R;
import com.example.educlub.interfaces.IAPIMethods;
import com.example.educlub.network.RetrofitClientInstance;
import com.example.educlub.pojo.Company;
import com.example.educlub.ui.adapters.EmptyAdapter;
import com.example.educlub.ui.adapters.Tab1AdapterRV;
import com.example.educlub.ui.fragments.base.BaseFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab1Fragment extends BaseFragment {
    private RecyclerView tab1RV;
    private ArrayList<Company> companies;
    private SwipeRefreshLayout swipeL;
    private ContentLoadingProgressBar contentLPB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab1_index_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tab1RV=view.findViewById(R.id.tab1RV);
        tab1RV.setAdapter(new EmptyAdapter());
        swipeL=view.findViewById(R.id.swipeL);
        swipeL.setOnRefreshListener(() ->{swipeL.setRefreshing(true); getCompanyList();});
        contentLPB=view.findViewById(R.id.contentLPB);
        contentLPB.show();
        getCompanyList();
    }

    private void showProgress(boolean value){
        if(value){
            contentLPB.show();
        }else {
            contentLPB.hide();
            swipeL.setRefreshing(false);
        }
    }

    private void printCompanies(){
//        for(Company item : companies){
//            item.setLatitude(41);
//            item.setLongitude(69);
//        }
        Tab1AdapterRV adapter = new Tab1AdapterRV(companies,this);
        tab1RV.setAdapter(adapter);
    }

    private void getCompanyList() {
        showProgress(true);
        IAPIMethods service = RetrofitClientInstance.getRetrofitInstance().create(IAPIMethods.class);
        Call<ArrayList<Company>> call = service.getCompanyList();
        call.enqueue(new Callback<ArrayList<Company>>() {
            @Override
            public void onResponse(Call<ArrayList<Company>> call, Response<ArrayList<Company>> response) {
                Log.d(getMyTAG(),"Start Response");
                try{
                    if(response.isSuccessful()){
                        companies = response.body();
                        Log.d(getMyTAG(),"companies.zie="+companies.size());
                        printCompanies();
                    }
                }catch(Exception ex){
                    Toast.makeText(getContext(), "Exception: "+ex.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d(getMyTAG(),"Exception: "+ex.getLocalizedMessage());
                    Log.d(getMyTAG(),"Exception: "+ex.getMessage());
                }
                showProgress(false);
            }

            @Override
            public void onFailure(Call<ArrayList<Company>> call, Throwable t) {
                Log.d(getMyTAG(),"Failure: "+t.getMessage());
                Log.d(getMyTAG(),"Failure: "+t.getLocalizedMessage());
                Toast.makeText(getContext(), "Failure: "+t.getMessage(), Toast.LENGTH_LONG).show();
                showProgress(false);
            }
        });
    }

}
