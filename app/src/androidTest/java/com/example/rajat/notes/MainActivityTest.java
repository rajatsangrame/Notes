package com.example.rajat.notes;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.rajat.notes.ui.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.Espresso.onView;

/**
 * Created by Rajat Sangrame on 18/1/20.
 * http://github.com/rajatsangrame
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    private static final String TAG = "MainActivityTest";
    private MainActivity mainActivity = null;

    @Rule
    public ActivityTestRule<MainActivity> testRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init() {
        Log.i(TAG, "@before init: ");
        mainActivity = testRule.getActivity();
        mainActivity.getSupportFragmentManager().beginTransaction();
    }

    @After
    public void reset() {
        Log.i(TAG, "@after reset: ");
        mainActivity = null;
    }

    /**
     * 10 Test cases in sequence asked in Task
     */
    @Test
    public void performTest() {

        for (int i = 0; i < title.length; i++) {

            //MainActivity
            onView(withId(R.id.menu_add)).perform(click());

            //BottomSheet
            onView(withId(R.id.et_title)).perform(typeText(title[i]));
            onView(withId(R.id.et_note)).perform(typeText(desc[i]));
            onView(withId(R.id.btn_save)).perform(click());

            //DetailActivity
            pressBack();

            //MainActivity
            onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition(0, click()));

            //DetailActivity
            pressBack();
        }
    }

    //region Constant used for testing
    private String[] title = {
            "Lorem ipsum dolor sit amet",
            "Consectetur adipiscing elit.",
            "Zenonis est, inquam, hoc Stoici.",
            "Ratio enim nostra consentit, pugnat oratio.",
            "Sint modo partes vitae beatae.",
            "Quae contraria sunt his, malane",
            "Lorem ipsum dolor sit amet",
            "Consectetur adipiscing elit.",
            "Zenonis est, inquam, hoc Stoici.",
            "Ratio enim nostra consentit, pugnat oratio.",
            "Quae contraria sunt his, malane"};

    private String[] desc = {
            "Tenetur quod quidem in voluptatem corporis dolorum dicta sit pariatur porro quaerat autem ipsam odit quam beatae tempora quibusdam illum! Modi velit odio nam nulla unde amet odit pariatur at!",
            "Laboriosam quaerat sapiente minima nam minus similique illum architecto et!",
            "Ad dolore dignissimos asperiores dicta facere optio quod commodi nam tempore recusandae. Rerum sed nulla eum vero expedita ex delectus voluptates rem at neque quos facere sequi unde optio aliquam!",
            "Tenetur laborum quod cum excepturi recusandae porro sint quas soluta!",
            "Consequatur rerum amet fuga expedita sunt et tempora saepe? Iusto nihil explicabo perferendis quos provident delectus ducimus necessitatibus reiciendis optio tempora unde earum doloremque commodi laudantium ad nulla vel odio",
            "Tenetur laborum quod cum excepturi recusandae porro sint quas soluta!",
            "Tenetur quod quidem in voluptatem corporis dolorum dicta sit pariatur porro quaerat autem ipsam odit quam beatae tempora quibusdam illum! Modi velit odio nam nulla unde amet odit pariatur at!",
            "Laboriosam quaerat sapiente minima nam minus similique illum architecto et!",
            "Ad dolore dignissimos asperiores dicta facere optio quod commodi nam tempore recusandae. Rerum sed nulla eum vero expedita ex delectus voluptates rem at neque quos facere sequi unde optio aliquam!",
            "Tenetur laborum quod cum excepturi recusandae porro sint quas soluta!",
            "Consequatur rerum amet fuga expedita sunt et tempora saepe? Iusto nihil explicabo perferendis quos provident delectus ducimus necessitatibus reiciendis optio tempora unde earum doloremque commodi laudantium ad nulla vel odio",
    };
    //endregion

}
