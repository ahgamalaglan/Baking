package com.gemy.ahmed.baking.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gemy.ahmed.baking.R;
import com.gemy.ahmed.baking.models.Step;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    private final OnListItemClickListener mListener;
    private List<Step> steps = new ArrayList<>();

    public StepsAdapter(OnListItemClickListener listener) {
        mListener = listener;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NotNull final ViewHolder holder, int position) {
        holder.mIdView.setText("Step :"+(position+1));


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
        private final TextView mIdView;

        ViewHolder(View view) {
            super(view);
            mIdView = view.findViewById(R.id.tv_step_text);
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