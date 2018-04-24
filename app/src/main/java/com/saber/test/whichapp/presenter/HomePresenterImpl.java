package com.saber.test.whichapp.presenter;


import com.saber.test.whichapp.interactor.HomeInteractor;
import com.saber.test.whichapp.interactor.HomeInteractorImpl;
import com.saber.test.whichapp.models.CountriesListData;
import com.saber.test.whichapp.networking.NetworkError;
import com.saber.test.whichapp.ui.HomeView;

import java.util.ArrayList;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class HomePresenterImpl implements HomePresenter {
    private final HomeInteractor homeInteractor;
    private final HomeView view;
    private CompositeSubscription subscriptions;

    public HomePresenterImpl(HomeInteractor homeInteractor, HomeView view) {
        this.homeInteractor = homeInteractor;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    @Override
    public void getCountriesList() {
        view.showWait();

        Subscription subscription = homeInteractor.getCityList(new HomeInteractor.GetCountriesListCallback() {
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
