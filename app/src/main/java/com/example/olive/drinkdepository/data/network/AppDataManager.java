package com.example.olive.drinkdepository.data.network;

import com.example.olive.drinkdepository.data.network.model.DrinksModel;

import io.reactivex.Observable;

/**
 * Created by olive on 03/03/2018.
 */

public class AppDataManager implements IDataManager {
    private IApiHelper iApiHelper;

    public AppDataManager() {
        iApiHelper = new AppApiHelper();
    }


    @Override
    public Observable<DrinksModel> getDrinksList() {
        return iApiHelper.getDrinksList();
    }
}
