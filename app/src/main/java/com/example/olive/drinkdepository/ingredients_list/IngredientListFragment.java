package com.example.olive.drinkdepository.ingredients_list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientListFragment extends BaseFragment implements IIngredientsListMvpView {
    private RecyclerView recyclerView;
    private IngredientsListPresenterImpl<IngredientListFragment> ingredientListFragmentIngredientsListPresenter;

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
        recyclerView= view.findViewById(R.id.rVIngredientList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ingredientListFragmentIngredientsListPresenter = new IngredientsListPresenterImpl<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        ingredientListFragmentIngredientsListPresenter.onAttach(this);
        callService();
    }

    public void callService(){
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isConnectedToInternet) throws Exception {
                        if(isConnectedToInternet){ingredientListFragmentIngredientsListPresenter.loadIngredientsList();}
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
            recyclerView.setAdapter(new IngredientsListAdapter(getActivity().getApplicationContext(), drinksModel.getDrinks(), R.layout.row_layout));
        hideLoading();
    }

    @Override
    public void onFetchDataError(String error) {
        showMessage(error);
        hideLoading();
    }
}
