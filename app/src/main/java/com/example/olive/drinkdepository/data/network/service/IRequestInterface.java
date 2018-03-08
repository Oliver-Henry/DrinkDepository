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

    @GET(ApiList.CATEGORY)
    Observable<DrinksModel> getDrinks(@Query("c") String c);

    @GET(ApiList.DRINK_DETAILS)
    Observable<DrinksModel> getDrinkDetails(@Query("i") int i);
    //Observable<DrinksModel> getDrinkDetails(@Path("id") int id);

    @GET(ApiList.INGREDIENTS_LIST)
    Observable<DrinksModel> getIngredients();

    @GET(ApiList.SEARCH_BY_INGREDIENT)
    Observable<DrinksModel> getDrinksByIngredient(@Query("i") String i);

    @GET(ApiList.RANDOM_DRINK)
    Observable<DrinksModel> getRandomDrink();

    @GET(ApiList.DRINK_SEARCH)
    Observable<DrinksModel> getSearchedDrink(@Query("s") String s);
}
