package com.saber.test.whichapp.presenter;



import com.saber.test.whichapp.models.CountriesListData;
import com.saber.test.whichapp.networking.NetworkError;
import com.saber.test.whichapp.networking.Service;
import com.saber.test.whichapp.ui.HomeView;

import java.util.ArrayList;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ennur on 6/25/16.
 */
public class HomePresenter {
    private final Service service;
    private final HomeView view;
    private CompositeSubscription subscriptions;

    public HomePresenter(Service service, HomeView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getCountriesList() {
        view.showWait();

        Subscription subscription = service.getCityList(new Service.GetCountriesListCallback() {
            @Override
            public void onSuccess(ArrayList<CountriesListData> countriesListResponse) {
                view.removeWait();
                view.getCountriesListSuccess(countriesListResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }
    public void onStop() {
        subscriptions.unsubscribe();
    }
}
