package com.example.andriodassignments;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class chatwindowinstrumentedtest {

    @Rule
    public ActivityScenarioRule<Chat_window> activityScenarioRule = new ActivityScenarioRule<>(Chat_window.class);

    @Test
    public void testSendMessage() {
        // Type the message in the input field and close the keyboard
        Espresso.onView(ViewMatchers.withId(R.id.chat_input_text))
                .perform(typeText("Hello, World!"), closeSoftKeyboard());

        // Click the send button
        Espresso.onView(ViewMatchers.withId(R.id.chat_send_button)).perform(click());

        // Pause briefly to allow the UI thread to process the update
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the message is displayed in the list view
        Espresso.onView(ViewMatchers.withId(R.id.chat_list_view))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(withText("Hello, World!"))));
    }
}
