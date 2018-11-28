package com.baatsen.ietsnieuws.presentation.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.baatsen.ietsnieuws.R
import com.baatsen.ietsnieuws.databinding.ActivityMainBinding


class ArticleListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ArticleListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProviders.of(this).get(ArticleListViewModel::class.java)
        viewModel.urlToOpen.observe(this, Observer { url -> startBrowser(url) })
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
    }

    private fun startBrowser(url: String?) {
        url?.let {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
    }
}