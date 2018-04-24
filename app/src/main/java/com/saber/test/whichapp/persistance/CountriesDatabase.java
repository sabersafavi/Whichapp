package com.saber.test.whichapp.persistance;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.saber.test.whichapp.models.CountriesListData;

@Database(entities = {CountriesListData.class}, version = 2, exportSchema = false)
public abstract class CountriesDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess() ;
}