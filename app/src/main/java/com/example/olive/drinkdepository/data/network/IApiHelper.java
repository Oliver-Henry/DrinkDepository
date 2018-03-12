package com.example.olive.drinkdepository.data.network;

import com.example.olive.drinkdepository.data.network.model.DrinksModel;

import io.reactivex.Observable;

/**
 * Created by olive on 03/03/2018.
 */

public interface IApiHelper {
    Observable<DrinksModel> getDrinksList(String c);
    Observable<DrinksModel> getIngredientsList();
    Observable<DrinksModel> getDrinksByIngredientList(String i);
    Observable<DrinksModel> getDrinkDetailsPage(int i);
    Observable<DrinksModel> getSearchedDrinkList(String s);
    Observable<DrinksModel> getRandomDrinkDetails();
    Observable<DrinksModel> getCategoriesList();

}
