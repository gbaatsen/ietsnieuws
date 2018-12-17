package com.baatsen.ietsnieuws.presentation.news

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.widget.TextView
import com.baatsen.ietsnieuws.R
import com.baatsen.ietsnieuws.presentation.settings.SettingsPage
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf


class ArticleListPage {

    fun checkDisplayed(): ArticleListPage {
        //check toolbar title
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar)), withText(R.string.app_name)))
            .check(matches(withText(R.string.app_name)))

        //check toolbar subtitle
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar)), withText(R.string.news_items)))
            .check(matches(withText(R.string.news_items)))
        return this
    }

    fun gotoSettings(): SettingsPage {
        onView(withId(R.id.menu_settings)).perform(click())
        return SettingsPage()
    }
}


