package com.saber.test.whichapp.persistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Update;

import com.saber.test.whichapp.models.CountriesListData;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DaoAccess {
    @Insert
    void insertOnlySingleCountry (CountriesListData Country);

    @Insert
    void insertMultipleCountries (List<CountriesListData> countries);

    @Query("SELECT * FROM countriesListData WHERE iso = :countryIso")
    List<CountriesListData> fetchOneCountriesbyIsoId (int countryIso);

    @Query("SELECT * FROM countriesListData")
    List<CountriesListData> fetchAllCountries();

    @Update
    void updateCountry (CountriesListData country);

    @Delete
    void deleteCountry (CountriesListData country);
}

