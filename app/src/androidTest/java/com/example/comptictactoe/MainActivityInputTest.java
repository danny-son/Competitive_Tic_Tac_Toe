package com.example.comptictactoe;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import com.example.comptictactoe.View.MainActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInputTest {
    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule(MainActivity.class);

    @Test
    public void testTextInput() {
        //ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.player_one_name_edit_text)).perform(typeText("Danny"))
                .check(matches(withText("Danny")));
        onView(withId(R.id.player_two_name_edit_text)).perform(typeText("Son"))
                .check(matches(withText("Son")));
    }

    @Test
    public void testPlayerInputSwitchedToGame() {
       // ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.player_one_name_edit_text)).perform(typeText("Danny"));
        onView(withId(R.id.player_two_name_edit_text)).perform(typeText("Son"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.play_game_button)).perform(click());
        onView(withId(R.id.player_one_points_text)).check(matches(withText("Danny: 2")));
        onView(withId(R.id.player_two_points_text)).check(matches(withText("Son: 2")));

    }

    @Test
    public void testBothPlayerInputNotFilled() {
        onView(withId(R.id.play_game_button)).perform(click());
        onView(withId(R.id.player_one_name_edit_text)).check(matches(isDisplayed()));
        onView(withId(R.id.player_two_name_edit_text)).check(matches(isDisplayed()));
    }

    @Test
    public void testOnePlayerInput() {
        ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.player_one_name_edit_text)).perform(typeText("danny"),
                ViewActions.closeSoftKeyboard());
        onView(withId(R.id.play_game_button)).perform(click());
        onView(withId(R.id.player_one_name_edit_text)).check(matches(isDisplayed()));

        onView(withId(R.id.player_one_name_edit_text)).perform(replaceText(""));
        onView(withId(R.id.player_two_name_edit_text)).perform(typeText("son"),
                ViewActions.closeSoftKeyboard());
        onView(withId(R.id.play_game_button)).perform(click());
        onView(withId(R.id.player_two_name_edit_text)).check(matches(isDisplayed()));


    }

}