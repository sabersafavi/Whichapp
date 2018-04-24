package com.saber.test.whichapp.deps;


import com.saber.test.whichapp.networking.NetworkModule;
import com.saber.test.whichapp.ui.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(HomeActivity homeActivity);
}
