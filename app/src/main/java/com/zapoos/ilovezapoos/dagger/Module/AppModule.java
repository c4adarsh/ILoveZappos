package com.zapoos.ilovezapoos.dagger.Module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by adarsh on 2/3/2017.
 */
@Module
public class AppModule extends Application {

    Application mApplication;

    public AppModule(Application pApplication){
        mApplication = pApplication;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return mApplication;
    }
}
