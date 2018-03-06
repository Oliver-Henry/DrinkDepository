package com.example.olive.drinkdepository.drink_list;

import com.example.olive.drinkdepository.ui.base.MvpPresenter;

/**
 * Created by olive on 03/03/2018.
 */

public interface IDrinkListMvpPresenter<V extends IDrinkListMvpView> extends MvpPresenter<V> {
    void loadDrinksList();
    void loadCocktailDrinksList();
    void loadHomemadeDrinksList();
    void loadPartyDrinksList();
    void loadDrinksByIngredientList(String i);
}
