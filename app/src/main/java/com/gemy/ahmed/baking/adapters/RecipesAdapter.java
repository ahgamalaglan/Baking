package com.gemy.ahmed.baking.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gemy.ahmed.baking.R;
import com.gemy.ahmed.baking.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.Holder> {

    private List<Recipe> recipes;
    private OnRecipeClickListener onRecipeClickListener;

    public RecipesAdapter(OnRecipeClickListener onRecipeClickListener) {
        recipes = new ArrayList<>();
        this.onRecipeClickListener = onRecipeClickListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView.setText(recipes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (recipes == null)
            return 0;
        return recipes.size();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_recipe_card_text);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onRecipeClickListener.onRecipeClick(recipes.get(getAdapterPosition()));
        }
    }

    public interface OnRecipeClickListener {
        void onRecipeClick(Recipe recipe);
    }
}
