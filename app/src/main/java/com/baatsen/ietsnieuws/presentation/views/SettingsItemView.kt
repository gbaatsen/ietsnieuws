package com.baatsen.ietsnieuws.presentation.views

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import com.baatsen.ietsnieuws.R
import com.baatsen.ietsnieuws.databinding.ViewSettingsItemBinding

class SettingsItemView : ConstraintLayout {

    private val binding: ViewSettingsItemBinding =
        ViewSettingsItemBinding.inflate(LayoutInflater.from(context), this, true)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        minHeight = resources.getDimension(R.dimen.settings_row_height).toInt()

        // Padding
        val padding = resources.getDimension(R.dimen.spacing_tiny).toInt()
        setPadding(padding, padding, padding, padding)

        // Background
        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        setBackgroundResource(outValue.resourceId)
    }

    fun setTitle(@StringRes resId: Int) {
        binding.settingsItemTitle.setText(resId)
    }

    fun setTitle(charSequence: CharSequence?) {
        binding.settingsItemTitle.text = charSequence
    }

    fun setSubTitle(@StringRes resId: Int) {
        binding.settingsItemSubTitle.setText(resId)
    }

    fun setSubTitle(charSequence: CharSequence?) {
        binding.settingsItemSubTitle.text = charSequence
    }

    fun setIcon(@DrawableRes resId: Int) {
        binding.icon.setImageResource(resId)
    }
}