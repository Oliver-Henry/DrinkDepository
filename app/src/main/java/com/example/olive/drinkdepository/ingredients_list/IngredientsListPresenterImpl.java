package com.example.olive.drinkdepository.ingredients_list;

import com.example.olive.drinkdepository.data.network.IDataManager;
import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.drink_list.IDrinkListMvpView;
import com.example.olive.drinkdepository.ui.base.BasePresenter;
import com.example.olive.drinkdepository.ui.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by olive on 06/03/2018.
 */

public class IngredientsListPresenterImpl<V extends IIngredientsListMvpView> extends BasePresenter<V> implements IIngredientsListMvpPresenter<V> {

    public IngredientsListPresenterImpl(IDataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void loadIngredientsList() {
        getCompositeDisposable().add(getDataManager().getIngredientsList()
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
    public void loadDrinksByIngredientList(String i) {
        getCompositeDisposable().add(getDataManager().getDrinksByIngredientList(i)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<DrinksModel>() {
                               @Override
                               public void accept(DrinksModel drinksModel) throws Exception {
                                   getMvpView().onFetchDataSuccess2(drinksModel);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getMvpView().onFetchDataError2(throwable.getMessage());
                            }
                        }));
    }
}
