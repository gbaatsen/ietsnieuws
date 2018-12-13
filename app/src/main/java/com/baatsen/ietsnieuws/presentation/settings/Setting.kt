package com.baatsen.ietsnieuws.presentation.settings

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes

data class Setting(
    val action: SettingActions,
    @StringRes val title: Int,
    @StringRes val subTitle: Int,
    @DrawableRes val icon: Int
)