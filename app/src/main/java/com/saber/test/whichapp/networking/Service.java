package com.saber.test.whichapp.networking;

import com.saber.test.whichapp.models.CountriesListData;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ennur on 6/25/16.
 */
public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getCityList(final GetCountriesListCallback callback) {

        return networkService.GetCountriesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<CountriesListData>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(ArrayList<CountriesListData> countries) {
                        callback.onSuccess(countries);

                    }
                });
    }

    public interface GetCountriesListCallback {
        void onSuccess(ArrayList<CountriesListData> countries);

        void onError(NetworkError networkError);
    }
}
