package com.baatsen.ietsnieuws.presentation.settings

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.baatsen.ietsnieuws.R
import com.baatsen.ietsnieuws.SingleLiveEvent
import com.baatsen.ietsnieuws.presentation.settings.SettingActions.CHANGE_SOURCE
import com.baatsen.ietsnieuws.presentation.views.SettingsView
import javax.inject.Inject

class SettingsViewModel @Inject constructor() : ViewModel(), SettingsView.OnClickListener {


    val settings = MutableLiveData<List<Setting>>()
    val settingsClick = SingleLiveEvent<Setting>()

    init {
        settings.value = setupSettings()
    }

    private fun setupSettings(): List<Setting>? {
        return listOf(
            Setting(CHANGE_SOURCE, R.string.change_source, R.string.change_source_subtitle, R.drawable.ic_source)
        )
    }

    override fun onClick(setting: Setting) {
        settingsClick.value = setting
    }
}