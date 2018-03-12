package com.example.olive.drinkdepository.ingredients_list;


import com.example.olive.drinkdepository.ui.base.MvpPresenter;

/**
 * Created by olive on 06/03/2018.
 */

public interface IIngredientsListMvpPresenter<V extends IIngredientsListMvpView> extends MvpPresenter<V> {
    void loadIngredientsList();
    void loadDrinksByIngredientList(String i);
}
