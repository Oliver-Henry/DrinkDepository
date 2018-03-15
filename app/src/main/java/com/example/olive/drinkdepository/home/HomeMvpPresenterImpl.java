package com.example.olive.drinkdepository.home;

import com.example.olive.drinkdepository.data.network.IDataManager;
import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.ui.base.BasePresenter;
import com.example.olive.drinkdepository.ui.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by olive on 12/03/2018.
 */

public class HomeMvpPresenterImpl<V extends IHomeMvpView> extends BasePresenter<V> implements IHomeMvpPresenter<V> {

    public HomeMvpPresenterImpl(IDataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadCategoriesList() {
        getMvpView().onFetchDataProgress();
        getCompositeDisposable().add(getDataManager().getCategoriesList()
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
