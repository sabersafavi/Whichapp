package com.saber.test.whichapp.ui;


import com.saber.test.whichapp.models.CountriesListData;

import java.util.ArrayList;


public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCountriesListSuccess(ArrayList<CountriesListData> countries);

}
