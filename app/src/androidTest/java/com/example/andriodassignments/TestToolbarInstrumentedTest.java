package com.example.andriodassignments;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestToolbarInstrumentedTest {

    private ActivityScenario<TestToolbar> scenario;

    @Before
    public void setUp() {
        // Launch the TestToolbar activity
        scenario = ActivityScenario.launch(TestToolbar.class);
    }

    @After
    public void tearDown() {
        // Close the activity scenario after each test
        scenario.close();
    }

    @Test
    public void testMenuItemOne() {
        // Click on menu item 1 and verify Snackbar message
        onView(withId(R.id.action_choice_1)).perform(click());
        // Verify that Snackbar message is displayed
        onView(withText("You selected item 1")).check(matches(isDisplayed()));
    }

    @Test
    public void testMenuItemTwo() {
        // Click on menu item 2 and verify exit confirmation dialog appears
        onView(withId(R.id.action_choice_2)).perform(click());
        // Verify that the dialog title is displayed
        onView(withText("Do you want to go back?")).check(matches(isDisplayed()));
        // Perform click on OK button in the dialog (Not recommended to automate dialog dismiss action directly)
        // You can assert that clicking the OK button should finish the activity
    }

    @Test
    public void testMenuItemThree() {
        // Click on menu item 3 and verify custom dialog appears
        onView(withId(R.id.action_choice_3)).perform(click());
        // Verify that the custom dialog title is displayed
        onView(withText("Custom Dialog")).check(matches(isDisplayed()));
        // Perform actions in the custom dialog (enter text, click OK)
        onView(withId(android.R.id.button1)).perform(click()); // Assuming OK button is android.R.id.button1
        // Verify that the custom message is updated
        onView(withId(R.id.fab)).perform(click()); // Click FAB to show Snackbar with updated message
        onView(withText("This is a custom Snackbar message!")).check(matches(isDisplayed())); // Adjust with your expected custom message
    }

//    @Test
//    public void testAboutMenuItem() {
//        // Click on About menu item and verify Toast message
//        onView(withId(R.id.action_about)).perform(click());
//        // Verify that Toast message is displayed
//        onView(withText("Version 1.0, by DARSHKUMAR PATEL")).inRoot(new ToastMatcher())
//                .check(matches(isDisplayed()));
//    }
}
