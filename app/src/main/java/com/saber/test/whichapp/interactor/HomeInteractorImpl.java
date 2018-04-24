package com.saber.test.whichapp.interactor;

import com.saber.test.whichapp.models.CountriesListData;
import com.saber.test.whichapp.networking.NetworkError;
import com.saber.test.whichapp.networking.NetworkService;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ennur on 6/25/16.
 */
public class HomeInteractorImpl implements HomeInteractor{
    private final NetworkService networkService;

    public HomeInteractorImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public Subscription getCityList(final HomeInteractor.GetCountriesListCallback callback) {

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
}
