package com.saber.test.whichapp.networking;


import com.saber.test.whichapp.models.CountriesListData;

import java.util.ArrayList;

import retrofit2.http.GET;
import rx.Observable;


public interface NetworkService {

    @GET("v1/countries")
    Observable<ArrayList<CountriesListData>> GetCountriesList();

}
