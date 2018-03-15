package com.example.olive.drinkdepository.search_drink;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
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
public class SearchDrinkFragment extends BaseFragment implements ISearchDrinkMvpView {
    @BindView(R.id.rVDrinkSearch)RecyclerView recyclerView;
    @BindView(R.id.sVDrinkSearch) SearchView searchView;
    private Unbinder unbinder;
    private SearchDrinkMvpPresenterImpl<SearchDrinkFragment> searchDrinkFragmentSearchDrinkMvpPresenter;

    public SearchDrinkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_drink, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchView.setQueryHint("Margarita");
       // searchView.setQuery("Margarita", false);
        searchDrinkCallService((String.valueOf(searchView.getQueryHint())));

        searchView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                searchView.hasFocus();
//                if (recyclerView.hasFocus()) {
//                    recyclerView.clearFocus();
//                }
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                if(s != null){searchDrinkCallService(s);}
                return true;
            }
        });
        searchDrinkFragmentSearchDrinkMvpPresenter= new SearchDrinkMvpPresenterImpl<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        searchDrinkFragmentSearchDrinkMvpPresenter.onAttach(this);
    }


    @Override
    public void onDestroy() {
        if(unbinder != null){
            unbinder.unbind();
        }
        unbinder = null;
        super.onDestroy();
    }


    public void searchDrinkCallService(String s){
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isConnectedToInternet) throws Exception {
                        if(isConnectedToInternet){ //Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                            searchDrinkFragmentSearchDrinkMvpPresenter.loadSearchedDrinks(s);}
        //                else{Toast.makeText(getActivity().getApplicationContext(), "No Network Connection", Toast.LENGTH_SHORT).show();}
                    }
                });

    }

    @Override
    public void onFetchDataProgress() {
        showLoading();
    }

    @Override
    public void onFetchDataSuccess(DrinksModel drinksModel) {
           if(drinksModel.getDrinks().size() > 0){recyclerView.setAdapter(new SearchDrinkAdapter(getActivity().getApplicationContext(), drinksModel.getDrinks(), R.layout.row_layout));}
 //          else{Toast.makeText(getContext(), "No Results Found", Toast.LENGTH_LONG).show();}
        hideLoading();

    }

    @Override
    public void onFetchDataError(String error) {
        showMessage(error);
        hideLoading();
    }


}
