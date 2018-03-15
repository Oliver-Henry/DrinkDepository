package com.example.olive.drinkdepository.drink_list;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olive.drinkdepository.R;
import com.example.olive.drinkdepository.data.local.RealmCategoryListModel;
import com.example.olive.drinkdepository.data.local.controller.RealmBackupRestore;
import com.example.olive.drinkdepository.data.local.controller.RealmHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by olive on 13/03/2018.
 */

class RealmDrinkListAdapter extends RecyclerView.Adapter<RealmDrinkListAdapter.MyViewHolder> {
    Context applicationContext;
    ArrayList<RealmCategoryListModel> drinkArrayList;
    int row_layout;
    private Realm realm;
    private RealmHelper realmHelper;
    private RealmBackupRestore realmBackupRestore;
    private Activity activity;

    public RealmDrinkListAdapter(Context applicationContext, ArrayList<RealmCategoryListModel> drinkArrayList, int row_layout) {
        this.applicationContext = applicationContext;
        this.drinkArrayList = drinkArrayList;
        this.row_layout = row_layout;
        this.activity = activity;
    }

    private void initRealm() {
        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);
    }

    @Override
    public RealmDrinkListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        initRealm();
        realmBackupRestore = new RealmBackupRestore(activity);
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RealmDrinkListAdapter.MyViewHolder holder, int position) {
        holder.rDrinkName.setText(drinkArrayList.get(position).getStrDrink());

        String url = drinkArrayList.get(position).getStrDrinkThumb();
        Picasso.with(applicationContext).load(url).resize(300, 300).centerCrop().into(holder.rDrinkImage);
    }

    @Override
    public int getItemCount() {
        return drinkArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView rDrinkName;
        private ImageView rDrinkImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            rDrinkName = itemView.findViewById(R.id.tVDrinkName);
            rDrinkImage = itemView.findViewById(R.id.imgV);
        }
    }
}
