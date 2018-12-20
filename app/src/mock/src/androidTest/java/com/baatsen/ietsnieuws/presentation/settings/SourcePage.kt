package com.baatsen.ietsnieuws.presentation.settings

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.widget.TextView
import com.baatsen.ietsnieuws.R
import com.baatsen.ietsnieuws.presentation.news.ArticleListPage
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf


class SourcePage {

    fun checkDisplayed(): SourcePage {
        //check toolbar title
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar)),
            withText(R.string.app_name)))
            .check(matches(withText(R.string.app_name)))

        //check toolbar subtitle
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar)),
            withText(R.string.select_source)))
            .check(matches(withText(R.string.select_source)))
        return this
    }

    fun goto(source: String): ArticleListPage {
        onView(allOf(withText(source))).perform(ViewActions.click())
        return ArticleListPage()
    }
}


