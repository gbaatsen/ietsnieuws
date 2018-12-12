package com.baatsen.ietsnieuws.presentation.settings

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.baatsen.ietsnieuws.R
import com.baatsen.ietsnieuws.presentation.settings.SettingActions.CHANGE_SOURCE
import com.baatsen.ietsnieuws.presentation.views.SettingsView
import javax.inject.Inject

class SettingsViewModel @Inject constructor() : ViewModel(), SettingsView.OnClickListener {


    val settings = MutableLiveData<List<Setting>>()
    val _settingsClick = MutableLiveData<Setting>()

    val settingsClick: LiveData<Setting>
        get() = _settingsClick

    init {
        settings.value = setupSettings()
    }

    private fun setupSettings(): List<Setting>? {
        return listOf(
            Setting(CHANGE_SOURCE, R.string.change_source, R.string.change_source_subtitle)
        )
    }

    override fun onClick(setting: Setting) {
        _settingsClick.value = setting
    }
}