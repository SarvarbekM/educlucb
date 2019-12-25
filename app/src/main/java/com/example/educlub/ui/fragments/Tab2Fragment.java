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
import com.example.educlub.pojo.Group;
import com.example.educlub.ui.adapters.EmptyAdapter;
import com.example.educlub.ui.adapters.Tab2AdapterRV;
import com.example.educlub.ui.fragments.base.BaseFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab2Fragment extends BaseFragment {
    private RecyclerView tab2RV;
    private SwipeRefreshLayout swipeL;
    private ContentLoadingProgressBar contentLPB;
    private ArrayList<Group> groups;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab1_index_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tab2RV=view.findViewById(R.id.tab1RV);
        tab2RV.setAdapter(new EmptyAdapter());
        swipeL=view.findViewById(R.id.swipeL);
        swipeL.setOnRefreshListener(() ->{swipeL.setRefreshing(true); getGroupList();});
        contentLPB=view.findViewById(R.id.contentLPB);
        contentLPB.show();
        getGroupList();
//        Tab2AdapterRV adapterRV=new Tab2AdapterRV(getDatas());
//        tab2RV.setAdapter(adapterRV);
    }

    private void printGroups(){
        Tab2AdapterRV adapter = new Tab2AdapterRV(groups);
        tab2RV.setAdapter(adapter);
    }

    private void showProgress(boolean value){
        if(value){
            contentLPB.show();
        }else {
            contentLPB.hide();
            swipeL.setRefreshing(false);
        }
    }


    private void getGroupList() {
        showProgress(true);
//        IAPIMethods service = RetrofitClientInstance.getRetrofitInstance().create(IAPIMethods.class);
        IAPIMethods service = RetrofitClientInstance.getRetrofitService();
        Call<ArrayList<Group>> call = service.getGroupList();
        call.enqueue(new Callback<ArrayList<Group>>() {
            @Override
            public void onResponse(Call<ArrayList<Group>> call, Response<ArrayList<Group>> response) {
                try{
                    if(response.isSuccessful()){
                        Log.d(getMyTAG(),"Call is succesfull");
                        groups = response.body();
                        Log.d(getMyTAG(),"groups.size="+groups.size());
                        printGroups();
                    }
                }catch(Exception ex){
                    Toast.makeText(getContext(), "Exception 2:"+ex.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d(getMyTAG(),"Exception 2:"+ex.getLocalizedMessage());
                }
                showProgress(false);
            }
            @Override
            public void onFailure(Call<ArrayList<Group>> call, Throwable t) {
                Toast.makeText(getContext(), "Failure 2:"+t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d(getMyTAG(),"Failure 2 :"+t.toString());
                showProgress(false);
            }
        });
    }

//    private ArrayList<Group> getDatas(){
//        ArrayList<Group> groups=new ArrayList<>();
//        Group group=new Group(1,
//                "Tarix",
//                1,
//                "Boshlang'ich",
//                "Toshkent shaxar, Shayhontohur tumani",
//                "Category",
//                "Subcategory",
//                "Tag1,Tag2,Tag3",
//                "Note that most of the property setters have different variations for literal values, dimension resources, and attribute IDs. You can also check whether or not all weekdays are selected",
//                new Date(),
//                new Date(),
//                "1,3,5"
//                );
//
//        groups.add(group);
//        groups.add(group);
//        groups.add(group);
//        groups.add(group);
//        groups.add(group);
//        groups.add(group);
//        groups.add(group);
//        groups.add(group);
//        groups.add(group);
//        groups.add(group);
//        groups.add(group);
//        groups.add(group);
//        groups.add(group);
//        groups.add(group);
//        groups.add(group);
//        groups.add(group);
//
//
//
//        return groups;
//    }
}
