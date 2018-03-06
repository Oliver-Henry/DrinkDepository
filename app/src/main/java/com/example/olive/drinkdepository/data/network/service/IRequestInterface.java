package com.example.olive.drinkdepository.data.network.service;

import com.example.olive.drinkdepository.data.network.model.Drink;
import com.example.olive.drinkdepository.data.network.model.DrinksModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by olive on 03/03/2018.
 */

public interface IRequestInterface {

    @GET(ApiList.ORDINARY_DRINKS)
    Observable<DrinksModel> getDrinks();

    @GET(ApiList.COCKTAILS)
    Observable<DrinksModel> getCocktailDrinks();

    @GET(ApiList.HOMEMADE_LIQUEURS)
    Observable<DrinksModel> getHomemadeDrinks();

    @GET(ApiList.PUNCH_PARTY_DRINKS)
    Observable<DrinksModel> getPartyDrinks();

    @GET(ApiList.DRINK_DETAILS)
    Observable<DrinksModel> getDrinkDetails(@Query("i") int i);
    //Observable<DrinksModel> getDrinkDetails(@Path("id") int id);

    @GET(ApiList.INGREDIENTS_LIST)
    Observable<DrinksModel> getIngredients();

    @GET(ApiList.SEARCH_BY_INGREDIENT)
    Observable<DrinksModel> getDrinksByIngredient(@Query("i") String i);

    @GET(ApiList.RANDOM_DRINK)
    Observable<Drink> getRandomDrink();

}
