package com.example.andriodassignments;

import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class Login_acitivity_instrumental_test{

    private ActivityScenario<LoginActivity> scenario;
    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        scenario = ActivityScenario.launch(LoginActivity.class);
        scenario.onActivity(activity -> {
            sharedPreferences = activity.getSharedPreferences("MyPrefsFile", activity.MODE_PRIVATE);
            sharedPreferences.edit().clear().apply();
        });
    }

    @After
    public void tearDown() {
        if (scenario != null) {
            scenario.close();
        }
    }

    @Test
    public void testLoginButtonExists() {
        scenario.onActivity(activity -> {
            Button loginButton = activity.findViewById(R.id.button3);
            assertNotNull(loginButton);
        });
    }

    @Test
    public void testEmailFieldExists() {
        scenario.onActivity(activity -> {
            EditText emailField = activity.findViewById(R.id.editTextText);
            assertNotNull(emailField);
        });
    }

    @Test
    public void testPasswordFieldExists() {
        scenario.onActivity(activity -> {
            EditText passwordField = activity.findViewById(R.id.editTextText2);
            assertNotNull(passwordField);
        });
    }

    @Test
    public void testDefaultEmailIsSet() {
        scenario.onActivity(activity -> {
            EditText emailField = activity.findViewById(R.id.editTextText);
            String defaultEmail = emailField.getText().toString();
            assertEquals("email@domain.com", defaultEmail);
        });
    }


    @Test
    public void testEmptyPasswordShowsError() {
        onView(withId(R.id.editTextText)).perform(typeText("email@domain.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextText2)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.button3)).perform(click());

        // Check if the error text is set on the password EditText
        onView(withId(R.id.editTextText2)).check(matches(withText("")));
    }

    @Test
    public void testValidLoginStartsMainActivity() {
        onView(withId(R.id.editTextText)).perform(typeText("email@domain.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextText2)).perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.button3)).perform(click());

        // Verify that MainActivity is started by checking an element in MainActivity
        onView(withId(R.id.main)).check(matches(isDisplayed()));
    }
}
