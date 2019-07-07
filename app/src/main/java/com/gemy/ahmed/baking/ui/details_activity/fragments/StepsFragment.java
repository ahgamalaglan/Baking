package com.gemy.ahmed.baking.ui.details_activity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gemy.ahmed.baking.R;
import com.gemy.ahmed.baking.adapters.StepsPagerAdapter;
import com.gemy.ahmed.baking.models.Step;

import java.util.List;


public class StepsFragment extends Fragment {

    private int index;
    private Step step;
    private List<Step> steps;

    public StepsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (getArguments().getParcelableArrayList("steps") != null) {
                steps = getArguments().getParcelableArrayList("steps");
                index = getArguments().getInt("stepIndex");
                step = steps.get(index);

            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_steps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager mViewPager = view.findViewById(R.id.vp_steps_pager);
        mViewPager.setAdapter(new StepsPagerAdapter(getChildFragmentManager(),steps,step));
    }


}
