package com.gemy.ahmed.baking.ui.details_activity.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.gemy.ahmed.baking.R;
import com.gemy.ahmed.baking.models.Step;
import com.gemy.ahmed.baking.viewmodels.RecipeViewModel;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StepFragment extends Fragment {

    private RecipeViewModel recipeViewModel;
    private Step step;
    private SimpleExoPlayer player;


    public StepFragment() {
    }


    public static StepFragment newInstance(int index) {
        StepFragment f = new StepFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null ) {
            recipeViewModel.setSelectedStep(getArguments().getInt("index"));
        }
        recipeViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(RecipeViewModel.class);
        recipeViewModel.getSelectedStep().observe(getActivity(), step1 -> {
            step = step1;

        });
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_step, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.tv_short_description);
        player = ExoPlayerFactory.newSimpleInstance(Objects.requireNonNull(getContext()), new DefaultTrackSelector(), new DefaultLoadControl());
        PlayerView playerView = view.findViewById(R.id.playerView);
        playerView.setPlayer(player);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), "Baking"));
        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(step.getVideoURL()));
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
        textView.setText(step.getDescription());
        Objects.requireNonNull(getActivity()).setTitle("Step : " .concat(String.valueOf(step.getId()+1)));

    }

    @Override
    public void onPause() {
        super.onPause();
        player.stop();
        player.release();
        player = null;
    }

}
