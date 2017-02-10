package com.zapoos.ilovezapoos;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.zapoos.ilovezapoos.dagger.Component.DaggerNetworkComponent;
import com.zapoos.ilovezapoos.dagger.Component.NetworkComponent;
import com.zapoos.ilovezapoos.dagger.Module.AppModule;
import com.zapoos.ilovezapoos.dagger.Module.NetworkModule;

/**
 * Created by adarsh on 2/3/2017.
 */

public class ILoveZapoosApplication extends Application{

    public static final String TAG  = "ILoveZapoosApplication";

    private NetworkComponent mNetworkComponent;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNetworkComponent = DaggerNetworkComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("http://api.zappos.com/"))
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return mNetworkComponent;
    }
}
