package com.baatsen.ietsnieuws.presentation.settings.selectsource

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baatsen.ietsnieuws.R
import com.baatsen.ietsnieuws.databinding.FragmentSelectSourceBinding
import com.baatsen.ietsnieuws.di.Injectable
import javax.inject.Inject

class SelectSourceFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentSelectSourceBinding
    lateinit var viewModel: SelectSourceViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SelectSourceViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_source, container, false)
        binding.viewModel = viewModel
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.setLifecycleOwner(this)
        (activity as AppCompatActivity).let {
            it.setSupportActionBar(binding.toolbar.toolbar)
            it.supportActionBar?.setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbar.toolbar.let {
            it.setNavigationOnClickListener { _ -> activity?.onBackPressed() }
            it.setSubtitle(R.string.select_source)
        }
        return binding.root
    }
}
