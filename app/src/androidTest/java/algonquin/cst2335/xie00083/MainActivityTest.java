package algonquin.cst2335.xie00083;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
/** This is the test class for MainActivity class
 * @author Feiling Xie
 * @version 1.0
 */

public class MainActivityTest {

    /**  This hold a test instance about rules to test MainActivity class */
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test

    /** This function is to test if the password meets the all requirements.
     *
     */
    public void mainActivityTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText = onView(withId(R.id.password));
        appCompatEditText.perform(replaceText("Pass12345@#"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withText("Login"));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("Your password meets the requirements")));
    }


    /** This function is to test if the password has an uppercase letter.
     *
     */
    @Test
    public void testFindMissingUpperCase() {
        //find the view
        ViewInteraction appCompatEditText = onView(withId(R.id.password));
        //type in password123#$*
        appCompatEditText.perform(replaceText("password123#$*"));

        //find the button
        ViewInteraction materialButton = onView(withId(R.id.button));//click the button
        materialButton.perform(click());

        //find the text view
        ViewInteraction textView = onView(withId(R.id.textView));//check the text
        textView.check(matches(withText("You shall not pass!")));
    }

    /** This function is to test if the password has a lowercase letter.
     *
     */
    @Test
    public void testFindMissinglowercase() {
        //find the view
        ViewInteraction appCompatEditText = onView(withId(R.id.password));
        //type in password123#$*
        appCompatEditText.perform(replaceText("P123#$*"));

        //find the button
        ViewInteraction materialButton = onView(withId(R.id.button));//click the button
        materialButton.perform(click());

        //find the text view
        ViewInteraction textView = onView(withId(R.id.textView));//check the text
        textView.check(matches(withText("You shall not pass!")));
    }

    /** This function is to test if the password has a number.
     *
     */
    @Test
    public void testFindMissingNumber() {
        //find the view
        ViewInteraction appCompatEditText = onView(withId(R.id.password));
        //type in password123#$*
        appCompatEditText.perform(replaceText("Pass#$*"));

        //find the button
        ViewInteraction materialButton = onView(withId(R.id.button));//click the button
        materialButton.perform(click());

        //find the text view
        ViewInteraction textView = onView(withId(R.id.textView));//check the text
        textView.check(matches(withText("You shall not pass!")));
    }

    /** This function is to test if the password has a special character.
     *
     */
    @Test
    public void testFindMissingSpecial() {
        //find the view
        ViewInteraction appCompatEditText = onView(withId(R.id.password));
        //type in password123#$*
        appCompatEditText.perform(replaceText("Pass123"));

        //find the button
        ViewInteraction materialButton = onView(withId(R.id.button));//click the button
        materialButton.perform(click());

        //find the text view
        ViewInteraction textView = onView(withId(R.id.textView));//check the text
        textView.check(matches(withText("You shall not pass!")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
