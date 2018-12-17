package com.baatsen.ietsnieuws.presentation.news


import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.baatsen.ietsnieuws.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule

    val activityRule = ActivityTestRule(MainActivity::class.java)
    private val page = ArticleListPage()

    @Test
    fun verifyDisplayed() {
        page.checkDisplayed()
    }

    @Test
    fun selectSourceTest() {
        page.gotoSettings()
            .checkDisplayed()
            .select(R.string.change_source)
            .checkDisplayed()
            .goto("ABC News (AU)")
    }
}
