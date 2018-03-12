package com.example.olive.drinkdepository.home;

import com.example.olive.drinkdepository.ui.base.MvpPresenter;

/**
 * Created by olive on 12/03/2018.
 */

public interface IHomeMvpPresenter<V extends IHomeMvpView> extends MvpPresenter<V> {
    void loadCategoriesList();
}
