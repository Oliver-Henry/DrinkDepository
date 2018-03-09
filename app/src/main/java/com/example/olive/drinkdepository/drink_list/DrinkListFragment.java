package com.example.olive.drinkdepository.drink_list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.olive.drinkdepository.R;
import com.example.olive.drinkdepository.data.network.AppDataManager;
import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.ui.base.BaseFragment;
import com.example.olive.drinkdepository.ui.utils.rx.AppSchedulerProvider;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrinkListFragment extends BaseFragment implements IDrinkListMvpView {

    @BindView(R.id.rVDrinkList)RecyclerView recyclerView;
    @BindView(R.id.swiperefresh)SwipeRefreshLayout refreshLayout;
    private Unbinder unbinder;
    private DrinkListPresenterImpl<DrinkListFragment> drinkListFragmentDrinkListPresenter;


    public DrinkListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setRetainInstance(true);
        callService();
        drinkListFragmentDrinkListPresenter = new DrinkListPresenterImpl<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        drinkListFragmentDrinkListPresenter.onAttach(this);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callService();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drink_list, container, false);
    }

    @Override
    public void onDestroy() {
        if(unbinder != null){
            unbinder.unbind();
        }
        unbinder = null;
        super.onDestroy();
    }



    public void callService(){
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isConnectedToInternet) throws Exception {
                        if(isConnectedToInternet){
                             String page = getArguments().getString("page");
                            if(page == "I"){String i = getArguments().getString("name");
                                drinkListFragmentDrinkListPresenter.loadDrinksByIngredientList(i);}
                             else {drinkListFragmentDrinkListPresenter.loadDrinksList(page);}
                        }
                        else{
                            Toast.makeText(getActivity(), "No Network Connection", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onFetchDataProgress() {

    }

    @Override
    public void onFetchDataSuccess(DrinksModel drinksModel) {
            recyclerView.setAdapter(new DrinkListAdapter(getActivity().getApplicationContext(), drinksModel.getDrinks(), R.layout.row_layout));
            refreshLayout.setRefreshing(false);

        }

    @Override
    public void onFetchDataError(String error) {
        showMessage(error);
        refreshLayout.setRefreshing(false);
    }
}
