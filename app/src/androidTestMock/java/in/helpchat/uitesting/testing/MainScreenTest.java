package in.helpchat.uitesting.testing;

import android.app.Activity;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import in.helpchat.uitesting.MainActivity;
import in.helpchat.uitesting.R;

/**
 * Created by adarshpandey on 6/23/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainScreenTest {


    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class, true, false);


    @Before
    public void init() {
        Intent startIntent = new Intent();
        mainActivityActivityTestRule.launchActivity(startIntent);
    }

    @Test
    public void clickTest() {
        // onView(withText(R.string.TOAST_STRING))
        // .inRoot(withDecorView(not(is(getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.click_me)).perform(ViewActions.click());

        InstrumentationRegistry.getContext().getAssets();

        goHome(mainActivityActivityTestRule.getActivity());

        bringToFroeground(mainActivityActivityTestRule.getActivity());

        Espresso.onView(ViewMatchers.withId(R.id.click_me)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withText("Hello Hello"))
                .inRoot(RootMatchers.withDecorView(CoreMatchers.not(CoreMatchers.is(mainActivityActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }

    private void goHome(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        activity.startActivity(intent);
    }

    private void bringToFroeground(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.startActivity(intent);
    }
}
