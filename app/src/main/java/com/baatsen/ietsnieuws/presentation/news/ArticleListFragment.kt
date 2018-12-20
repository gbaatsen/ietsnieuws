package com.baatsen.ietsnieuws.presentation.news

import android.app.Activity.RESULT_OK
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.baatsen.ietsnieuws.R
import com.baatsen.ietsnieuws.databinding.FragmentArticlelistBinding
import com.baatsen.ietsnieuws.di.Injectable
import com.baatsen.ietsnieuws.presentation.settings.SettingsActivity
import org.jetbrains.anko.support.v4.browse
import org.jetbrains.anko.support.v4.intentFor
import javax.inject.Inject


class ArticleListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentArticlelistBinding
    lateinit var viewModel: ArticleListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleListViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_articlelist, container, false)
        binding.viewModel = viewModel
        viewModel.urlToOpen.observe(this, Observer { url -> startBrowser(url) })
        binding.setLifecycleOwner(this)
        (activity as AppCompatActivity).let {
            it.setSupportActionBar(binding.toolbar.toolbar)
            it.supportActionBar?.setDisplayHomeAsUpEnabled(true);
            it.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        }
        binding.toolbar.toolbar.let {
            it.setNavigationOnClickListener { _ -> activity?.onBackPressed() }
            it.setSubtitle(R.string.news_items)
        }

        return binding.root
    }

    private fun startBrowser(url: String?) {
        url?.let {
            browse(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.articlelist_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_settings -> {
            gotoSettings()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun gotoSettings() {
        startActivityForResult(intentFor<SettingsActivity>(), REFRESH_ON_RETURN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REFRESH_ON_RETURN && resultCode == RESULT_OK) {
            viewModel.refresh()
        }

    }

    companion object {
        const val REFRESH_ON_RETURN = 1
    }
}


