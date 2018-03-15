package com.example.olive.drinkdepository.data.local.controller;

import com.example.olive.drinkdepository.data.local.RealmCategoryListModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by olive on 11/02/2018.
 */

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void saveDrinksR(final RealmCategoryListModel realmCategoryListModel){
        //Async
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {realm.copyToRealm(realmCategoryListModel);}
        });
    }

    public ArrayList<RealmCategoryListModel> getDrinksR(){

        ArrayList<RealmCategoryListModel> drinkArrayList = new ArrayList<>();

        RealmResults<RealmCategoryListModel> realmResults= realm.where(RealmCategoryListModel.class).findAll();

        for(RealmCategoryListModel realmCategoryListModel: realmResults){
            drinkArrayList.add(realmCategoryListModel);
        }
        return drinkArrayList;
    }
}
