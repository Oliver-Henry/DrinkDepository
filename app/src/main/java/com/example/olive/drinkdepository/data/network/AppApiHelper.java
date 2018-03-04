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

    public AppApiHelper() { iRequestInterface = ServiceConnection.getConnection();}
}