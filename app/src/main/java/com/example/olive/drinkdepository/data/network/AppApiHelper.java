package com.example.olive.drinkdepository.data.network;

import com.example.olive.drinkdepository.data.network.model.Drink;
import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.data.network.service.IRequestInterface;
import com.example.olive.drinkdepository.data.network.service.ServiceConnection;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by olive on 03/03/2018.
 */

public class AppApiHelper implements IApiHelper{

    private IRequestInterface iRequestInterface;


    @Override
    public Observable<DrinksModel>getDrinksList(String c) {return iRequestInterface.getDrinks(c);}

    @Override
    public Observable<DrinksModel> getIngredientsList() {return iRequestInterface.getIngredients();}

    @Override
    public Observable<DrinksModel> getDrinksByIngredientList(String i) {return iRequestInterface.getDrinksByIngredient(i);}

    @Override
    public Observable<DrinksModel> getDrinkDetailsPage(int i) {return iRequestInterface.getDrinkDetails(i);}

    @Override
    public Observable<DrinksModel> getSearchedDrinkList(String s) {return iRequestInterface.getSearchedDrink(s);}

    @Override
    public Observable<DrinksModel> getRandomDrinkDetails() {return iRequestInterface.getRandomDrink();}

    @Override
    public Observable<DrinksModel> getCategoriesList() {return iRequestInterface.getCategories();}


    public AppApiHelper() { iRequestInterface = ServiceConnection.getConnection();}
}
