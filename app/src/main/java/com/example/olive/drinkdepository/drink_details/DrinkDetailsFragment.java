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

    ImageView imgDrink;
    TextView txtDrinkName;
    TextView txtGlassType;
    TextView txtAlcoholic;
    TextView txtCategory;
    TextView txtInstructions;
    TextView txtIngredient1;
    TextView txtMeasurement1;


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

        imgDrink= (ImageView) view.findViewById(R.id.imgVDrink);
        txtDrinkName= (TextView) view.findViewById(R.id.tVDrinkName);
        txtGlassType= (TextView) view.findViewById(R.id.tVGlassType);
        txtAlcoholic= (TextView) view.findViewById(R.id.tVAlcoholic);
        txtCategory= (TextView) view.findViewById(R.id.tVCategory);
        txtInstructions= (TextView) view.findViewById(R.id.tVInstructions);
        txtIngredient1= (TextView) view.findViewById(R.id.tVIngre1);
        txtMeasurement1= (TextView) view.findViewById(R.id.tVMeas1);

    }

    public void callService(){
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isConnectedToInternet) throws Exception {
                        if(isConnectedToInternet){
                            int id = getArguments().getInt("id", -1);
                            drinkDetailsFragmentDrinkDetailsMvpPresenter.loadDrinkDetails(id);
                        }
                        else{Toast.makeText(getActivity(), "No Network Connection", Toast.LENGTH_SHORT).show();}
                    }
                });
    }

    @Override
    public void onFetchDataProgress() {

    }

    @Override
    public void onFetchDataSuccess(DrinksModel drinksModel) {
        txtDrinkName.setText(drinksModel.getDrinks().get(0).getStrDrink());
        txtGlassType.setText(drinksModel.getDrinks().get(0).getStrGlass());
        txtAlcoholic.setText(drinksModel.getDrinks().get(0).getStrAlcoholic());
        txtCategory.setText(drinksModel.getDrinks().get(0).getStrCategory());
        txtInstructions.setText(drinksModel.getDrinks().get(0).getStrInstructions());
        txtIngredient1.setText(drinksModel.getDrinks().get(0).getStrIngredient1());
        txtMeasurement1.setText(drinksModel.getDrinks().get(0).getStrMeasure1());

        String url = drinksModel.getDrinks().get(0).getStrDrinkThumb();
        Picasso.with(getActivity()).load(url).resize(1000, 1000).centerCrop().into(imgDrink);

    }

    @Override
    public void onFetchDataError(String error) {
        showMessage(error);
    }
}
