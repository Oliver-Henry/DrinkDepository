package com.example.olive.drinkdepository.drink_list;

import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.ui.base.MvpView;

/**
 * Created by olive on 03/03/2018.
 */

public interface IDrinkListMvpView extends MvpView {
    void onFetchDataProgress();
    void onFetchDataSuccess(DrinksModel drinksModel);
    void onFetchDataError(String error);
}
