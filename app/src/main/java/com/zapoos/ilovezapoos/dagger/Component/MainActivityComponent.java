package com.zapoos.ilovezapoos.dagger.Component;

import com.zapoos.ilovezapoos.dagger.Module.MainActivityModule;
import com.zapoos.ilovezapoos.presentation.ui.Activity.MainActivity;
import com.zapoos.ilovezapoos.util.scopes.MainPresenterScope;

import dagger.Component;

/**
 * Created by adarsh on 2/4/2017.
 */

@MainPresenterScope
@Component(dependencies = NetworkComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}