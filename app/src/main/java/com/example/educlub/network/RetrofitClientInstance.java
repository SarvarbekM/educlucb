package com.example.educlub.network;

import com.example.educlub.config.Utility;
import com.example.educlub.interfaces.IAPIMethods;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    private static IAPIMethods service;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("HH:mm:ss")
                    .create();
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(Utility.BASE_URL)
//                    .setConverter(new GsonConverter.create(gson))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static IAPIMethods getRetrofitService(){
        if(retrofit==null){
            getRetrofitInstance();
        }else {
            if(service==null){
                service=retrofit.create(IAPIMethods.class);
            }
        }
        return service;
    }

}
