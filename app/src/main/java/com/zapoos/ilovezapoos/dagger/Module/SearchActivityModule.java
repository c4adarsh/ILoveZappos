package com.zapoos.ilovezapoos.dagger.Module;

import com.zapoos.ilovezapoos.presentation.contract.MainActivityContract;
import com.zapoos.ilovezapoos.presentation.contract.SearchActivityContract;
import com.zapoos.ilovezapoos.presentation.ui.Activity.SearchActivity;
import com.zapoos.ilovezapoos.util.scopes.MainPresenterScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by adarsh on 2/4/2017.
 */

@Module
public class SearchActivityModule {
    private final SearchActivityContract.View mView;


    public SearchActivityModule(SearchActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @MainPresenterScope
    SearchActivityContract.View providesMainScreenContractView() {
        return mView;
    }
}
