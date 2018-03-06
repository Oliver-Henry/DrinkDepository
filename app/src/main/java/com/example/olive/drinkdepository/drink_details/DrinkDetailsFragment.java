package com.example.olive.drinkdepository.drink_details;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olive.drinkdepository.R;
import com.example.olive.drinkdepository.data.network.model.Drink;
import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.data.network.service.ApiList;
import com.example.olive.drinkdepository.data.network.service.IRequestInterface;
import com.example.olive.drinkdepository.data.network.service.ServiceConnection;
import com.example.olive.drinkdepository.ui.utils.NetworkUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrinkDetailsFragment extends Fragment {
    private IRequestInterface iRequestInterface;

    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;


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

        textView= (TextView) view.findViewById(R.id.tVDrinkName);
        textView2= (TextView) view.findViewById(R.id.tVInstructions);
        textView3= (TextView) view.findViewById(R.id.tVIngre1);
        textView4= (TextView) view.findViewById(R.id.tVMeas1);



        iRequestInterface= ServiceConnection.getConnection();
        if(NetworkUtils.isNetworkConnected(getActivity())){
            displayDrinkDetails();
        }else{

            Toast.makeText(getActivity(), "No Network Available", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayDrinkDetails(){
        int id = getArguments().getInt("id", -1);
       // int position = getArguments().getInt("position", -1);
        iRequestInterface.getDrinkDetails(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<DrinksModel>() {
                               @Override
                               public void accept(DrinksModel drinksModel) throws Exception {
                                   textView.setText(drinksModel.getDrinks().get(0).getStrDrink());
                                   textView2.setText(drinksModel.getDrinks().get(0).getStrInstructions());
                                   textView3.setText(drinksModel.getDrinks().get(0).getStrIngredient1());
                                   textView4.setText(drinksModel.getDrinks().get(0).getStrMeasure1());

                                   //textView.setText(String.valueOf(id));
                                  // Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                            }
                        }
                );




//                .subscribe(new Consumer<Drink>() {
//                               @Override
//                               public void accept(Drink drink) throws Exception {
//                                  // textView.setText(drink.getStrDrink());
//                                   textView2.setText(drink.getStrInstructions());
//                                   Toast.makeText(getActivity(), drink.getStrDrink(), Toast.LENGTH_SHORT).show();
//                                   textView.setText(String.valueOf(id));
//                               }
//                           },
//                        new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable throwable) throws Exception {
//                                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                );












    }
}
