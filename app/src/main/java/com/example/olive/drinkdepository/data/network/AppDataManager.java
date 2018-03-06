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

    @Override
    public Observable<DrinksModel> getCocktailDrinksList() {return iApiHelper.getCocktailDrinksList();}

    @Override
    public Observable<DrinksModel> getHomemadeDrinksList() {return iApiHelper.getHomemadeDrinksList();}

    @Override
    public Observable<DrinksModel> getPartyDrinksList() {return iApiHelper.getPartyDrinksList();}

    @Override
    public Observable<DrinksModel> getIngredientsList() {
        return iApiHelper.getIngredientsList();
    }

    @Override
    public Observable<DrinksModel> getDrinksByIngredientList(String i) {
        return iApiHelper.getDrinksByIngredientList(i);
    }

    @Override
    public Observable<DrinksModel> getDrinkDetailsPage(int i) {
        return iApiHelper.getDrinkDetailsPage(i);
    }
}
