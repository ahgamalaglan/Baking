package com.gemy.ahmed.baking.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gemy.ahmed.baking.models.Step;
import com.gemy.ahmed.baking.ui.details_activity.fragments.StepFragment;

import java.util.List;

public class StepsPagerAdapter extends FragmentPagerAdapter {

    private List<Step> steps;
    private Step step;
    public StepsPagerAdapter(FragmentManager fm, List<Step> steps,Step step) {
        super(fm);
        this.steps = steps;
        this.step = step;
    }

    @Override
    public Fragment getItem(int position) {
        return StepFragment.newInstance(position,steps,step);
    }

    @Override
    public int getCount() {
        return steps.size();
    }

}
