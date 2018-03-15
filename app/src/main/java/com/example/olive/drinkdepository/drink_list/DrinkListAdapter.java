package com.example.olive.drinkdepository.drink_list;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olive.drinkdepository.MainActivity;
import com.example.olive.drinkdepository.OnItemSelectedListener;
import com.example.olive.drinkdepository.R;
import com.example.olive.drinkdepository.data.local.RealmCategoryListModel;
import com.example.olive.drinkdepository.data.local.controller.RealmBackupRestore;
import com.example.olive.drinkdepository.data.local.controller.RealmHelper;
import com.example.olive.drinkdepository.data.network.model.Drink;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.Realm;

/**
 * Created by olive on 03/03/2018.
 */

class DrinkListAdapter extends RecyclerView.Adapter<DrinkListAdapter.MyViewHolder> {
    Activity applicationContext;
    List<Drink> drink;
    int row_layout;
    private Realm realm;
    private RealmHelper realmHelper;
    private RealmBackupRestore realmBackupRestore;
    private RealmCategoryListModel realmCategoryListModel;

    public DrinkListAdapter(Activity applicationContext, List<Drink> drink, int row_layout) {
    this.applicationContext= applicationContext;
    this.drink = drink;
    this.row_layout = row_layout;
    }

    private void initRealm() {
        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);
    }

    @Override
    public DrinkListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        initRealm();
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(DrinkListAdapter.MyViewHolder holder, int position) {

        realmCategoryListModel = new RealmCategoryListModel(
                drink.get(position).getIdDrink(),
                drink.get(position).getStrDrink(),
                drink.get(position).getStrDrinkThumb()
        );
        realmHelper.saveDrinksR(realmCategoryListModel);


        String url = drink.get(position).getStrDrinkThumb();
        Picasso.with(applicationContext).load(url).resize(300, 300).centerCrop().into(holder.imgView);

        holder.txtDrinkName.setText(drink.get(position).getStrDrink());

        holder.callItemClick(new OnItemSelectedListener(){
            @Override
            public void OnClick(View view, int position) {
                MainActivity.showDrinkDetails(Integer.parseInt(drink.get(position).getIdDrink()), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return drink.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtDrinkName;
        private ImageView imgView;
        private OnItemSelectedListener onItemSelectedListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtDrinkName = itemView.findViewById(R.id.tVDrinkName);
            imgView = itemView.findViewById(R.id.imgV);
            itemView.setOnClickListener(this);
        }

        public void callItemClick(OnItemSelectedListener onItemSelectedListener){
            this.onItemSelectedListener = onItemSelectedListener;
        }
        @Override
        public void onClick(View view) {onItemSelectedListener.OnClick(view, getPosition());}

    }


}
