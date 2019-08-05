package com.gemy.ahmed.baking;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.gemy.ahmed.baking.ui.main_activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.matcher.ViewMatchers.*;


@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> testRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testRecipeItemClick() {
    onData(withId(R.id.rv_recipes)).perform();
    }
}
