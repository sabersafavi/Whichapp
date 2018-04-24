package com.saber.test.whichapp.interactor;

import com.saber.test.whichapp.models.CountriesListData;
import com.saber.test.whichapp.networking.NetworkError;

import java.util.ArrayList;

import rx.Subscription;

public interface HomeInteractor {



    interface GetCountriesListCallback {
        void onSuccess(ArrayList<CountriesListData> countries);

        void onError(NetworkError networkError);
    }

    Subscription getCityList(GetCountriesListCallback callback);
}
