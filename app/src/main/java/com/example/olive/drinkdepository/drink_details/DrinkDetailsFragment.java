package com.example.olive.drinkdepository.drink_details;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrinkDetailsFragment extends BaseFragment implements IDrinkDetailsMvpView {
    private IRequestInterface iRequestInterface;
    private DrinkDetailsMvpPresenterImpl<DrinkDetailsFragment> drinkDetailsFragmentDrinkDetailsMvpPresenter;

 //   ImageView imgDrink;
    PanoramaImageView imgDrink;
    private GyroscopeObserver gyroscopeObserver;
    TextView txtDrinkName;
    TextView txtGlassType;
    TextView txtAlcoholic;
    TextView txtCategory;
    TextView txtInstructions;

    TextView txtIngredient1,txtIngredient2,txtIngredient3,txtIngredient4,txtIngredient5,txtIngredient6,txtIngredient7,txtIngredient8,txtIngredient9;
    TextView txtIngredient10,txtIngredient11,txtIngredient12,txtIngredient13,txtIngredient14,txtIngredient15;

    TextView txtMeasurement1,txtMeasurement2,txtMeasurement3,txtMeasurement4,txtMeasurement5,txtMeasurement6,txtMeasurement7,txtMeasurement8,txtMeasurement9;
    TextView txtMeasurement10,txtMeasurement11,txtMeasurement12,txtMeasurement13,txtMeasurement14,txtMeasurement15;

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
        drinkDetailsFragmentDrinkDetailsMvpPresenter = new DrinkDetailsMvpPresenterImpl<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        drinkDetailsFragmentDrinkDetailsMvpPresenter.onAttach(this);
        callService();

        gyroscopeObserver = new GyroscopeObserver();
        // Set the maximum radian the device should rotate to show image's bounds.
        // It should be set between 0 and π/2.
        // The default value is π/9.
        gyroscopeObserver.setMaxRotateRadian(Math.PI/2);

        //imgDrink= (ImageView) view.findViewById(R.id.imgVDrink);
        imgDrink= (PanoramaImageView) view.findViewById(R.id.imgVDrink);
        imgDrink.setGyroscopeObserver(gyroscopeObserver);
        txtDrinkName= (TextView) view.findViewById(R.id.tVDrinkName);
        txtGlassType= (TextView) view.findViewById(R.id.tVGlassType);
        txtAlcoholic= (TextView) view.findViewById(R.id.tVAlcoholic);
        txtCategory= (TextView) view.findViewById(R.id.tVCategory);
        txtInstructions= (TextView) view.findViewById(R.id.tVInstructions);
        txtIngredient1= (TextView) view.findViewById(R.id.tVIngre1);
        txtIngredient2= (TextView) view.findViewById(R.id.tVIngre2);
        txtIngredient3= (TextView) view.findViewById(R.id.tVIngre3);
        txtIngredient4= (TextView) view.findViewById(R.id.tVIngre4);
        txtIngredient5= (TextView) view.findViewById(R.id.tVIngre5);
        txtIngredient6= (TextView) view.findViewById(R.id.tVIngre6);
        txtIngredient7= (TextView) view.findViewById(R.id.tVIngre7);
        txtIngredient8= (TextView) view.findViewById(R.id.tVIngre8);
        txtIngredient9= (TextView) view.findViewById(R.id.tVIngre9);
        txtIngredient10= (TextView) view.findViewById(R.id.tVIngre10);
        txtIngredient11= (TextView) view.findViewById(R.id.tVIngre11);
        txtIngredient12= (TextView) view.findViewById(R.id.tVIngre12);
        txtIngredient13= (TextView) view.findViewById(R.id.tVIngre13);
        txtIngredient14= (TextView) view.findViewById(R.id.tVIngre14);
        txtIngredient15= (TextView) view.findViewById(R.id.tVIngre15);
        txtMeasurement1= (TextView) view.findViewById(R.id.tVMeas1);
        txtMeasurement2= (TextView) view.findViewById(R.id.tVMeas2);
        txtMeasurement3= (TextView) view.findViewById(R.id.tVMeas3);
        txtMeasurement4= (TextView) view.findViewById(R.id.tVMeas4);
        txtMeasurement5= (TextView) view.findViewById(R.id.tVMeas5);
        txtMeasurement6= (TextView) view.findViewById(R.id.tVMeas6);
        txtMeasurement7= (TextView) view.findViewById(R.id.tVMeas7);
        txtMeasurement8= (TextView) view.findViewById(R.id.tVMeas8);
        txtMeasurement9= (TextView) view.findViewById(R.id.tVMeas9);
        txtMeasurement10= (TextView) view.findViewById(R.id.tVMeas10);
        txtMeasurement11= (TextView) view.findViewById(R.id.tVMeas11);
        txtMeasurement12= (TextView) view.findViewById(R.id.tVMeas12);
        txtMeasurement13= (TextView) view.findViewById(R.id.tVMeas13);
        txtMeasurement14= (TextView) view.findViewById(R.id.tVMeas14);
        txtMeasurement15= (TextView) view.findViewById(R.id.tVMeas15);

    }

    @Override
    public void onResume() {
        super.onResume();
        gyroscopeObserver.register(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        gyroscopeObserver.unregister();
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
        txtDrinkName.setText(drinksModel.getDrinks().get(0).getStrDrink());
        txtGlassType.setText("Glass Type: " + drinksModel.getDrinks().get(0).getStrGlass());
        txtAlcoholic.setText("Alcoholic/Non-Alcoholic: " + drinksModel.getDrinks().get(0).getStrAlcoholic());
        txtCategory.setText("Category: " + drinksModel.getDrinks().get(0).getStrCategory());
        txtInstructions.setText("Preparation Instructions: " + drinksModel.getDrinks().get(0).getStrInstructions());
        txtIngredient1.setText(drinksModel.getDrinks().get(0).getStrIngredient1());
        txtIngredient2.setText(drinksModel.getDrinks().get(0).getStrIngredient2());
        txtIngredient3.setText(drinksModel.getDrinks().get(0).getStrIngredient3());
        txtIngredient4.setText(drinksModel.getDrinks().get(0).getStrIngredient4());
        txtIngredient5.setText(drinksModel.getDrinks().get(0).getStrIngredient5());
        txtIngredient6.setText(drinksModel.getDrinks().get(0).getStrIngredient6());
        txtIngredient7.setText(drinksModel.getDrinks().get(0).getStrIngredient7());
        txtIngredient8.setText(drinksModel.getDrinks().get(0).getStrIngredient8());
        txtIngredient9.setText(drinksModel.getDrinks().get(0).getStrIngredient9());
        txtIngredient10.setText(drinksModel.getDrinks().get(0).getStrIngredient10());
        txtIngredient11.setText(drinksModel.getDrinks().get(0).getStrIngredient11());
        txtIngredient12.setText(drinksModel.getDrinks().get(0).getStrIngredient12());
        txtIngredient13.setText(drinksModel.getDrinks().get(0).getStrIngredient13());
        txtIngredient14.setText(drinksModel.getDrinks().get(0).getStrIngredient14());
        txtIngredient15.setText(drinksModel.getDrinks().get(0).getStrIngredient15());
        txtMeasurement1.setText(drinksModel.getDrinks().get(0).getStrMeasure1());
        txtMeasurement2.setText(drinksModel.getDrinks().get(0).getStrMeasure2());
        txtMeasurement3.setText(drinksModel.getDrinks().get(0).getStrMeasure3());
        txtMeasurement4.setText(drinksModel.getDrinks().get(0).getStrMeasure4());
        txtMeasurement5.setText(drinksModel.getDrinks().get(0).getStrMeasure5());
        txtMeasurement6.setText(drinksModel.getDrinks().get(0).getStrMeasure6());
        txtMeasurement7.setText(drinksModel.getDrinks().get(0).getStrMeasure7());
        txtMeasurement8.setText(drinksModel.getDrinks().get(0).getStrMeasure8());
        txtMeasurement9.setText(drinksModel.getDrinks().get(0).getStrMeasure9());
        txtMeasurement10.setText(drinksModel.getDrinks().get(0).getStrMeasure10());
        txtMeasurement11.setText(drinksModel.getDrinks().get(0).getStrMeasure11());
        txtMeasurement12.setText(drinksModel.getDrinks().get(0).getStrMeasure12());
        txtMeasurement13.setText(drinksModel.getDrinks().get(0).getStrMeasure13());
        txtMeasurement14.setText(drinksModel.getDrinks().get(0).getStrMeasure14());
        txtMeasurement15.setText(drinksModel.getDrinks().get(0).getStrMeasure15());


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
