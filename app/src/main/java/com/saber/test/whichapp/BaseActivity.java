package com.saber.test.whichapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.saber.test.whichapp.deps.DaggerDeps;
import com.saber.test.whichapp.deps.Deps;
import com.saber.test.whichapp.networking.NetworkModule;

import java.io.File;

/**
 * Created by ennur on 6/28/16.
 */
public class BaseActivity  extends AppCompatActivity{
    Deps deps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public Deps getDeps() {
        return deps;
    }
}
