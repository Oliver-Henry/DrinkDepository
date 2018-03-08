package com.example.olive.drinkdepository.drink_details;

import com.example.olive.drinkdepository.drink_list.IDrinkListMvpView;
import com.example.olive.drinkdepository.ui.base.MvpPresenter;

/**
 * Created by olive on 06/03/2018.
 */

public interface IDrinkDetailsMvpPresenter<V extends IDrinkDetailsMvpView> extends MvpPresenter<V> {
    void loadDrinkDetails(int i);
    void loadRandomDrinkDetails();
}
