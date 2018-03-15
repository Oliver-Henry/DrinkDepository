package com.example.olive.drinkdepository.drink_details;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olive.drinkdepository.R;
import com.example.olive.drinkdepository.data.network.AppDataManager;
import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.ui.base.BaseFragment;
import com.example.olive.drinkdepository.ui.utils.rx.AppSchedulerProvider;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrinkDetailsFragment extends BaseFragment implements IDrinkDetailsMvpView {

    private DrinkDetailsMvpPresenterImpl<DrinkDetailsFragment> drinkDetailsFragmentDrinkDetailsMvpPresenter;

    @BindView(R.id.imgVDrink) ImageView imgDrink;
    @BindViews({R.id.tVDrinkName, R.id.tVGlassType, R.id.tVAlcoholic, R.id.tVCategory, R.id.tVInstructions, R.id.tVIngredientsText, R.id.tVMeasurmentText})
    List<TextView> drinkDetailsTextViews;

    private Unbinder unbinder;
//    private Realm realm;
//    private RealmHelper realmHelper;
//    private ArrayList<Drink> drinkArrayList;

    public DrinkDetailsFragment() {
        // Required empty public constructor
    }

//    public void initRealm(){
//        realm = Realm.getDefaultInstance();
//        realmHelper = new RealmHelper(realm);
//        drinkArrayList = new ArrayList<>();
//        drinkArrayList = realmHelper.getDrinksR();
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drink_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
//        initRealm();
        unbinder = ButterKnife.bind(this, view);
        drinkDetailsFragmentDrinkDetailsMvpPresenter = new DrinkDetailsMvpPresenterImpl<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        drinkDetailsFragmentDrinkDetailsMvpPresenter.onAttach(this);

        drinkDetailsCallService();

    }

    @Override
    public void onDestroy() {
        if(unbinder != null){
            unbinder.unbind();
        }
        unbinder = null;
        super.onDestroy();
    }

    public void drinkDetailsCallService(){
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isConnectedToInternet) throws Exception {
                        if (isConnectedToInternet) {
                            String page = DrinkDetailsFragment.this.getArguments().getString("page");
                            int id = DrinkDetailsFragment.this.getArguments().getInt("id", -1);
                            if (page == "D") {
                                drinkDetailsFragmentDrinkDetailsMvpPresenter.loadDrinkDetails(id);
                            } else if (page == "R") {
                                drinkDetailsFragmentDrinkDetailsMvpPresenter.loadRandomDrinkDetails();
                            }
                        } else {
//                            Toast.makeText(DrinkDetailsFragment.this.getActivity().getApplicationContext(), "No Network Connection", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onFetchDataProgress() {
        try {
            if(getActivity() != null){showLoading();}
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public void onFetchDataSuccess(DrinksModel drinksModel) {
//        drinkDetailsTextViews.get(0).setText(drink.getStrDrink());

        drinkDetailsTextViews.get(0).setText(drinksModel.getDrinks().get(0).getStrDrink());
        drinkDetailsTextViews.get(1).setText("Glass Type: " + drinksModel.getDrinks().get(0).getStrGlass());
        drinkDetailsTextViews.get(2).setText("Alcoholic/Non-Alcoholic: " + drinksModel.getDrinks().get(0).getStrAlcoholic());
        drinkDetailsTextViews.get(3).setText("Category: " + drinksModel.getDrinks().get(0).getStrCategory());
        drinkDetailsTextViews.get(4).setText("Preparation Instructions: " + drinksModel.getDrinks().get(0).getStrInstructions());
        drinkDetailsTextViews.get(5).setText(drinksModel.getDrinks().get(0).getAllIngredients());
        drinkDetailsTextViews.get(6).setText(drinksModel.getDrinks().get(0).getAllMeasurements());

        String url = drinksModel.getDrinks().get(0).getStrDrinkThumb();
        Picasso.with(getActivity()).load(url).resize(1000, 1000).centerCrop().into(imgDrink);
        hideLoading();

    }

    @Override
    public void onFetchDataError(String error) {
        showMessage(error);
        hideLoading();
    }
}
