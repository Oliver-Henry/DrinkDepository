package com.example.olive.drinkdepository.data.network;

import com.example.olive.drinkdepository.data.network.model.Drink;
import com.example.olive.drinkdepository.data.network.model.DrinksModel;

import java.util.List;

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
    public Observable<DrinksModel> getDrinksList(String c) {return iApiHelper.getDrinksList(c);}

    @Override
    public Observable<DrinksModel> getIngredientsList() {
        return iApiHelper.getIngredientsList();
    }

    @Override
    public Observable<DrinksModel> getDrinksByIngredientList(String i) {return iApiHelper.getDrinksByIngredientList(i);}

    @Override
    public Observable<DrinksModel> getDrinkDetailsPage(int i) {return iApiHelper.getDrinkDetailsPage(i);}

    @Override
    public Observable<DrinksModel> getSearchedDrinkList(String s) {return iApiHelper.getSearchedDrinkList(s);}

    @Override
    public Observable<DrinksModel> getRandomDrinkDetails() {return iApiHelper.getRandomDrinkDetails();}

    @Override
    public Observable<DrinksModel> getCategoriesList() {return iApiHelper.getCategoriesList();}
}
