package com.saber.test.whichapp.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.saber.test.whichapp.BaseActivity;
import com.saber.test.whichapp.R;
import com.saber.test.whichapp.adapter.HomeAdapter;
import com.saber.test.whichapp.interactor.HomeInteractor;
import com.saber.test.whichapp.interactor.HomeInteractorImpl;
import com.saber.test.whichapp.models.CountriesListData;
import com.saber.test.whichapp.presenter.HomePresenter;
import com.saber.test.whichapp.presenter.HomePresenterImpl;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements HomeView {

    private RecyclerView list;
    @Inject
    public HomeInteractor homeInteractor;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();
        init();

        HomePresenter presenter = new HomePresenterImpl(homeInteractor, this);
        presenter.getCountriesList();
    }

    public  void renderView(){
        setContentView(R.layout.activity_home);
        list = findViewById(R.id.listCountries);
        progressBar = findViewById(R.id.progressLoading);
    }

    public void init(){
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getCountriesListSuccess(ArrayList<CountriesListData> countries) {

        HomeAdapter adapter = new HomeAdapter(getApplicationContext(), countries,
                new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(CountriesListData Item) {
                        Toast.makeText(getApplicationContext(), Item.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        list.setAdapter(adapter);

    }
}
