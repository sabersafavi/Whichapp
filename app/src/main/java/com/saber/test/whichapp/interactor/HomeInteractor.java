package com.saber.test.whichapp.interactor;

import com.saber.test.whichapp.models.CountriesListData;
import com.saber.test.whichapp.networking.NetworkError;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

public interface HomeInteractor {


    interface GetCountriesListCallback {
        void onSuccess(List<CountriesListData> countries);

        void onError(NetworkError networkError);
    }

    void getCityList(GetCountriesListCallback callback);
}
