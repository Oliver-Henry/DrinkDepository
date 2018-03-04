package com.example.olive.drinkdepository.data.network;

import com.example.olive.drinkdepository.data.network.model.DrinksModel;

import io.reactivex.Observable;

/**
 * Created by olive on 03/03/2018.
 */

public interface IApiHelper {
    Observable<DrinksModel> getDrinksList();
}
