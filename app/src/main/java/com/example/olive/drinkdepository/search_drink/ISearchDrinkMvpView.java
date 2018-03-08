package com.example.olive.drinkdepository.search_drink;

import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.ui.base.MvpView;

/**
 * Created by olive on 07/03/2018.
 */

public interface ISearchDrinkMvpView extends MvpView {
    void onFetchDataProgress();
    void onFetchDataSuccess(DrinksModel drinksModel);
    void onFetchDataError(String error);
}
