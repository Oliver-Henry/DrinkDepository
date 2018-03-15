package com.example.olive.drinkdepository.search_drink;

import com.example.olive.drinkdepository.data.network.IDataManager;
import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.ui.base.BasePresenter;
import com.example.olive.drinkdepository.ui.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by olive on 07/03/2018.
 */

public class SearchDrinkMvpPresenterImpl<V extends ISearchDrinkMvpView> extends BasePresenter<V> implements ISearchDrinkMvpPresenter<V> {

    public SearchDrinkMvpPresenterImpl(IDataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadSearchedDrinks(String s) {
        getMvpView().onFetchDataProgress();
            getCompositeDisposable().add(getDataManager().getSearchedDrinkList(s)
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
