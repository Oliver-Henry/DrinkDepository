package com.example.olive.drinkdepository;

import com.example.olive.drinkdepository.home.HomeFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by olive on 15/03/2018.
 */

public class HomeFragmentTest {

    @Rule
    public FragmentTestRule<HomeFragment> homeFragmentFragmentTestRule = new FragmentTestRule<>(HomeFragment.class);

    @Before
    public void homeFragStartUp() throws Exception{
        homeFragmentFragmentTestRule.launchActivity(null);
    }

    @Test
    public void fragment_can_be_instantiated() {

        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));
    }

    @Test
    public void recyclerViewTest(){
//                onView(withId(R.id.rVCategories))
//                .check(matches(isDisplayed()));
//        onView(withId(R.id.rVCategories)).perform(scrollToPosition(4));
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        onView(withText("Cocoa")).check(matches(isDisplayed())); // Other/Unknown
    }
}
