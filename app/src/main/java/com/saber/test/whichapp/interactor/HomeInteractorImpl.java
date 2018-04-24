package com.saber.test.whichapp.interactor;

import android.arch.persistence.room.Room;
import android.util.Log;

import com.saber.test.whichapp.WhichApplication;
import com.saber.test.whichapp.models.CountriesListData;
import com.saber.test.whichapp.networking.NetworkError;
import com.saber.test.whichapp.networking.NetworkService;
import com.saber.test.whichapp.persistance.CountriesDatabase;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ennur on 6/25/16.
 */
public class HomeInteractorImpl implements HomeInteractor {
    private static final String DATABASE_NAME = "countries_db";

    private final NetworkService networkService;

    private CountriesDatabase countryDatabase;


    public HomeInteractorImpl(NetworkService networkService) {
        this.networkService = networkService;
        countryDatabase = Room.databaseBuilder(WhichApplication.getInstance(), CountriesDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Override
    public void getCityList(final HomeInteractor.GetCountriesListCallback callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<CountriesListData> items = countryDatabase.daoAccess().fetchAllCountries();
                if(items == null || items.size()==0){
                    networkService.GetCountriesList()
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
                                public void onNext(final ArrayList<CountriesListData> countries) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            countryDatabase.daoAccess().insertMultipleCountries(countries);
                                        }
                                    }).start();


                                    callback.onSuccess(countries);

                                }
                            });
                }else{
                    callback.onSuccess(items);
                }
                Log.e("sds",items.size()+"");
            }
        }).start();


    }
}
