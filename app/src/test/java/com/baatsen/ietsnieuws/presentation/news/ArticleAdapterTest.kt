package com.baatsen.ietsnieuws.presentation.news

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.baatsen.ietsnieuws.domain.model.Article
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class ArticleAdapterTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var testRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var clickListener: ArticleAdapter.ClickListener

    @Mock
    private lateinit var viewHolder: ArticleAdapter.ViewHolder

    @InjectMocks
    private lateinit var adapter: ArticleAdapter

    private val article = Article("title", "description", "artUrl", "ImgUrl", "date", "bla")
    private val articleList = listOf(article, article, article, article)

    @Before
    fun setup() {
        adapter = ArticleAdapter(clickListener)
    }

    @Test
    fun `ItemCount returns 0 when not initialized`() {
        assert(adapter.itemCount == 0)
    }

    @Test
    fun `ItemCount returns the correct number when initialized`() {
        adapter.updateArticleList(articleList)
        assert(adapter.itemCount == 4)

    }

    @Test
    fun `Viewholders bind method is called on bind`() {
        adapter.updateArticleList(articleList)
        adapter.onBindViewHolder(viewHolder, 0)
        verify(viewHolder).bind(article)
    }
}