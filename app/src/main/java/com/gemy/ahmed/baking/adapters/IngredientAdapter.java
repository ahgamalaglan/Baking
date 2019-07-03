package com.gemy.ahmed.baking.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gemy.ahmed.baking.R;
import com.gemy.ahmed.baking.models.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.Holder> {
    private List<Ingredient> ingredientList = new ArrayList<>();

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.amount.setText(String.valueOf(ingredient.getQuantity()));
        holder.amountType.setText(ingredient.getMeasure());
        holder.type.setText(ingredient.getIngredient());
    }

    @Override
    public int getItemCount() {
        if (ingredientList == null)
            return 0;
        return ingredientList.size();
    }

    public void setIngredients(List<Ingredient> ingredients) {
        ingredientList = ingredients;
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView amount, amountType, type;

        Holder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.tv_ingredient_amount);
            amountType = itemView.findViewById(R.id.tv_ingredient_amount_type);
            type = itemView.findViewById(R.id.tv_ingredient_type);
        }
    }
}
