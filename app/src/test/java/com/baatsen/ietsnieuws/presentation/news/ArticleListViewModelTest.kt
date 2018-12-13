package com.baatsen.ietsnieuws.presentation.news

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.baatsen.ietsnieuws.domain.interactor.GetNewsInteractor
import com.baatsen.ietsnieuws.utils.ImmediateSchedulerProvider
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit


class ArticleListViewModelTest {
    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    var testRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getNewsInteractor: GetNewsInteractor

    @Mock
    private lateinit var adapter: ArticleAdapter

    private lateinit var viewModel: ArticleListViewModel

    @Before
    fun setUp() {
        whenever(getNewsInteractor.execute()).thenReturn(Single.just(emptyList()))
        viewModel = ArticleListViewModel(ImmediateSchedulerProvider, adapter, getNewsInteractor)
    }

    @Test
    fun `Articles are loaded on init`() {
        verify(getNewsInteractor).execute()
    }

    @Test
    fun `Refresh reloads articles`() {
        viewModel.refresh()
        verify(getNewsInteractor, times(2)).execute()
    }

    @Test
    fun `Click on item sets URL`() {
        viewModel.onClick("url")
        assert(viewModel.urlToOpen.value == "url")
    }


}