package com.example.olive.drinkdepository.drink_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olive.drinkdepository.MainActivity;
import com.example.olive.drinkdepository.OnItemSelectedListener;
import com.example.olive.drinkdepository.R;
import com.example.olive.drinkdepository.data.network.model.Drink;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by olive on 03/03/2018.
 */

class DrinkListAdapter extends RecyclerView.Adapter<DrinkListAdapter.MyViewHolder> {
    Context applicationContext;
    List<Drink> drinks;
    int row_layout;

    public DrinkListAdapter(Context applicationContext, List<Drink> drinks, int row_layout) {
    this.applicationContext= applicationContext;
    this.drinks = drinks;
    this.row_layout = row_layout;
    }

    @Override
    public DrinkListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(DrinkListAdapter.MyViewHolder holder, int position) {
        String url = drinks.get(position).getStrDrinkThumb();
        Picasso.with(applicationContext).load(url).resize(300, 300).centerCrop().into(holder.imgView);

        holder.txtDrinkName.setText(drinks.get(position).getStrDrink());
      //  holder.txtAlcoholic.setText(drinks.get(position).getStrAlcoholic());
      //  holder.txtCategory.setText(drinks.get(position).getStrCategory());

        holder.callItemClick(new OnItemSelectedListener(){
            @Override
            public void OnClick(View view, int position) {
                MainActivity.showDrinkDetails(Integer.parseInt(drinks.get(position).getIdDrink()), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtDrinkName;
       // private TextView txtAlcoholic;
       // private TextView txtCategory;
        private ImageView imgView;
        private OnItemSelectedListener onItemSelectedListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtDrinkName = itemView.findViewById(R.id.tVDrinkName);
        //    txtAlcoholic = itemView.findViewById(R.id.tVAlcoholic);
        //    txtCategory = itemView.findViewById(R.id.tVCategory);
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
