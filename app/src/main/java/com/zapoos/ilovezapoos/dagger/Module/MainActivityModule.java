package com.zapoos.ilovezapoos.dagger.Module;

import com.zapoos.ilovezapoos.presentation.contract.MainActivityContract;
import com.zapoos.ilovezapoos.util.scopes.MainPresenterScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by adarsh on 2/4/2017.
 */

@Module
public class MainActivityModule {
    private final MainActivityContract.View mView;


    public MainActivityModule(MainActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @MainPresenterScope
    MainActivityContract.View providesMainScreenContractView() {
        return mView;
    }
}
