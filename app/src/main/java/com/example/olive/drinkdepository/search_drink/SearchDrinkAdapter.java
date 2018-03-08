package com.example.olive.drinkdepository.search_drink;

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
import com.example.olive.drinkdepository.data.network.model.DrinksModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by olive on 07/03/2018.
 */

class SearchDrinkAdapter extends RecyclerView.Adapter<SearchDrinkAdapter.MyViewHolder> {
    Context applicationContext;
    List<Drink> drinks;
    int row_layout;

    public SearchDrinkAdapter(Context applicationContext, List<Drink> drinks, int row_layout) {
        this.applicationContext = applicationContext;
        this.drinks = drinks;
        this.row_layout = row_layout;
    }

    @Override
    public SearchDrinkAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String url = drinks.get(position).getStrDrinkThumb();
        Picasso.with(applicationContext).load(url).resize(300, 300).centerCrop().into(holder.imgView);

        holder.txtDrinkName.setText(drinks.get(position).getStrDrink());

        holder.callItemClick(new OnItemSelectedListener(){
            @Override
            public void OnClick(View view, int position) {
                MainActivity.showDrinkDetails(Integer.parseInt(drinks.get(position).getIdDrink()), position);
            }
        });
    }

//    @Override
//    public void onBindViewHolder(SearchViewHolder holder, int position) {
//      //  holder.doBinding(drinks.get(position));
//    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //        final ImageView drinkImg;
//        final TextView drinkName;
//        private DrinksModel drinksModel;
        private TextView txtDrinkName;
        private ImageView imgView;
        private OnItemSelectedListener onItemSelectedListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.imgView = itemView.findViewById(R.id.imgV);
            this.txtDrinkName = itemView.findViewById(R.id.tVDrinkName);
            itemView.setOnClickListener(this);
        }

        public void callItemClick(OnItemSelectedListener onItemSelectedListener){
            this.onItemSelectedListener = onItemSelectedListener;
        }

        @Override
        public void onClick(View view) {
            onItemSelectedListener.OnClick(view, getPosition());
        }


//    void addSearchResponses(DrinksModel drinksModel) {
//        drinksModel.getDrinks().clear();
//        drinksModel.getDrinks().addAll(drinksModel.getDrinks());
//        notifyDataSetChanged();
//    }


//        void doBinding(DrinksModel drinksModel) {
//         //   this.drinksModel = drinksModel;
//            this.drinkName.setText(drinksModel.getDrinks().get(0).getStrDrink());
//            Picasso.with(this.drinkImg.getContext()).load(drinksModel.getDrinks().get(0).getStrDrinkThumb()).into(this.drinkImg);
//        }
    }


}
