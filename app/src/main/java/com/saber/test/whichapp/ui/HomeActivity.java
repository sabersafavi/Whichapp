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
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomeView {

    @Inject
    public HomeInteractor homeInteractor;

    @BindView(R.id.listCountries)
    RecyclerView list;
    @BindView(R.id.progressLoading)
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
        ButterKnife.bind(this);
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
    public void getCountriesListSuccess(List<CountriesListData> countries) {

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
