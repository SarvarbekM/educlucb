package com.example.educlub;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;

import com.example.educlub.config.Utility;
import com.example.educlub.pojo.UserInfo;
import com.google.gson.Gson;

import java.util.Locale;

public class App extends Application {
    private static final String TAG="App";


    private static App app;
    private UserInfo userInfo;
    private String language;

    public App() {
        app = this;
    }

    public static App getInstance() {
        return app;
    }

    @Override
    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(LocaleHelper.setLocale(base));
        //super.attachBaseContext(RuntimeLocaleChanger.INSTANCE.wrapContext(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //RuntimeLocaleChanger.INSTANCE.overrideLocale(this);
//        LocaleManager.setLocale(this);
    }

    public void saveUserInfo(){
        SharedPreferences.Editor editor=getSharedPreferences(Utility.SHARED_PREF_NAME_FOR_USER_INFO,MODE_PRIVATE).edit();
        Gson gson=new Gson();
        String jsonString= gson.toJson(userInfo);
        editor.putString(jsonString,Utility.KEY_JSON_USERINFO);
        editor.apply();
    }

    public void loadUserInfo(){
        SharedPreferences prefs = getSharedPreferences(Utility.SHARED_PREF_NAME_FOR_USER_INFO, MODE_PRIVATE);
        String jsonString=prefs.getString(Utility.KEY_JSON_USERINFO, "{" +
                "    \"userId\": 0," +
                "    \"userType\": 0," +
                "    \"userTypeName\": \"None\"," +
                "    \"userFIO\": \"None\"," +
                "    \"userPhone\": \"0\"," +
                "    \"access_token\": \"txSCyOi-0-lTZB7QmlW88OoghC3Q8gK5\"," +
                "    \"expiret_at\": 1576516599," +
                "    \"language_id\": \"0\"" +
                "}");
        Gson gson=new Gson();
        userInfo=gson.fromJson(jsonString,UserInfo.class);
    }

    public void saveSettings(){
        SharedPreferences.Editor editor=getSharedPreferences(Utility.SHARED_PREF_NAME_FOR_USER_INFO,MODE_PRIVATE).edit();
        editor.putString(Utility.KEY_FOR_LOCALE,language);
        editor.apply();
    }

    public void loadSettings(){
        SharedPreferences preferences=getSharedPreferences(Utility.SHARED_PREF_NAME_FOR_USER_INFO, MODE_PRIVATE);
//        changeLanguages(preferences.getString(Utility.KEY_FOR_LOCALE,Utility.LANGUAGE_UZ));
    }

    public void changeLanguages(String newlan){
        Log.d(TAG,"newLan="+newlan);
        language=newlan;
        Locale locale=new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        }else {
            configuration.locale=locale;
        }
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        Log.d(TAG, getBaseContext().getResources().getConfiguration().toString());
    }

    //<editor-fold desc="Getters and Setters">

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    //</editor-fold>
}
