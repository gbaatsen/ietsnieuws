package com.baatsen.ietsnieuws.presentation.settings

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.widget.TextView
import com.baatsen.ietsnieuws.R
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf


class SettingsPage {

    fun checkDisplayed(): SettingsPage {
        //check toolbar title
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar)),
            withText(R.string.app_name)))
            .check(matches(withText(R.string.app_name)))

        //check toolbar subtitle
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar)),
            withText(R.string.settings)))
            .check(matches(withText(R.string.settings)))
        return this
    }

    fun select(settingResId: Int): SourcePage {
        onView(allOf(withText(settingResId))).perform(ViewActions.click())
        return SourcePage()
    }
}


