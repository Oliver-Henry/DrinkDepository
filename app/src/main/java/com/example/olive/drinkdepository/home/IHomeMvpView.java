package com.example.olive.drinkdepository.home;

import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.ui.base.MvpView;

/**
 * Created by olive on 12/03/2018.
 */

public interface IHomeMvpView extends MvpView {
    void onFetchDataProgress();
    void onFetchDataSuccess(DrinksModel drinksModel);
    void onFetchDataError(String error);
}
