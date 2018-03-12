package com.example.olive.drinkdepository.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.olive.drinkdepository.MainActivity;
import com.example.olive.drinkdepository.OnItemSelectedListener;
import com.example.olive.drinkdepository.R;
import com.example.olive.drinkdepository.data.network.model.Drink;

import java.util.List;

/**
 * Created by olive on 12/03/2018.
 */

class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.MyViewHolder> {
    Context applicationContext;
    List<Drink> drinks;
    int category_row_layout;

    public HomeCategoryAdapter(Context applicationContext, List<Drink> drinks, int category_row_layout) {
        this.applicationContext = applicationContext;
        this.drinks = drinks;
        this.category_row_layout = category_row_layout;
    }

    @Override
    public HomeCategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(category_row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeCategoryAdapter.MyViewHolder holder, int position) {
        holder.categoryName.setText(drinks.get(position).getStrCategory());

        holder.callItemClick(new OnItemSelectedListener(){
            @Override
            public void OnClick(View view, int position) {
                MainActivity.displayDrinksFromCategory(drinks.get(position).getStrCategory(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView categoryName;
        private OnItemSelectedListener onItemSelectedListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.tVCategoryName);
            itemView.setOnClickListener(this);
        }

        public void callItemClick(OnItemSelectedListener onItemSelectedListener){
            this.onItemSelectedListener = onItemSelectedListener;
        }
        @Override
        public void onClick(View view) {onItemSelectedListener.OnClick(view, getPosition());}
    }
}
