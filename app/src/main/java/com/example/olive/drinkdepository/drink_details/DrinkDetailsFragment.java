package com.example.olive.drinkdepository.drink_details;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olive.drinkdepository.R;
import com.example.olive.drinkdepository.data.network.AppDataManager;
import com.example.olive.drinkdepository.data.network.model.Drink;
import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.data.network.service.ApiList;
import com.example.olive.drinkdepository.data.network.service.IRequestInterface;
import com.example.olive.drinkdepository.data.network.service.ServiceConnection;
import com.example.olive.drinkdepository.ui.base.BaseFragment;
import com.example.olive.drinkdepository.ui.base.BasePresenter;
import com.example.olive.drinkdepository.ui.utils.NetworkUtils;
import com.example.olive.drinkdepository.ui.utils.rx.AppSchedulerProvider;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;
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

    public DrinkDetailsFragment() {
        // Required empty public constructor
    }


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
        unbinder = ButterKnife.bind(this, view);
        drinkDetailsFragmentDrinkDetailsMvpPresenter = new DrinkDetailsMvpPresenterImpl<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        drinkDetailsFragmentDrinkDetailsMvpPresenter.onAttach(this);

        callService();

    }

    @Override
    public void onDestroy() {
        if(unbinder != null){
            unbinder.unbind();
        }
        unbinder = null;
        super.onDestroy();
    }

    public void callService(){
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isConnectedToInternet) throws Exception {
                        if(isConnectedToInternet){
                            String page = getArguments().getString("page");
                            int id = getArguments().getInt("id", -1);
                            if(page == "D"){drinkDetailsFragmentDrinkDetailsMvpPresenter.loadDrinkDetails(id);}
                            else if(page == "R"){drinkDetailsFragmentDrinkDetailsMvpPresenter.loadRandomDrinkDetails();}
                        }
                        else{Toast.makeText(getActivity(), "No Network Connection", Toast.LENGTH_SHORT).show();}
                    }
                });
    }

    @Override
    public void onFetchDataProgress() {
        showLoading();
    }

    @Override
    public void onFetchDataSuccess(DrinksModel drinksModel) {
        drinkDetailsTextViews.get(0).setText(drinksModel.getDrinks().get(0).getStrDrink());
        drinkDetailsTextViews.get(1).setText("Glass Type: " + drinksModel.getDrinks().get(0).getStrGlass());
        drinkDetailsTextViews.get(2).setText("Alcoholic/Non-Alcoholic: " + drinksModel.getDrinks().get(0).getStrAlcoholic());
        drinkDetailsTextViews.get(3).setText("Category: " + drinksModel.getDrinks().get(0).getStrCategory());
        drinkDetailsTextViews.get(4).setText("Preparation Instructions: " + drinksModel.getDrinks().get(0).getStrInstructions());

//        String ingredients = "";
//        if(drinksModel.getDrinks().get(0).getStrIngredient1() != null || drinksModel.getDrinks().get(0).getStrIngredient1() != "null" || drinksModel.getDrinks().get(0).getStrIngredient1() != " ")
//        {ingredients = drinksModel.getDrinks().get(0).getStrIngredient1();}
//        if(drinksModel.getDrinks().get(0).getStrIngredient2() != null || drinksModel.getDrinks().get(0).getStrIngredient2() != "null" || drinksModel.getDrinks().get(0).getStrIngredient2() != " ")
//        {ingredients = ingredients + "\n" + drinksModel.getDrinks().get(0).getStrIngredient2();}
//        if(drinksModel.getDrinks().get(0).getStrIngredient3() != null || drinksModel.getDrinks().get(0).getStrIngredient3() != "null" || drinksModel.getDrinks().get(0).getStrIngredient3() != " ")
//        {ingredients = ingredients + "\n" + drinksModel.getDrinks().get(0).getStrIngredient3();}
//        if(drinksModel.getDrinks().get(0).getStrIngredient4() != null || drinksModel.getDrinks().get(0).getStrIngredient4() != "null" || drinksModel.getDrinks().get(0).getStrIngredient4() != " ")
//        {ingredients = ingredients + "\n" + drinksModel.getDrinks().get(0).getStrIngredient4();}
//        if(drinksModel.getDrinks().get(0).getStrIngredient5() != null || drinksModel.getDrinks().get(0).getStrIngredient5() != "null" || drinksModel.getDrinks().get(0).getStrIngredient5() != " ")
//        {ingredients = ingredients + "\n" + drinksModel.getDrinks().get(0).getStrIngredient5();}
//
//        drinkDetailsTextViews.get(5).setText(ingredients);
                drinkDetailsTextViews.get(5).setText("Ingredients:\n" + drinksModel.getDrinks().get(0).getStrIngredient1() + "\n" + drinksModel.getDrinks().get(0).getStrIngredient2()
                + "\n" + drinksModel.getDrinks().get(0).getStrIngredient3() + "\n" + drinksModel.getDrinks().get(0).getStrIngredient4()
                + "\n" + drinksModel.getDrinks().get(0).getStrIngredient5() + "\n" + drinksModel.getDrinks().get(0).getStrIngredient6()
                + "\n" + drinksModel.getDrinks().get(0).getStrIngredient7() + "\n" + drinksModel.getDrinks().get(0).getStrIngredient8()
                + "\n" + drinksModel.getDrinks().get(0).getStrIngredient9() + "\n" + drinksModel.getDrinks().get(0).getStrIngredient10()
                + "\n" + drinksModel.getDrinks().get(0).getStrIngredient11() + "\n" + drinksModel.getDrinks().get(0).getStrIngredient12()
                + "\n" + drinksModel.getDrinks().get(0).getStrIngredient13() + "\n" + drinksModel.getDrinks().get(0).getStrIngredient14()
                + "\n" + drinksModel.getDrinks().get(0).getStrIngredient15());


        drinkDetailsTextViews.get(6).setText("Measurements:\n" + drinksModel.getDrinks().get(0).getStrMeasure1() + "\n" + drinksModel.getDrinks().get(0).getStrMeasure2()
                + "\n" + drinksModel.getDrinks().get(0).getStrMeasure3() + "\n" + drinksModel.getDrinks().get(0).getStrMeasure4()
                + "\n" + drinksModel.getDrinks().get(0).getStrMeasure5() + "\n" + drinksModel.getDrinks().get(0).getStrMeasure6()
                + "\n" + drinksModel.getDrinks().get(0).getStrMeasure7() + "\n" + drinksModel.getDrinks().get(0).getStrMeasure8()
                + "\n" + drinksModel.getDrinks().get(0).getStrMeasure9() + "\n" + drinksModel.getDrinks().get(0).getStrMeasure10()
                + "\n" + drinksModel.getDrinks().get(0).getStrMeasure11() + "\n" + drinksModel.getDrinks().get(0).getStrMeasure12()
                + "\n" + drinksModel.getDrinks().get(0).getStrMeasure13() + "\n" + drinksModel.getDrinks().get(0).getStrMeasure14()
                + "\n" + drinksModel.getDrinks().get(0).getStrMeasure15());

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
