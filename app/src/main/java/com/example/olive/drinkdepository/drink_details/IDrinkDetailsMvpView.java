package com.example.olive.drinkdepository.drink_details;

import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.ui.base.MvpView;

/**
 * Created by olive on 06/03/2018.
 */

public interface IDrinkDetailsMvpView extends MvpView {
    void onFetchDataProgress();
    void onFetchDataSuccess(DrinksModel drinksModel);
    void onFetchDataError(String error);
}
