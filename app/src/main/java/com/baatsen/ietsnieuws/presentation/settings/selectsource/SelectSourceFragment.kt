package com.baatsen.ietsnieuws.presentation.settings.selectsource

import android.app.Activity
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import com.baatsen.ietsnieuws.R
import com.baatsen.ietsnieuws.databinding.FragmentSelectSourceBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SelectSourceFragment : Fragment() {

    lateinit var binding: FragmentSelectSourceBinding
    val viewModel: SelectSourceViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_source, container, false)
        binding.viewModel = viewModel
        viewModel.sourceSelected.observe(this, Observer { closeSettings() })
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.setLifecycleOwner(this)
        (activity as AppCompatActivity).let {
            it.setSupportActionBar(binding.toolbar.toolbar)
            it.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.toolbar.let {
            it.setNavigationOnClickListener { _ -> goBack() }
            it.setSubtitle(R.string.select_source)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.selectsource_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        val searchView = menu?.findItem(R.id.menu_search)?.actionView as? SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.filter(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filter(newText.orEmpty())
                return true
            }
        })
    }

    private fun goBack() {
        requireFragmentManager().popBackStackImmediate()
    }

    private fun closeSettings() {
        activity?.setResult(Activity.RESULT_OK)
        activity?.finish()
    }
}
