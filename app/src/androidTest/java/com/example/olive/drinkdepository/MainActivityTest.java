package com.example.olive.drinkdepository;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by olive on 14/03/2018.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void navigationMenuTest(){
        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));

        //onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT))).perform(DrawerActions.open());
        // Check if Nav drawer is closed.  // If is closed, open Nav drawer

        onView(withId(R.id.drawer_layout)).perform(swipeUp());
        //onView(withId(R.id.drawer_layout)).perform(NavigationViewActions.navigateTo(R.id.nav_search_drk_ingr), click());

        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_categories), click());//).perform(scrollTo(), click());

    }
}

