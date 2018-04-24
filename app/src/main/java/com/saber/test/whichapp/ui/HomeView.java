package com.saber.test.whichapp.ui;


import com.saber.test.whichapp.models.CountriesListData;

import java.util.ArrayList;
import java.util.List;


public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCountriesListSuccess(List<CountriesListData> countries);

}
