package com.ry.bakingapp.pages.home;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ry.bakingapp.R;
import com.ry.bakingapp.pages.detailstep.DetailStepActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class DetailStepActivityTest {
    @Rule
    public ActivityTestRule<DetailStepActivity> activityTestRule = new ActivityTestRule<DetailStepActivity>(DetailStepActivity.class);

    @Test
    public void ensurePrevClickWork() {
        onView(withId(R.id.btn_prev))
                .perform(click())
                .check(matches(isDisplayed()));
    }
    @Test
    public void ensureNextClickWork() {
        onView(withId(R.id.btn_next))
                .perform(click())
                .check(matches(isDisplayed()));
    }
}