package com.zapoos.ilovezapoos.dagger.Component;

import android.content.SharedPreferences;

import com.zapoos.ilovezapoos.dagger.Module.AppModule;
import com.zapoos.ilovezapoos.dagger.Module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by adarsh on 2/3/2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface NetworkComponent {
    Retrofit getRetrofit();
    SharedPreferences getSharedPreferences();
    OkHttpClient getOkHttpClient();
}
