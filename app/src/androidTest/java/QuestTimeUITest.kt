import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.screen.common.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class QuestTimeUITest {
    companion object {
        fun getFirstItem(resyclerViewId: Int): ViewInteraction =
            onView(withId(resyclerViewId)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    0
                )
            )
    }

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun settingsButtonTest() {
        onView(withId(R.id.navigation_settings)).perform(click())

        onView(withId(R.id.fragment_settings_lang_container)).check(matches(isDisplayed()))
    }

    @Test
    fun previewOpenTest() {
        onView(withId(R.id.navigation_external)).perform(click())
        getFirstItem(R.id.external__recycler_view).perform(click())

        onView(withId(R.id.fragment_preview_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun playTest() {
        onView(withId(R.id.navigation_library)).perform(click())
        getFirstItem(R.id.library_recycler_view).perform(click())
        onView(withId(R.id.fragment_preview__playButton)).perform(click())

        onView(withId(R.id.fragment_content__image)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_content__text)).check(matches(isDisplayed()))
    }
}