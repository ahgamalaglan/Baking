package com.gemy.ahmed.baking.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gemy.ahmed.baking.models.Step;
import com.gemy.ahmed.baking.ui.details_activity.fragments.StepFragment;

import java.util.List;

public class StepsPagerAdapter extends FragmentPagerAdapter {

    private List<Step> steps;
    public StepsPagerAdapter(FragmentManager fm, List<Step> steps) {
        super(fm);
        this.steps = steps;
    }

    @Override
    public Fragment getItem(int position) {
        return StepFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return steps.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
