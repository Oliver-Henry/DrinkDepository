package com.example.olive.drinkdepository.search_drink;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olive.drinkdepository.MainActivity;
import com.example.olive.drinkdepository.OnItemSelectedListener;
import com.example.olive.drinkdepository.R;
import com.example.olive.drinkdepository.data.network.AppDataManager;
import com.example.olive.drinkdepository.data.network.model.Drink;
import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.example.olive.drinkdepository.data.network.service.IRequestInterface;
import com.example.olive.drinkdepository.data.network.service.ServiceConnection;
import com.example.olive.drinkdepository.ui.base.BaseFragment;
import com.example.olive.drinkdepository.ui.utils.rx.AppSchedulerProvider;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.jakewharton.rxbinding2.widget.RxSearchView;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchDrinkFragment extends BaseFragment implements ISearchDrinkMvpView {
    private RecyclerView recyclerView;
    private SearchView searchView;
    private SearchDrinkMvpPresenterImpl<SearchDrinkFragment> searchDrinkFragmentSearchDrinkMvpPresenter;
    private IRequestInterface iRequestInterface;
    private SearchDrinkAdapter searchDrinkAdapter;

    public SearchDrinkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_drink, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= view.findViewById(R.id.rVDrinkSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchView= view.findViewById(R.id.sVDrinkSearch);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
               // Toast.makeText(getActivity(), newText, Toast.LENGTH_SHORT).show();
                if(s != null){callService(s);}
                return true;
            }
        });
        searchDrinkFragmentSearchDrinkMvpPresenter= new SearchDrinkMvpPresenterImpl<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        searchDrinkFragmentSearchDrinkMvpPresenter.onAttach(this);
//        iRequestInterface= ServiceConnection.getConnection();


//        RxSearchView.queryTextChanges(searchView)
//                .debounce(350, TimeUnit.MILLISECONDS)
//                //.subscribeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .filter(new Predicate<CharSequence>() {
//                    @Override
//                    public boolean test(CharSequence charSequence) throws Exception {
//                        return !charSequence.toString().isEmpty();
//                    }
//                })
//                .distinctUntilChanged()
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .switchMap(new Function<CharSequence, ObservableSource<DrinksModel>>() {
//                    @Override
//                    public ObservableSource<DrinksModel> apply(CharSequence s) throws Exception {
//                        searchDrinkFragmentSearchDrinkMvpPresenter.loadSearchedDrinks(String.valueOf(s));
//                        return iRequestInterface.getSearchedDrink(s.toString());
//
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<DrinksModel>() {
//                               @Override
//                               public void accept(DrinksModel drinksModel) throws Exception {
//                                   searchDrinkAdapter.addSearchResponses(drinksModel);
//                               }
//                           },
//                        new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable throwable) throws Exception {
//                                Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
//                            }
//                        });


    }


    public void callService(String s){
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isConnectedToInternet) throws Exception {
                        if(isConnectedToInternet){ //Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                            searchDrinkFragmentSearchDrinkMvpPresenter.loadSearchedDrinks(s);}
                        else{Toast.makeText(getActivity(), "No Network Connection", Toast.LENGTH_SHORT).show();}
                    }
                });

    }

    @Override
    public void onFetchDataProgress() {

    }

    @Override
    public void onFetchDataSuccess(DrinksModel drinksModel) {
           if(drinksModel.getDrinks().size() > 0){recyclerView.setAdapter(new SearchDrinkAdapter(getActivity().getApplicationContext(), drinksModel.getDrinks(), R.layout.row_layout));}
           else{Toast.makeText(getContext(), "No Results Found", Toast.LENGTH_LONG).show();}

    }

    @Override
    public void onFetchDataError(String error) {
        showMessage(error);
    }



//    class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
//
//        private final DrinksModel drinksModel = new DrinksModel();
//
//        @Override
//        public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            return new SearchViewHolder(getLayoutInflater().inflate(R.layout.row_layout, parent, false));
//        }
//
//        @Override
//        public void onBindViewHolder(SearchViewHolder holder, int position) {
//            holder.doBinding(drinksModel);
//        }
//
//        @Override
//        public int getItemCount() {
//            return drinksModel.getDrinks().size();
//        }
//
//        void addSearchResponses(DrinksModel drinksModel) {
//            drinksModel.getDrinks().clear();
//            drinksModel.getDrinks().addAll(drinksModel.getDrinks());
//            notifyDataSetChanged();
//        }
//
//        class SearchViewHolder extends RecyclerView.ViewHolder implements OnItemSelectedListener {
//
//            final ImageView drinkImg;
//            final TextView drinkName;
//            private DrinksModel drinksModel;
//            private OnItemSelectedListener onItemSelectedListener;
//
//            SearchViewHolder(View itemView) {
//                super(itemView);
//                this.drinkImg = itemView.findViewById(R.id.imgV);
//                this.drinkName = itemView.findViewById(R.id.tVDrinkName);
//            //    itemView.setOnClickListener(this);
//            }
//
//            void doBinding(DrinksModel drinksModel) {
//                this.drinksModel = drinksModel;
//                this.drinkName.setText(drinksModel.getDrinks().get(0).getStrDrink());
//                Picasso.with(this.drinkImg.getContext()).load(drinksModel.getDrinks().get(0).getStrDrinkThumb()).into(this.drinkImg);
//            }
//
//            @Override
//            public void OnClick(View view, int position) {
//                MainActivity.showDrinkDetails(Integer.parseInt(drinksModel.getDrinks().get(position).getIdDrink()), position);
//            }
//        }
//    }
}
