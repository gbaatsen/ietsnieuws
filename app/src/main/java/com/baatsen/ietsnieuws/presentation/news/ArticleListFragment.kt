package com.baatsen.ietsnieuws.presentation.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baatsen.ietsnieuws.R
import com.baatsen.ietsnieuws.databinding.FragmentArticlelistBinding
import com.baatsen.ietsnieuws.di.Injectable
import javax.inject.Inject


class ArticleListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentArticlelistBinding
    lateinit var viewModel: ArticleListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleListViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_articlelist, container, false)
        binding.viewModel = viewModel
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.urlToOpen.observe(this, Observer { url -> startBrowser(url) })
        binding.setLifecycleOwner(this)
        return binding.root
    }

    private fun startBrowser(url: String?) {
        url?.let {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
    }
}
