package com.example.olive.drinkdepository.data.network.service;

import com.example.olive.drinkdepository.data.network.model.DrinksModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by olive on 03/03/2018.
 */

public interface IRequestInterface {

    @GET(ApiList.MARGARITA_TEST)
    //Observable<DrinksModel> getDrinks(@Query("api_key") String apiKey);
    Observable<DrinksModel> getDrinks();
}
