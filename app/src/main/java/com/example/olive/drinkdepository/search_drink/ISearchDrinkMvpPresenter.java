package com.example.olive.drinkdepository.search_drink;

import com.example.olive.drinkdepository.ui.base.MvpPresenter;

/**
 * Created by olive on 07/03/2018.
 */

public interface ISearchDrinkMvpPresenter<V extends ISearchDrinkMvpView> extends MvpPresenter<V> {
    void loadSearchedDrinks(String s);
}
