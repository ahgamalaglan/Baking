package com.gemy.ahmed.baking.ui.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gemy.ahmed.baking.R;
import com.gemy.ahmed.baking.models.Step;

import java.util.ArrayList;
import java.util.List;


public class RecipeDetailsAdapter extends RecyclerView.Adapter<RecipeDetailsAdapter.ViewHolder> {

    private final OnListItemClickListener mListener;
    List<Step> steps = new ArrayList<>();

    public RecipeDetailsAdapter(OnListItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(steps.get(position).getDescription());


    }

    @Override
    public int getItemCount() {
        if (steps == null)
            return 0;
        return steps.size();
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mIdView;

        public ViewHolder(View view) {
            super(view);
            mIdView = view.findViewById(R.id.tv_recipe_card_text);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(steps.get(getAdapterPosition()));
        }


    }

    public interface OnListItemClickListener {
        void onItemClick(Step step);
    }
}