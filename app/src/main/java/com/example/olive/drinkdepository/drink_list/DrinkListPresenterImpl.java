package com.example.olive.drinkdepository.drink_list;

import com.example.olive.drinkdepository.data.network.IDataManager;
import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.ui.base.BasePresenter;
import com.example.olive.drinkdepository.ui.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by olive on 03/03/2018.
 */

public class DrinkListPresenterImpl<V extends IDrinkListMvpView> extends BasePresenter<V> implements IDrinkListMvpPresenter<V> {

    public DrinkListPresenterImpl(IDataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadDrinksList(String c) {
        getMvpView().onFetchDataProgress();
        getCompositeDisposable().add(getDataManager().getDrinksList(c)
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(new Consumer<DrinksModel>() {

                       @Override
                       public void accept(DrinksModel drinksModel) throws Exception {
                            getMvpView().onFetchDataSuccess(drinksModel);
                       }
                   },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onFetchDataError(throwable.getMessage());
                    }
                }
        ));
    }
}
