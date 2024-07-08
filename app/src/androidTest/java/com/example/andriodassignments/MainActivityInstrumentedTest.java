package com.example.andriodassignments;

import android.content.Intent;
import android.widget.Button;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        ActivityScenario.launch(MainActivity.class);
    }

    @After
    public void tearDown() {
        activityScenarioRule.getScenario().close();
    }

    @Test
    public void testButtonsExist() {
        onView(withId(R.id.button)).check(matches(isDisplayed()));
        onView(withId(R.id.start_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testStartListItemsActivity() {
        onView(withId(R.id.button)).perform(click());
        // Check if the ListItemsActivity is started
        onView(withId(R.id.main)).check(matches(isDisplayed()));
    }

    @Test
    public void testStartChatWindow() {
        onView(withId(R.id.start_button)).perform(click());
        // Check if the Chat_window activity is started
        onView(withId(R.id.main)).check(matches(isDisplayed()));
    }

//    @Test
//    public void testReturnFromListItemsActivity() {
//        // Start ListItemsActivity
//        onView(withId(R.id.button)).perform(click());
//
//        // Simulate returning a result
//        Intent resultData = new Intent();
//        resultData.putExtra("Response", "Test Response");
//        activityScenarioRule.getScenario().onActivity(activity -> activity.onActivityResult(MainActivity.REQUEST_CODE_LIST_ITEMS, MainActivity.RESULT_OK, resultData));
//
//        // Check if the Toast message is displayed
//        onView(withText("List items passed: Test Response")).inRoot(new CustomToastMatcher()).check(matches(isDisplayed()));
//    }
}
