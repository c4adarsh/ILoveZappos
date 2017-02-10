package com.zapoos.ilovezapoos.dagger.Module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adarsh on 2/3/2017.
 */
@Module
public class NetworkModule {

    String mUrl;

    public NetworkModule(String pUrl){
        mUrl = pUrl;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application pApplication){
        SharedPreferences sharedPreferences = null;
        return PreferenceManager.getDefaultSharedPreferences(pApplication);
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application pApplication){
        int cacheSize = 10 * 1024 * 1024; // allot 10MB for cache
        Cache cache = new Cache(pApplication.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache pCache){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(pCache)
                .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    Gson ProvideGson(){
        Gson gson = new Gson();
        return gson;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(mUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

}
