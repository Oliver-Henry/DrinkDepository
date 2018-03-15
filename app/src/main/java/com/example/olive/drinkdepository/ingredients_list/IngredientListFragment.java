package com.example.olive.drinkdepository.ingredients_list;


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
import android.widget.Toast;

import com.example.olive.drinkdepository.MainActivity;
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
public class IngredientListFragment extends BaseFragment implements IIngredientsListMvpView {
    private IngredientsListPresenterImpl<IngredientListFragment> ingredientListFragmentIngredientsListPresenter;
    @BindView(R.id.rVIngredientList)RecyclerView recyclerView;
    @BindView(R.id.swiperefreshIngre)SwipeRefreshLayout refreshLayout;
    @BindView(R.id.rVIngredientDrinkList) RecyclerView recyclerViewD;
    @BindView(R.id.tVSelectedIngredient) TextView textViewSelectedIngre;
    private Unbinder unbinder;
    LinearLayoutManager HorizontalLayout ;
    String page;

    public IngredientListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredient_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     //   page = "";
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerViewD.setLayoutManager(new LinearLayoutManager(getActivity()));
        ingredientListFragmentIngredientsListPresenter = new IngredientsListPresenterImpl<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        ingredientListFragmentIngredientsListPresenter.onAttach(this);
        ingredientListCallService();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ingredientListCallService();
            }
        });
    }

    @Override
    public void onDestroy() {
        if(unbinder != null){
            unbinder.unbind();
        }
        unbinder = null;
        super.onDestroy();
    }

    public void ingredientListCallService(){
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isConnectedToInternet) throws Exception {
                        page = getArguments().getString("page");
                        if(isConnectedToInternet){
                        ingredientListFragmentIngredientsListPresenter.loadIngredientsList();

                            if(page == "I"){String i = getArguments().getString("name");
                               // ingredientListFragmentIngredientsListPresenter.loadIngredientsList();
                                ingredientListFragmentIngredientsListPresenter.loadDrinksByIngredientList(i);
                            textViewSelectedIngre.setText(i);}}

                        else{Toast.makeText(getActivity(), "No Network Connection", Toast.LENGTH_SHORT).show();}
                    }
                });
    }

    @Override
    public void onFetchDataProgress() {
        showLoading();
    }

    @Override
    public void onFetchDataSuccess(DrinksModel drinksModel) {

        recyclerView.setAdapter(new IngredientsListAdapter(getActivity().getApplicationContext(), drinksModel.getDrinks(), R.layout.ingredient_row_layout));
        hideLoading();
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchDataError(String error) {
        showMessage(error);
        hideLoading();
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchDataProgress2() {
        showLoading();
    }

    @Override
    public void onFetchDataSuccess2(DrinksModel drinksModel) {
        recyclerViewD.setAdapter(new IngredientDrinkListAdapter(getActivity().getApplicationContext(), drinksModel.getDrinks(), R.layout.row_layout));

        hideLoading();
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchDataError2(String error) {
        showMessage(error);
        hideLoading();
        refreshLayout.setRefreshing(false);
    }
}
