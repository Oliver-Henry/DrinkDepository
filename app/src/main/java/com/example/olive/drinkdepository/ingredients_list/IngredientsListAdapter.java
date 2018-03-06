package com.example.olive.drinkdepository.ingredients_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olive.drinkdepository.MainActivity;
import com.example.olive.drinkdepository.OnItemSelectedListener;
import com.example.olive.drinkdepository.R;
import com.example.olive.drinkdepository.data.network.model.Drink;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by olive on 06/03/2018.
 */

class IngredientsListAdapter extends RecyclerView.Adapter<IngredientsListAdapter.MyViewHolder> {
    Context applicationContext;
    List<Drink> drinks;
    int row_layout;

    public IngredientsListAdapter(Context applicationContext, List<Drink> drinks, int row_layout) {
        this.applicationContext = applicationContext;
        this.drinks = drinks;
        this.row_layout = row_layout;
    }

    @Override
    public IngredientsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(IngredientsListAdapter.MyViewHolder holder, int position) {
        holder.txtIngredientName.setText(drinks.get(position).getStrIngredient1());

        //https://www.thecocktaildb.com/images/ingredients/ice.png
        String url = "https://www.thecocktaildb.com/images/ingredients/" + drinks.get(position).getStrIngredient1() + ".png";
        Picasso.with(applicationContext).load(url).resize(300, 300).centerCrop().into(holder.imgView);

        holder.callItemClick(new OnItemSelectedListener() {
            @Override
            public void OnClick(View view, int position) {
                MainActivity.displayDrinksByIngredient(drinks.get(position).getStrIngredient1(), position);
                //Toast.makeText(applicationContext, drinks.get(position).getStrIngredient1(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtIngredientName;
        private ImageView imgView;
        private OnItemSelectedListener onItemSelectedListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtIngredientName = itemView.findViewById(R.id.tVDrinkName);
            imgView = itemView.findViewById(R.id.imgV);
            itemView.setOnClickListener(this);
        }

        public void callItemClick(OnItemSelectedListener onItemSelectedListener){
            this.onItemSelectedListener = onItemSelectedListener;
        }
        @Override
        public void onClick(View view) {
            onItemSelectedListener.OnClick(view, getPosition());
        }
    }
}
