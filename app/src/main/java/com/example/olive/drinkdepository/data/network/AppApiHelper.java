package com.example.olive.drinkdepository.data.network;

import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.data.network.service.IRequestInterface;
import com.example.olive.drinkdepository.data.network.service.ServiceConnection;

import io.reactivex.Observable;

/**
 * Created by olive on 03/03/2018.
 */

public class AppApiHelper implements IApiHelper{

    private IRequestInterface iRequestInterface;


    @Override
    public Observable<DrinksModel> getDrinksList() {
        return iRequestInterface.getDrinks();
    }

    @Override
    public Observable<DrinksModel> getCocktailDrinksList() {return iRequestInterface.getCocktailDrinks();}

    @Override
    public Observable<DrinksModel> getHomemadeDrinksList() {return iRequestInterface.getHomemadeDrinks();}

    @Override
    public Observable<DrinksModel> getPartyDrinksList() {return iRequestInterface.getPartyDrinks();}

    @Override
    public Observable<DrinksModel> getIngredientsList() {
        return iRequestInterface.getIngredients();
    }

    @Override
    public Observable<DrinksModel> getDrinksByIngredientList(String i) {
        return iRequestInterface.getDrinksByIngredient(i);
    }

    @Override
    public Observable<DrinksModel> getDrinkDetailsPage(int i) {
        return iRequestInterface.getDrinkDetails(i);
    }


    public AppApiHelper() { iRequestInterface = ServiceConnection.getConnection();}
}
