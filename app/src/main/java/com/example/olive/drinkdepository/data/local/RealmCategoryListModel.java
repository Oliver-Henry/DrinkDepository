package com.example.olive.drinkdepository.data.local;

import io.realm.RealmObject;

/**
 * Created by olive on 14/03/2018.
 */

public class RealmCategoryListModel extends RealmObject {

    public RealmCategoryListModel(){}

    public RealmCategoryListModel(String idDrink, String strDrink, String strDrinkThumb){
            this.idDrink = idDrink;
            this.strDrink = strDrink;
            this.strDrinkThumb = strDrinkThumb;
    }

    private String idDrink;
    private String strDrink;
    private String strDrinkThumb;

    public String getIdDrink() {return idDrink;}

    public String getStrDrink() {return strDrink;}

    public String getStrDrinkThumb() {return strDrinkThumb;}

    public void setIdDrink(String idDrink) {this.idDrink = idDrink;}

    public void setStrDrink(String strDrink) {this.strDrink = strDrink;}

    public void setStrDrinkThumb(String strDrinkThumb) {this.strDrinkThumb = strDrinkThumb;}
}
