package com.example.olive.drinkdepository.ingredients_list;

import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.ui.base.MvpView;

/**
 * Created by olive on 06/03/2018.
 */

public interface IIngredientsListMvpView extends MvpView {
    void onFetchDataProgress();
    void onFetchDataSuccess(DrinksModel drinksModel);
    void onFetchDataError(String error);

    void onFetchDataProgress2();
    void onFetchDataSuccess2(DrinksModel drinksModel);
    void onFetchDataError2(String error);
}
