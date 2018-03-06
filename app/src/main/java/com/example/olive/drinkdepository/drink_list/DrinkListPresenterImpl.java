package com.example.olive.drinkdepository.drink_list;

import com.example.olive.drinkdepository.data.network.IDataManager;
import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.data.network.service.ApiList;
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
    public void loadDrinksList() {
        getCompositeDisposable().add(getDataManager().getDrinksList()
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

    @Override
    public void loadCocktailDrinksList() {
        getCompositeDisposable().add(getDataManager().getCocktailDrinksList()
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
                }));
    }

    @Override
    public void loadHomemadeDrinksList() {
        getCompositeDisposable().add(getDataManager().getHomemadeDrinksList()
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
                }));
    }

    @Override
    public void loadPartyDrinksList() {
        getCompositeDisposable().add(getDataManager().getPartyDrinksList()
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
                }));
    }
}
