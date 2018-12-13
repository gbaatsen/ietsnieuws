package com.baatsen.ietsnieuws.presentation.news

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.baatsen.ietsnieuws.R
import com.baatsen.ietsnieuws.databinding.ArticleBinding
import com.baatsen.ietsnieuws.domain.model.Article

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private lateinit var articleList: List<Article>
    lateinit var clickListener: ClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ArticleBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.article, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articleList[position])

    }

    override fun getItemCount(): Int {
        return if (::articleList.isInitialized) articleList.size else 0
    }

    fun updateArticleList(articleList: List<Article>) {
        this.articleList = articleList
    }

    inner class ViewHolder(private val binding: ArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
            binding.root.setOnClickListener { clickListener.onClick(article.urlToArticle) }
            binding.executePendingBindings()
        }
    }

    interface ClickListener {
        fun onClick(url: String?)
    }
}