package com.baatsen.ietsnieuws.presentation.settings

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baatsen.ietsnieuws.R
import com.baatsen.ietsnieuws.databinding.FragmentSettingsBinding
import com.baatsen.ietsnieuws.di.Injectable
import com.baatsen.ietsnieuws.presentation.settings.selectsource.SelectSourceFragment
import javax.inject.Inject


class SettingsFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentSettingsBinding
    lateinit var viewModel: SettingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SettingsViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        viewModel.settingsClick.observe(this, Observer { setting -> settingsClick(setting) })
        binding.settingsView.onClickListener = viewModel
        (activity as AppCompatActivity).let {
            it.setSupportActionBar(binding.toolbar.toolbar)
            it.supportActionBar?.setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbar.toolbar.let {
            it.setNavigationOnClickListener { _ -> activity?.onBackPressed() }
            it.setSubtitle(R.string.settings)
        }
        return binding.root
    }

    private fun settingsClick(setting: Setting?) {
        when (setting?.action) {
            SettingActions.CHANGE_SOURCE -> requireFragmentManager()
                .beginTransaction()
                .replace(
                R.id.container,
                SelectSourceFragment()
            )
                .addToBackStack(null)
                .commit()
        }
    }
}