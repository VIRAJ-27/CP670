package com.example.andriodassignments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.GrantPermissionRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ListtemsActivityInstrumentedTest {

    @Rule
    public ActivityScenarioRule<ListItemsActivity> activityScenarioRule = new ActivityScenarioRule<>(ListItemsActivity.class);

    @Rule
    public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.CAMERA);

    @Before
    public void setUp() {
        ActivityScenario.launch(ListItemsActivity.class);
    }

    @After
    public void tearDown() {
        activityScenarioRule.getScenario().close();
    }

    @Test
    public void testImageButtonExists() {
        onView(withId(R.id.imageButton)).check(matches(isDisplayed()));
    }

    @Test
    public void testSwitchButtonExists() {
        onView(withId(R.id.switch1)).check(matches(isDisplayed()));
    }

    @Test
    public void testCheckBoxExists() {
        onView(withId(R.id.checkBox)).check(matches(isDisplayed()));
    }

//    @Test
//    public void testCameraLaunch() {
//        onView(withId(R.id.imageButton)).perform(click());
//        // Verify if camera intent is started by checking if camera activity is displayed
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        assertThat(cameraIntent.resolveActivity(getPackageManager()), notNullValue());
//    }

    @Test
    public void testSwitchToggle() {
        onView(withId(R.id.switch1)).perform(click());
        // Perform an action to check the switch toggling, for example swipe right
        onView(withId(R.id.switch1)).perform(swipeRight());
    }

    @Test
    public void testCheckBoxClick() {
        onView(withId(R.id.checkBox)).perform(click());
        // Verify if dialog is shown after clicking the checkbox
        onView(withText(R.string.dialog_message)).check(matches(isDisplayed()));
    }
}
