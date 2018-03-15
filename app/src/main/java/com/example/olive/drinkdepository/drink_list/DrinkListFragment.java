package com.example.olive.drinkdepository.drink_list;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.olive.drinkdepository.R;
import com.example.olive.drinkdepository.data.local.RealmCategoryListModel;
import com.example.olive.drinkdepository.data.local.controller.RealmHelper;
import com.example.olive.drinkdepository.data.network.AppDataManager;
import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.ui.base.BaseFragment;
import com.example.olive.drinkdepository.ui.utils.rx.AppSchedulerProvider;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrinkListFragment extends BaseFragment implements IDrinkListMvpView {

    @BindView(R.id.rVDrinkList)RecyclerView recyclerView;
    @BindView(R.id.swiperefreshDrinkList)SwipeRefreshLayout refreshLayout;
    @BindView(R.id.tVCat)TextView textViewCate;
    private Unbinder unbinder;
    private DrinkListPresenterImpl<DrinkListFragment> drinkListFragmentDrinkListPresenter;
    private Realm realm;
    private RealmHelper realmHelper;
    private ArrayList<RealmCategoryListModel> drinkArrayList;
    private RealmCategoryListModel realmCategoryListModel;
    Context context;


    public DrinkListFragment() {
        // Required empty public constructor
    }

    public void initRealm(){
        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);
        drinkArrayList = new ArrayList<>();
        drinkArrayList = realmHelper.getDrinksR();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setRetainInstance(true);
        initRealm();
        drinkListFragmentDrinkListPresenter = new DrinkListPresenterImpl<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        drinkListFragmentDrinkListPresenter.onAttach(this);
        drinkListCallService();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                drinkListCallService();
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.context=getActivity();

    }

    @Override
    public void onDestroy() {
        if(unbinder != null){
            unbinder.unbind();
        }
        unbinder = null;
        super.onDestroy();
    }





    public void drinkListCallService(){
        ReactiveNetwork.observeInternetConnectivity()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Boolean>() {
                               @Override
                               public void accept(Boolean isConnectedToInternet) throws Exception {
                                   if (isConnectedToInternet) {
                                       String page = getArguments().getString("page");
                                       drinkListFragmentDrinkListPresenter.loadDrinksList(page);

                                       textViewCate.setText(page);
                                   } else {
                                     // Toast.makeText(getActivity(), "No Network Connection", Toast.LENGTH_LONG).show();
                                       drinkArrayList = realmHelper.getDrinksR();
                                       recyclerView.setAdapter(new RealmDrinkListAdapter(getActivity().getApplicationContext(), drinkArrayList, R.layout.row_layout));
                                       refreshLayout.setRefreshing(false);
                                   }
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                          //     Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    @Override
    public void onFetchDataProgress() {
        showLoading();
    }

    @Override
    public void onFetchDataSuccess(DrinksModel drinksModel) {
//        realmCategoryListModel = new RealmCategoryListModel(
//                drinksModel.getDrinks().get(0).getIdDrink(),
//                drinksModel.getDrinks().get(0).getStrDrink(),
//                drinksModel.getDrinks().get(0).getStrDrinkThumb()
//        );
//            realmHelper.saveDrinksR(realmCategoryListModel);
            recyclerView.setAdapter(new DrinkListAdapter(getActivity(), drinksModel.getDrinks(), R.layout.row_layout));
            refreshLayout.setRefreshing(false);
            hideLoading();
        }

    @Override
    public void onFetchDataError(String error) {
        showMessage(error);
//        refreshLayout.setRefreshing(false);
        hideLoading();
    }
}
